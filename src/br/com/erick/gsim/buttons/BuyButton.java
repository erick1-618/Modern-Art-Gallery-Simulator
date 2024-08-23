package br.com.erick.gsim.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import br.com.erick.gsim.production_utilities.ProductionUnity;

public class BuyButton extends JButton implements MouseListener{

	private ProductionUnity productionUnity;
	
	public BuyButton(ProductionUnity pu, double unlockValue) {
		addMouseListener(this);
		this.productionUnity = pu;
		setText("Unlock R$" + unlockValue);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {			
			((ProductionUnity)this.getParent()).unlock();
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
