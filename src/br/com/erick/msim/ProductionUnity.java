package br.com.erick.msim;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ProductionUnity extends JPanel implements LabelListener{
	
	private MainWindow mainWindow;
	private double unlockValue;
	private boolean isUnlocked;
	private List<Component> productionUnityComponents = new ArrayList<Component>();
	private BuyButton buyButton;
	
	public ProductionUnity(double unlockValue, boolean isEnabled, MainWindow m) {
		this.mainWindow = m;
		this.unlockValue = unlockValue;
		this.isUnlocked = isEnabled;
		this.buyButton = new BuyButton(this, unlockValue);
	}
	
	public void saveProductionSettings(List<Component> list) {
		this.productionUnityComponents = list;
	}
	
	public void unlock() {
		removeAll();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipadx = 50;
		gbc.ipady = 50;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(productionUnityComponents.get(1), gbc);
		
		gbc.gridx = 1;
		add(productionUnityComponents.get(2), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		add(productionUnityComponents.get(0), gbc);
		
		revalidate();
		repaint();
		isUnlocked = true;
		if(this.unlockValue != 0) {			
			mainWindow.sub(unlockValue);
		}
	}
	
	public void peformAction(double value) {
		if(!isUnlocked) {
			if(value >= unlockValue) {
				add(buyButton);
				revalidate();
				repaint();
			}else {
				removeAll();
				revalidate();
				repaint();
			}			
		}
	}
}
