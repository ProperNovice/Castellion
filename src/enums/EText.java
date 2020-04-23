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
	SELECT_FIRST_TILE("Select first tile", TextTypeEnum.INDICATOR),
	SELECT_SECOND_TILE("Select second tile", TextTypeEnum.INDICATOR),

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
