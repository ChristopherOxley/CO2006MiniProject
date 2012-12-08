

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class GUICompleteCR extends JFrame implements ActionListener {

	private CORootController controller;
	private Developer developer;
	private JButton btnBack, btnComplete;
	private JComboBox approvedRequestsBox;
	
	public GUICompleteCR() {
	}

	public GUICompleteCR(CORootController controller, Developer developer) {
		this.setBounds(400, 0, 600, 800);
		setController(controller);
		setDeveloper(developer);
		createUI();

	}
	
	private void createUI(){
	
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		String[] reqStub = new String[controller.getApprovedRequests(this.developer).size()];
		int index = 0;
		for (ChangeRequest request : controller.getApprovedRequests(this.developer)) {
			reqStub[index] = request.getProblem().substring(0, Math.min(50, (request.getProblem().length())));
			index++;
		}
		
		cp.add(createHeader("Select a Change Request:"));

		
		approvedRequestsBox = new JComboBox(reqStub);
		approvedRequestsBox.setSelectedIndex(0);
		approvedRequestsBox.addActionListener(this);
	
		
		
		
		cp.add(approvedRequestsBox);
		
		cp.add(createHeader("\"complete\" assigns the selected CR complete with current date and time"));

		
		// Create back button
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		cp.add(btnBack);
		
		// Create back button
		btnComplete = new JButton("Complete");
		btnComplete.addActionListener(this);
		cp.add(btnComplete);

		
	}

	
	public JLabel createHeader(String title){
		
		JLabel lbl = new JLabel(title);
		lbl.setPreferredSize(new Dimension(this.getWidth() - 60, 30));
		return lbl;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnBack) {
			controller.popView();
		}

		
		if (e.getSource() == btnComplete) {
			
			if (approvedRequestsBox.getSelectedIndex() >= 0) {
				ChangeRequest selectedRequest = controller.getApprovedRequests(developer).get(approvedRequestsBox.getSelectedIndex());
				selectedRequest.print();
				controller.completeChangeRequest(selectedRequest);
				selectedRequest.print();
			}

		if (controller.getApprovedRequests(developer).size() == 0) {
			
			JOptionPane.showMessageDialog(this, "No more requests to complete");
			controller.popToRootView();
		}else {
			
			Container cp = this.getContentPane();
			for (Component c : cp.getComponents()) {
				cp.remove(c);
				c=null;
			}
			
			this.repaint();
			this.createUI();
			this.setVisible(true);
			
		}
			

		
		}
		
	}

	public CORootController getController() {
		return controller;
	}

	public void setController(CORootController controller) {
		this.controller = controller;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

}
