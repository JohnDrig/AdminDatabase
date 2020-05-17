package paket;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class LabelMain extends JLabel {

	ImageLoader il = new ImageLoader();

	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D gd = (Graphics2D) g;

		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// paint
		g.setFont(new Font("Arial", Font.BOLD, 17));

		// paint Buttons
		g.setColor(new Color(101, 137, 143));
		g.fillRect(MainWindow.delete.x, MainWindow.delete.y, MainWindow.delete.breite, MainWindow.delete.hoehe);

		if (MainWindow.delete.hover) {
			g.setColor(new Color(39, 184, 37));
			g.fillRect(MainWindow.delete.x, MainWindow.delete.y, MainWindow.delete.breite, MainWindow.delete.hoehe);
		}

		g.drawImage(il.deleteIcon, MainWindow.delete.x + (0 + MainWindow.delete.breite) / 2 - 15,
				MainWindow.delete.y + 2, 30, 30, null);

		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.setColor(Color.WHITE);
		g.drawString("First name", 84, MainWindow.hoehe / 2 - 20);

		g.drawString("Second name", 70, MainWindow.hoehe / 2 + 60);

		g.drawString("Age", 105, MainWindow.hoehe / 2 + 140);

		g.drawString("Gender", 90, MainWindow.hoehe / 2 + 220);

		g.setColor(Color.GRAY);
		g.drawRect(30, 310, 280, 360);

		repaint();
	}

}
