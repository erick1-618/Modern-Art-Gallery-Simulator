package br.com.erick.msim;

import javax.swing.JProgressBar;

public class ProductionThread extends Thread{
	
	private JProgressBar progressBar;
	private MainWindow mainWindow;
	private double productionValue;
	private boolean manager;
	private int pauses;
	
	public ProductionThread(MainWindow w, JProgressBar pg, int durationInSeconds, double value, boolean manager) {
		this.manager = manager;
		this.productionValue = value;
		this.mainWindow = w;
		this.progressBar = pg;
		this.pauses = durationInSeconds * 10;
		this.start();
	}
	
	@Override
	public void run() {
		while(progressBar.getValue() != progressBar.getMaximum()) {
			progressBar.setValue(progressBar.getValue() + 1);
			try {
				Thread.sleep(pauses);
			} catch (Exception e) {
			}
		}
		progressBar.setValue(0);
		mainWindow.add(productionValue);
		if(manager) {
			new ProductionThread(mainWindow, progressBar, pauses / 10, productionValue, manager);
		}
	}
}
