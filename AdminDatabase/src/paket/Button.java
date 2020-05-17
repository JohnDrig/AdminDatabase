package paket;

public class Button {

	public int x, y, breite, hoehe;
	public boolean hover;

	public Button(int x, int y, int breite, int hoehe, boolean hover) {
		setX(x);
		setY(y);
		setBreite(breite);
		setHoehe(hoehe);
		setHover(hover);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

	

}
