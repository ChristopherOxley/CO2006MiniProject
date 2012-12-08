

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.midi.ControllerEventListener;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.text.html.MinimalHTMLWriter;


public class GUIAssessChange extends JFrame implements ActionListener, ListDataListener {

	private CORootController controller;
	private JComboBox pendingRequests, developers;
	private JTextArea lblProblem, lblSolution;
	private ButtonGroup priorityButtonGroup, approvalButtonGroup;
	private JRadioButton btnPriorityHigh, btnPriorityMedium, btnPriorityLow, btnAccepted, btnRejected;
	private JButton btnBack, btnSave;
	private JTextField txtDate;
	
	public GUIAssessChange() {
	}
	
	public GUIAssessChange(CORootController controller) {
		this.setBounds(400, 0, 600, 800); // default size upon creation
		this.setController(controller); //allows the GUI interface with the controller
		this.createUI(); // sets up the UI for the JFrame, buttons etc.
	}
	
	private void createUI(){
		
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		// The combo box uses an array of Strings to display the pending change
		// requests, we create this array by using the first 50 chars of each 
		// requests problem attribute.
		String[] reqStub = new String[controller.getPendingChangeRequests().size()];
		int index = 0;
		for (ChangeRequest request : controller.getPendingChangeRequests()) {
			reqStub[index] = request.getProblem().substring(0, Math.min(50, (request.getProblem().length())));
			index++;
		}

			
			cp.add(createHeader("Select a Change Request:"));

			
		    pendingRequests = new JComboBox(reqStub);
			pendingRequests.setSelectedIndex(-1);
			pendingRequests.addActionListener(this);
		
			
			
			cp.add(pendingRequests);
			
			cp.add(createHeader("Problem Details:"));

			
			lblProblem = new JTextArea();
			lblProblem.setPreferredSize(new Dimension(580, 150));
			cp.add(lblProblem);
		
			cp.add(createHeader("Suggested Solution:"));

			
			lblSolution = new JTextArea();
			lblSolution.setPreferredSize(new Dimension(580, 150));
			cp.add(lblSolution);
			
			cp.add(createHeader("Set Approval:"));

			
			// Setup the approval radio buttons
			btnAccepted = new JRadioButton("Accept");
			btnRejected = new JRadioButton("Reject");
			
			
			
			approvalButtonGroup = new ButtonGroup();
			approvalButtonGroup.add(btnAccepted);
			approvalButtonGroup.add(btnRejected);
			btnAccepted.addActionListener(this);
			btnRejected.addActionListener(this);
			cp.add(btnAccepted);
			cp.add(btnRejected);
			
			cp.add(createHeader("Priority:"));
			
			// Setup the priority radio buttons
			btnPriorityHigh = new JRadioButton("High");
			btnPriorityMedium = new JRadioButton("Med");
			btnPriorityLow = new JRadioButton("Low");
			
			priorityButtonGroup = new ButtonGroup();
			priorityButtonGroup.add(btnPriorityHigh);
			priorityButtonGroup.add(btnPriorityMedium);
			priorityButtonGroup.add(btnPriorityLow);
		
			//btnPriorityMedium.setSelected(true);
			
			cp.add(btnPriorityHigh);
			cp.add(btnPriorityMedium);
			cp.add(btnPriorityLow);
			

			
			// Setup the developers combo box
			cp.add(createHeader("Assign Developer:"));
			developers = new JComboBox();
			
			String[] devNames = new String[controller.getDevelopers().size()];
			int devIndex = 0;
			for (Developer developer : controller.getDevelopers()) {
				devNames[devIndex] = developer.getName();
				devIndex++;
			}
			
		    developers = new JComboBox(devNames);
		    developers.setSelectedIndex(-1);
		    developers.addActionListener(this);
			cp.add(developers);

			// Completion Date
			cp.add(createHeader("Completion Date: dd/mm/yyyy"));
			txtDate = new JTextField();
			txtDate.setPreferredSize(new Dimension(100, txtDate.getPreferredSize().height));
			
			cp.add(txtDate);

			cp.add(createHeader(" "));

			// Create back button
			btnBack = new JButton("Back");
			btnBack.addActionListener(this);
			cp.add(btnBack);
			
			
			// Create save button
			btnSave = new JButton("Save");
			btnSave.addActionListener(this);
			cp.add(btnSave);
			
			// Hide optional components
			this.btnPriorityHigh.setEnabled(false);
			this.btnPriorityMedium.setEnabled(false);
			this.btnPriorityLow.setEnabled(false);
			this.developers.setEnabled(false);
			this.txtDate.setEnabled(false);
	}
	
