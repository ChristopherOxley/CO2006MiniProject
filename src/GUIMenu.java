

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUIMenu extends JFrame implements ActionListener {

	private	JButton create, assess, complete, exit;
	
	private CORootController controller;
	private JComboBox developersBox;
	
	public GUIMenu() {
		

	}
	public GUIMenu(CORootController controller) {
		this.setBounds(200, 200, 300, 400);
		this.setController(controller);

		this.createUI();
		this.setVisible(true);
	}

	
	private void createUI(){
		
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		
		cp.add(createHeader("New Change Requests"));
		
		create = new JButton("Create");
		create.addActionListener(this);
		cp.add(create);
		
		
		cp.add(createHeader("Assess Change Requests"));

		assess = new JButton("Assess");
		assess.addActionListener(this);
		cp.add(assess);
		
		cp.add(createHeader("Complete Change Requests"));

		String[] devNames = new String[controller.getDevelopers().size()];
		int devIndex = 0;
		for (Developer developer : controller.getDevelopers()) {
			devNames[devIndex] = developer.getName();
			devIndex++;
		}
		
		developersBox = new JComboBox(devNames);
		developersBox.setSelectedIndex(-1);
	    developersBox.addActionListener(this);
		cp.add(developersBox);
		
		complete = new JButton("Complete");
		complete.addActionListener(this);
		cp.add(complete);
		
		
		cp.add(createHeader("Close Application"));

		exit = new JButton("Save & Exit");
		exit.addActionListener(this);
		cp.add(exit);
		
	}
	
	public JLabel createHeader(String title){
		
		JLabel lbl = new JLabel(title);
		lbl.setPreferredSize(new Dimension(this.getWidth() - 60, 30));
		return lbl;
	}
	
	public void setController(CORootController controller){
		
		this.controller = controller;
		
	}
	
	
	
	public CORootController getController(){
		return this.controller;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == exit){
			controller.saveState();
			System.exit(0);
		}
		
		if(e.getSource() == create){
			GUISCIList list = new GUISCIList(this.controller);
			this.controller.pushView(list);
		}
		
		if(e.getSource() == assess){
			if (controller.getPendingChangeRequests().size() == 0) {
				JOptionPane.showMessageDialog(this, "There are no pending change requests");
			}else{
				GUIAssessChange change = new GUIAssessChange(this.controller);
				this.controller.pushView(change);
			}
		}
		
		if (e.getSource()==complete) {
			Developer selectedDeveloper;
			if (developersBox.getSelectedIndex()>=0) {
				 selectedDeveloper = controller.getDevelopers().get(developersBox.getSelectedIndex());
					if (controller.getApprovedRequests(selectedDeveloper).size() == 0) {
						JOptionPane.showMessageDialog(this, "There are no requests to process for this developer");
					}else{
						GUICompleteCR ccr = new GUICompleteCR(this.controller, selectedDeveloper);
						this.controller.pushView(ccr);
					}
			}else{
				JOptionPane.showMessageDialog(this, "Please select a developer");
			}
		}
	}
}
