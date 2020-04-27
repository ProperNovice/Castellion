package enums;

import utils.Text;

public enum EText {

	BLANK("", TextTypeEnum.INDICATOR),
	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	DRAW_TILE_FROM_PILE("Draw tile from pile", TextTypeEnum.INDICATOR),
	NORMAL("Normal", TextTypeEnum.OPTION),
	SAFE("Safe", TextTypeEnum.OPTION),
	CHOOSE_A_SEER_TILE("Choose a Seer tile", TextTypeEnum.INDICATOR),
	PLACE_TILE("Place tile", TextTypeEnum.INDICATOR),
	DISCARD_TILE("Discard tile", TextTypeEnum.OPTION),
	USE_ABILITY("Use ability", TextTypeEnum.OPTION),
	CONTINUE_WITHOUT_RESOLVING("Continue without resolving", TextTypeEnum.OPTION),
	CHOOSE_TILE_PILE_TO_ADD_FROM("Choose tile pile to add from", TextTypeEnum.INDICATOR),
	CHOOSE_TILE_TO_DESTROY("Choose tile to destroy", TextTypeEnum.INDICATOR),
	CHOOSE_TILES_TO_DESTROY("Choose tiles to destroy", TextTypeEnum.INDICATOR),
	SELECT_FIRST_TILE("Select first tile", TextTypeEnum.INDICATOR),
	SELECT_SECOND_TILE("Select second tile", TextTypeEnum.INDICATOR),
	CHOOSE_TILE_TO_RECOVER("Choose tile to recover", TextTypeEnum.INDICATOR),
	CHOOSE_TILE_TO_MOVE("Choose tile to move", TextTypeEnum.INDICATOR),
	CHOOSE_PLACE_TO_MOVE_TO("Choose place to move to", TextTypeEnum.INDICATOR),
	RESOLVE_ORDEAL_CARD("Resolve ordeal card", TextTypeEnum.INDICATOR),
	NOT_COMPLETE_FOUNDATION("Not complete foundation", TextTypeEnum.INDICATOR),

	;

	private String string = null;
	private TextTypeEnum textTypeEnum = null;

	public enum TextTypeEnum {
		INDICATOR, OPTION
	}

	private EText(String string, TextTypeEnum textTypeEnum) {
		this.string = string;
		this.textTypeEnum = textTypeEnum;
	}
	
	public void showText() {
		Text.INSTANCE.showText(this);
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