	public JLabel createHeader(String title){
		
		JLabel lbl = new JLabel(title);
		lbl.setPreferredSize(new Dimension(this.getWidth() - 60, 30));
		return lbl;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.pendingRequests) {

			String problemText = controller.getPendingChangeRequests().get(this.pendingRequests.getSelectedIndex()).getProblem();
			String solutionText = controller.getPendingChangeRequests().get(this.pendingRequests.getSelectedIndex()).getSolution();

			lblProblem.setText(problemText);
			lblSolution.setText(solutionText);
			
			controller.print(controller.getPendingChangeRequests());
			
		}
		
		if (e.getSource() == this.btnAccepted) {
			this.btnPriorityHigh.setEnabled(true);
			this.btnPriorityMedium.setEnabled(true);
			this.btnPriorityLow.setEnabled(true);
			this.developers.setEnabled(true);
			this.txtDate.setEnabled(true);
		}
		if (e.getSource() == this.btnRejected) {
			this.btnPriorityHigh.setEnabled(false);
			this.btnPriorityMedium.setEnabled(false);
			this.btnPriorityLow.setEnabled(false);
			this.developers.setEnabled(false);
			this.txtDate.setEnabled(false);
		}
		
		if (e.getSource() == this.btnBack) {
			controller.popView();
		}
		
		if (e.getSource() == this.btnSave) {
			
			// Check that change request is selected
			
			StringBuffer errorString = new StringBuffer("Error: \n");
			boolean errorOcurred = false;
			if (this.pendingRequests.getSelectedIndex() < 0) {
				errorOcurred = true;
				errorString.append("Please select a change request.\n");
			}
			
			// check if a priority is selected
			if (this.btnPriorityHigh.isSelected() == false 
					&& this.btnPriorityMedium.isSelected() == false
					&& this.btnPriorityLow.isSelected() == false && this.btnAccepted.isSelected()) {
				errorOcurred = true;
				errorString.append("Please select a priority.\n");
			}
			
			// check if approved or rejected
			if (this.btnAccepted.isSelected() == false && this.btnRejected.isSelected() == false) {
				errorOcurred = true;
				errorString.append("Please approve or reject the change request.\n");
			}
			// check if a developer is selected
			if (this.developers.getSelectedIndex() < 0 && this.btnAccepted.isSelected()) {
				errorOcurred = true;
				errorString.append("Please assign a developer.\n");
			}
			
			if (errorOcurred) {
				JOptionPane.showMessageDialog(this, errorString.toString());
			}else{
				
				ChangeRequest request = controller.getPendingChangeRequests().get(pendingRequests.getSelectedIndex());
				String priority=null;
				String approval;
				Developer developer=null;
				Date deadlineDate = null;
				request.print();
				
				if (btnAccepted.isSelected()) {
					developer = controller.getDevelopers().get(developers.getSelectedIndex());
					request.setDev(developer);
					developer.addChangeRequest(request);
					if (btnPriorityHigh.isSelected()) {
						priority = ChangeRequest.PRIORITY_HIGH;
					}
					if (btnPriorityMedium.isSelected()) {
						priority = ChangeRequest.PRIORITY_MEDIUM;
					}
					if (btnPriorityLow.isSelected()) {
						priority = ChangeRequest.PRIORITY_LOW;
					}

				    SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
				    
				    try {
						deadlineDate = dformat.parse(txtDate.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					} 
					
					approval = ChangeRequest.ASSESSMENT_APPROVED;

				}else {
					approval = ChangeRequest.ASSESSMENT_REJECTED;

				}
				
				controller.approveChangeRequest(request, developer, approval, priority, deadlineDate);

				//testing
				request.print();
				
				Container cp = this.getContentPane();
				for (Component c : cp.getComponents()) {
					cp.remove(c);
					c=null;
				}
				this.repaint();
				this.createUI();
				this.setVisible(true);
				request.print();
				
			}
			
			if (controller.getPendingChangeRequests().size() == 0) {
				JOptionPane.showMessageDialog(this, "There are no pending change requests");
				controller.popView();
			}
			
			// present error if not filled correctly
			
		}
	}
	
	
	

	public void save(){
		
	}
	
	public CORootController getController() {
		return controller;
	}

	public void setController(CORootController controller) {
		this.controller = controller;
	}


	@Override
	public void contentsChanged(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	


}
