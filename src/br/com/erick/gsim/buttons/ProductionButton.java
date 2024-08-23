package br.com.erick.gsim.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import br.com.erick.gsim.main.MainWindow;
import br.com.erick.gsim.production_utilities.ProductionThread;

public class ProductionButton extends JButton implements MouseListener{
	
	private JProgressBar progressBar;
	private MainWindow mainWindow;
	private int durationInSeconds;
	private double value;
	private int managers = 0;
	private String name;
	
	public ProductionButton(String name, JProgressBar pb, MainWindow w, double value, int duration) {
		addMouseListener(this);
		this.name = name;
		this.setText(this.name + "   mng: " + this.managers);
		this.durationInSeconds = duration;
		this.value = value;
		this.mainWindow = w;
		this.progressBar = pb;
		this.progressBar.setString("R$" + this.value);
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setValue(double value) {
		this.value = value;
		this.progressBar.setString("R$" + String.format("%.2f", value));
	}

	public double getValue() {
		return value;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {			
			if(progressBar.getValue() == progressBar.getMinimum()) {			
				new ProductionThread(this.mainWindow, progressBar, this.durationInSeconds, this.value, false);
			}
		}
	}
	
	public void addManager() {
		new ProductionThread(mainWindow, progressBar, durationInSeconds, value, true);
		this.managers++;
		setText(name + "   Mng: " + this.managers); 
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
