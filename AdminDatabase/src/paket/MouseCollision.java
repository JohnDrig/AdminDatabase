package paket;

public class MouseCollision {

	private int breite, hoehe;

	public boolean inside(int mouseX, int mouseY, Button button) {

		this.breite = button.breite;
		this.hoehe = button.hoehe;

		if (mouseX > button.x && mouseX <= button.x + breite && mouseY > button.y && mouseY <= button.y + hoehe) {
			return true;
		} else {
			return false;
		}
	}
}
