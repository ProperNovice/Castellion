package utils;

import javafx.animation.AnimationTimer;

public enum Animation {

	INSTANCE;

	private final double ANIMATION_STEP = 4;

	private ArrayList<NodeAnimation> animationsSynchronous = new ArrayList<NodeAnimation>();
	private ArrayList<NodeAnimation> animationsAsynchronous = new ArrayList<NodeAnimation>();
	private ArrayList<INode> nodesAnimating = new ArrayList<>();

	private Animation() {
		startAnimation();
	}

	public enum AnimationSynch {
		SYNCHRONOUS, ASYNCHRONOUS
	}

	private void startAnimation() {
		new AnimationTimerFX().start();
	}

	private class AnimationTimerFX extends AnimationTimer {

		@Override
		public void handle(long time) {

			if (!animationsSynchronous.isEmpty())
				executeAnimationSynchronous();
			if (!animationsAsynchronous.isEmpty())
				executeAnimationAsynchronous();

		}

	}

	private void executeAnimationSynchronous() {

		executeAnimationList(this.animationsSynchronous);

		if (!this.animationsSynchronous.isEmpty())
			return;

		Lock.INSTANCE.unlock();

	}

	private void executeAnimationAsynchronous() {
		executeAnimationList(this.animationsAsynchronous);
	}

	private void executeAnimationList(ArrayList<NodeAnimation> animationsList) {

		ArrayList<NodeAnimation> animationsListTemp = animationsList.clone();

		for (NodeAnimation imageViewAnimation : animationsListTemp) {

			imageViewAnimation.executeAnimation();
			imageViewAnimation.getNode().toFront(); // TODO

			if (!imageViewAnimation.animationEnded())
				continue;

			animationsList.remove(imageViewAnimation);
			this.nodesAnimating.remove(imageViewAnimation.getNode());

		}

	}

	private class NodeAnimation {

		private INode node = null;
		private double currentX, currentY;
		private double endingX, endingY;
		private boolean animationEnded = false;
		private double stepX, stepY;

		public NodeAnimation(INode node, double endingX, double endingY) {

			this.node = node;
			this.endingX = endingX;
			this.endingY = endingY;

			calculateCedentials();

		}

		private void calculateCedentials() {

			this.currentX = this.node.getLayoutX();
			this.currentY = this.node.getLayoutY();

			double differenceX = Math.abs(this.endingX - this.currentX);
			double differenceY = Math.abs(this.endingY - this.currentY);

			if (differenceX > differenceY) {

				this.stepX = ANIMATION_STEP;
				this.stepY = differenceY * ANIMATION_STEP / differenceX;

			} else if (differenceY > differenceX) {

				this.stepX = differenceX * ANIMATION_STEP / differenceY;
				this.stepY = ANIMATION_STEP;

			} else if (differenceX == differenceY) {

				this.stepX = ANIMATION_STEP;
				this.stepY = ANIMATION_STEP;

			}

		}

		public void executeAnimation() {

			executeX();
			executeY();

			this.node.relocateTopLeft(this.currentX, this.currentY);

			if (Math.floor(this.currentX) != Math.floor(this.endingX))
				return;

			if (Math.floor(this.currentY) != Math.floor(this.endingY))
				return;

			this.animationEnded = true;

		}

		private void executeX() {

			if (this.currentX == this.endingX)
				return;

			if (Math.abs(this.endingX - this.currentX) <= this.stepX) {
				this.currentX = this.endingX;
				return;
			}

			if (this.currentX < this.endingX)
				this.currentX += this.stepX;
			else if (this.currentX > this.endingX)
				this.currentX -= this.stepX;

		}

		private void executeY() {

			if (this.currentY == this.endingY)
				return;

			if (Math.abs(this.endingY - this.currentY) <= this.stepY) {
				this.currentY = this.endingY;
				return;
			}

			if (this.currentY < this.endingY)
				this.currentY += this.stepY;
			else if (this.currentY > this.endingY)
				this.currentY -= this.stepY;

		}

		public boolean animationEnded() {
			return this.animationEnded;
		}

		public INode getNode() {
			return this.node;
		}

	}

	public void animate(INode node, double endingX, double endingY, AnimationSynch animationSynch) {

		if (this.nodesAnimating.contains(node)) {

			for (NodeAnimation nodeAnimation : this.animationsSynchronous.clone())
				if (nodeAnimation.getNode() == node)
					this.animationsSynchronous.remove(nodeAnimation);

			for (NodeAnimation nodeAnimation : this.animationsAsynchronous.clone())
				if (nodeAnimation.getNode() == node)
					this.animationsAsynchronous.remove(nodeAnimation);

		} else
			this.nodesAnimating.addLast(node);

		PlatformFX.runLater(() -> {

			ArrayList<NodeAnimation> listToAdd = null;

			switch (animationSynch) {

			case SYNCHRONOUS:
				listToAdd = this.animationsSynchronous;
				break;

			case ASYNCHRONOUS:
				listToAdd = this.animationsAsynchronous;
				break;

			}

			node.toFront();
			listToAdd.addLast(new NodeAnimation(node, endingX, endingY));

		});

	}

	public void animate(INode node, NumbersPair numbersPair, AnimationSynch animationSynch) {
		animate(node, numbersPair.x, numbersPair.y, animationSynch);
	}

	public boolean isAnimatingSynchronous() {
		return !this.animationsSynchronous.isEmpty();
	}

	public boolean isAnimatingAsynchronous() {
		return !this.animationsAsynchronous.isEmpty();
	}

	public boolean isAnimating() {

		if (this.animationsSynchronous.isEmpty() && this.animationsAsynchronous.isEmpty())
			return false;
		else
			return true;

	}

	public void moveAsynchronousToSynchronous() {

		PlatformFX.runLater(new Runnable() {

			@Override
			public void run() {

				animationsSynchronous.addAll(animationsAsynchronous);
				animationsAsynchronous.clear();

			}
		});

	}

	public void moveAsynchronousToSynchronousLock() {

		moveAsynchronousToSynchronous();
		Lock.INSTANCE.lock();

	}

}
