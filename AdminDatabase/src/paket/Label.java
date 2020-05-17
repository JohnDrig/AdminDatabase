package paket;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class Label extends JLabel{

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D gd = (Graphics2D) g;
		
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//paint 
		g.setFont(new Font("Calibri",Font.BOLD, 20));
		g.drawString("Username", 70, 70);
		g.drawString("Password", 70, 170);
	}
}
