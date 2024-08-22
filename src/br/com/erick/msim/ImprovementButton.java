package br.com.erick.msim;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ImprovementButton extends JButton implements MouseListener{
	
	private ProductionButton productionButton;
	private double tax;
	
	public ImprovementButton(ProductionButton b) {
		addMouseListener(this);
		this.productionButton = b;
		setTax(productionButton.getValue() / 10);
	}
	
	private void setTax(double tax) {
		this.tax = tax;
		this.setText("R$" + this.tax);
	}

	public MainWindow getMainWindow() {
		return productionButton.getMainWindow();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			if(getMainWindow().getMoney() >= tax) {
				getMainWindow().sub(tax);
				productionButton.setValue(productionButton.getValue() + (productionButton.getValue() * 0.2));
				setTax(tax * 2);				
			}
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
