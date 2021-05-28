package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Font;
import com.codename1.ui.plaf.Border;

/**
 * Creates a custom button that every button is instantiated from in Game
 * @author kiryl
 *
 */

public class GameButton {
	private Button gameButton;
	
	/* adds Style to the button */
	public GameButton(String label) {
		gameButton = new Button(label);
		gameButton.getAllStyles().setBgTransparency(255);
		gameButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		gameButton.getSelectedStyle().setBgColor(ColorUtil.WHITE);
		gameButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		gameButton.getSelectedStyle().setFgColor(ColorUtil.BLUE);
		gameButton.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		gameButton.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.BLACK));
		gameButton.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		gameButton.getAllStyles().setPadding(Component.TOP, 5);
		gameButton.getAllStyles().setPadding(Component.BOTTOM, 5);
	}
	
	/* returns the button */
	public Button getGameButton() {
		return gameButton;
	}
}
