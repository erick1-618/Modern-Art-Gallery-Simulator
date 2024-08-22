package br.com.erick.msim;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import br.com.erick.msim.SectionButton.panels;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	
	private List<LabelListener> labelListeners = new ArrayList<LabelListener>();
	private double money = 0;
	private JLabel label;
	private JPanel productionPanel;
	private List<ProductionButton> pButtons = new ArrayList<>();
	private List<ManagerButton> mButton = new ArrayList<>();
	private JPanel managersPanel;
	private JPanel middlePanel;
	
	public void addListener(LabelListener l) {
		labelListeners.add(l);
	}
	
	public void notifyListener(double money) {
		labelListeners.forEach(l -> {
			l.peformAction(money);
		});
	}
	
	public synchronized void add(double value) {
		this.money += value;
		label.setText("R$" + String.format("%.2f", this.money));
		notifyListener(money);
	}
	
	public synchronized void sub(double value) {
		this.money -= value;
		label.setText("R$" + String.format("%.2f", this.money));
		notifyListener(money);
	}
	
	public MainWindow() {
		setSize(new Dimension(800, 600));
		setTitle("ModernArtMuseum Simulator");
		setLayout(new BorderLayout());
		
		this.productionPanel = getProdutionPanel();
		this.managersPanel = getManagersPanel();
		this.middlePanel = getMiddlePanel();
		
		
		add(getTopGrid(), BorderLayout.NORTH);
		add(this.middlePanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private JPanel getTopGrid() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout());
		grid.add(new SectionButton(panels.PRODUCTION, this.middlePanel, this.productionPanel));
		grid.add(new SectionButton(panels.MANAGERS, this.middlePanel, this.managersPanel));
		return grid;
	}
	
	private JPanel getMiddlePanel() {
		this.label = new JLabel("R$0.00");
		label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.label, BorderLayout.NORTH);
		panel.add(productionPanel ,BorderLayout.CENTER);
		
		return panel;
	}
	
	//GRIDBAGLAYOUT TUTORIAL: https://www.youtube.com/watch?v=sq46PYdW4c8
	private JPanel getProdutionPanel() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(5, 2));
		grid.add(getProductionUnity("Teste 1", 40, 1, true, 0));
		grid.add(getProductionUnity("Teste 2", 300, 4, false, 160));
		grid.add(getProductionUnity("Teste 3", 1200, 8, false, 1600));
		grid.add(getProductionUnity("Teste 4", 5600, 12, false, 8000));
		grid.add(getProductionUnity("Teste 5", 13000, 44, false, 22000));
		grid.add(getProductionUnity("Teste 6", 140000, 124, false, 123000));
		grid.add(getProductionUnity("Teste 7", 700000, 400, false, 600000));
		grid.add(getProductionUnity("Teste 8", 3000000, 4000, false, 1321360));
		grid.add(getProductionUnity("Teste 9", 30123120, 4000, false, 1231260));
		grid.add(getProductionUnity("Teste 10", 301231203, 4000, false, 1111360));
		return grid;
	}
	
	private JPanel getManagersPanel() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(5, 2));
		mButton.add(new ManagerButton("+1 Da Vinci", 150, pButtons.get(0), this));
		mButton.add(new ManagerButton("+1 Michelangelo", 1500, pButtons.get(1), this));
		mButton.add(new ManagerButton("+1 Donattelo", 6000, pButtons.get(2), this));
		mButton.add(new ManagerButton("+1 Rafael", 36000, pButtons.get(3), this));
		mButton.add(new ManagerButton("+1 Mozzart", 96000, pButtons.get(4), this));
		mButton.add(new ManagerButton("+1 Van Gogh", 250000, pButtons.get(5), this));
		mButton.add(new ManagerButton("+1 Vic Muniz", 111111, pButtons.get(6), this));
		mButton.add(new ManagerButton("+1 Sla", 20022322, pButtons.get(7), this));
		mButton.add(new ManagerButton("+1 Sla", 342132423, pButtons.get(8), this));
		mButton.add(new ManagerButton("+1 Sla", 243241231, pButtons.get(9), this));
		mButton.forEach(b -> {
			addListener(b);
			grid.add(b);
		});
		return grid;
	}
	
	private JPanel getProductionUnity(String name, double value, int durationInSeconds, boolean isEnabled, double unlockValue) {
		ProductionUnity productionUnity = new ProductionUnity(unlockValue, isEnabled, this);
		productionUnity.setLayout(new GridBagLayout());
		this.addListener(productionUnity);

		ArrayList<Component> components = new ArrayList<Component>();
		
		JProgressBar pb = new JProgressBar();
		pb.setString("R$" + value);
		pb.setStringPainted(true);
		pb.setForeground(Color.GREEN);
		ProductionButton button = new ProductionButton(name, pb, this, value, durationInSeconds);
		pButtons.add(button);
		ImprovementButton iButton = new ImprovementButton(button);
		components.add(pb);
		components.add(button);
		components.add(iButton);

		productionUnity.saveProductionSettings(components);
		
		if(isEnabled) {
			productionUnity.unlock();
		}
		
		return productionUnity;
	}
	
	public double getMoney() {
		return money;
	}

	public static void main(String[] args) {
		new MainWindow();
	}
}
