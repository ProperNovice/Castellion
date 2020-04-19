package utils;

public interface INode {

	public void relocateTopLeft(double x, double y);

	public void relocateTopLeft(NumbersPair numbersPair);

	public double getLayoutX();

	public double getLayoutY();

	public void toFront();

	public void toBack();

}
