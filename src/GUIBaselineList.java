


import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


public class GUIBaselineList extends JFrame implements ActionListener, WindowListener {

	private SCI selectedSCI;
	private JList UIlist;
	private CORootController controller;
	private Vector<Baseline> baselines;
	private JButton btnBack, btnSelect, btnMainMenu;
	
	public GUIBaselineList() {
		// TODO Auto-generated constructor stub
	}
	
	public GUIBaselineList(CORootController controller, SCI sci, JFrame previousView) {
		// TODO Auto-generated constructor stub
		previousView.setVisible(false);
		this.setBounds(200, 200, 300, 150);
		this.setSelectedSCI(sci);
		this.setController(controller);
		this.setBaselines(this.controller.getBaselinesFromVSCI(sci));
		this.addWindowListener(this);
	
		this.createUI();
	}

	public void createUI(){
		
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		
		
		
		Object[] baselineNames = new Object[baselines.size()];
		
		int index = 0;
		for(Baseline bl: baselines){
			
			System.out.println(bl.getVersionNumber());
			baselineNames[index] = bl.getVersionNumber();
			index++;
		}
		
		
		UIlist = new JList(baselineNames); //data has type Object[]
		UIlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		UIlist.setVisibleRowCount(-1);
		UIlist.setSelectedIndex(0);

		
		JScrollPane listScroller = new JScrollPane(UIlist);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		cp.add(listScroller);

		 btnBack = new JButton("Back");
		 btnBack.addActionListener(this);
		cp.add(btnBack);
	
		 btnSelect = new JButton("Select");
		 btnSelect.addActionListener(this);
		cp.add(btnSelect);
		
		 btnMainMenu = new JButton("Main Menu");
		 btnMainMenu.addActionListener(this);
		cp.add(btnMainMenu);
		
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == btnBack){
			controller.popView();
		}
		
		if(e.getSource() == btnSelect){
			if(this.UIlist.getSelectedIndex() > -1){
				GUIChangeRequest request = new GUIChangeRequest(this.getController(), this.baselines.get(this.UIlist.getSelectedIndex()) );
				this.controller.pushView(request);
			}
		}
		if(e.getSource() == btnMainMenu){
			this.controller.popToRootView();
		}
	}

	public SCI getSelectedSCI() {
		return selectedSCI;
	}

	public void setSelectedSCI(SCI selectedSCI) {
		this.selectedSCI = selectedSCI;
	}



	public CORootController getController() {
		return controller;
	}

	public void setController(CORootController controller) {
		this.controller = controller;
	}

	public Vector<Baseline> getBaselines() {
		return baselines;
	}

	public void setBaselines(Vector<Baseline> baselines) {
		this.baselines = baselines;
	}

}
