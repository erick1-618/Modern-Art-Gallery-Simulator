package br.com.erick.msim;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SectionButton extends JButton implements MouseListener{
	
	public static enum panels {PRODUCTION, MANAGERS};
	private panels showFunction;
	private JPanel middlePane;
	private JPanel panel;
	
	public SectionButton(panels p, JPanel middlePanel, JPanel panel) {
		addMouseListener(this);
		this.showFunction = p;
		this.middlePane = middlePanel;
		this.panel = panel;
		this.setText(showFunction.toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			this.middlePane.remove(1);
			this.middlePane.add(panel);
			this.middlePane.revalidate();
			this.middlePane.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
