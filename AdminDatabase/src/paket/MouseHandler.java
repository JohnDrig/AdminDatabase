package paket;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener, MouseListener {

	public MouseCollision mc = new MouseCollision();
	static String indexPersonString;
	MainWindow mw = new MainWindow();

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (mc.inside(e.getX(), e.getY(), MainWindow.delete)) {
			MainWindow.delete.setHover(true);
		} else {
			MainWindow.delete.setHover(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (mc.inside(e.getX(), e.getY(), MainWindow.delete)) {
			mw.deletePersonListener();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
