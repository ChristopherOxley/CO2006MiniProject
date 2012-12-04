

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;




public class GUISCIList extends JFrame implements ActionListener, WindowListener {

	private CORootController controller;
	private JList UIlist;
	private  Vector<SCI> sciList;
	private JButton btnSelect, btnBack, btnMainMenu;
	
	
	public GUISCIList() {
		
	}
	
	public GUISCIList(CORootController controller) {
	
		sciList = controller.getListOfSCIs(controller.getTrunk());

		
		this.setBounds(200, 200, 300, 150);
		this.setController(controller);
		this.addWindowListener(this);
		this.createUI();

	}

	public void createUI(){
		
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		
		
		
		Object[] sciNames = new Object[sciList.size()];
		
		int index = 0;
		for(SCI sci: sciList){
			
			System.out.println(sci.getName());
			sciNames[index] = sci.getName();
			index++;
		}
		
		
		UIlist = new JList(sciNames); //data has type Object[]
		UIlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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
	
	public CORootController getController() {
		return controller;
	}

	public void setController(CORootController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getSource());
		
		if(e.getSource() == btnSelect){
			if(this.UIlist.getSelectedIndex() > -1){
				GUIBaselineList base = new GUIBaselineList(this.getController(), this.sciList.get(this.UIlist.getSelectedIndex()) , this);
				this.controller.pushView(base);
			}
		}
		
		if(e.getSource() == btnBack){
			this.controller.popView();
		}
		
		if(e.getSource() == btnMainMenu){
			this.controller.popToRootView();
		}
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	public JList getUIList() {
		return UIlist;
	}

	public void setList(JList list) {
		this.UIlist = list;
	}


	
	
}
