package br.com.erick.msim;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ManagerButton extends JButton implements MouseListener, LabelListener{

	private double cost;
	private ProductionButton productionButton;
	private MainWindow mainWindow;
	
	public ManagerButton(String name, double cost, ProductionButton pb, MainWindow mw) {
		this.mainWindow = mw;
		addMouseListener(this);
		setText(name);
		setVisible(false);
		this.cost = cost;
		this.productionButton = pb;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			mainWindow.sub(cost);
			productionButton.addManager();
			this.cost = this.cost * 2;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void peformAction(double value) {
		if(value >= cost) {
			setVisible(true);
		}else {
			setVisible(false);
		}
	}
}
