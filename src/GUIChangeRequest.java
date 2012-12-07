

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GUIChangeRequest extends JFrame implements ActionListener, WindowListener {

	private CORootController controller;
	private Baseline baseline;
	private JButton btnBack, btnMainMenu, btnSave;
	private JTextArea txtProblem, txtSolution;
	
	
	public GUIChangeRequest() {
		// TODO Auto-generated constructor stub
	}
	

	public GUIChangeRequest(CORootController controller, Baseline baseline) {
		
		this.setController(controller);
		this.setBaseline(baseline);
		
		this.setBounds(200, 200, 600, 480);
		this.addWindowListener(this);

		this.createUI();
		
		// TODO Auto-generated constructor stub
	}
	
	private void createUI(){
		
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		
		txtProblem = new JTextArea();
		txtProblem.setWrapStyleWord(true);
		txtProblem.setLineWrap(true);
		txtProblem.setText("Please state the problem");
		
		JScrollPane problemScrollPane = new JScrollPane(txtProblem);
		problemScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		problemScrollPane.setPreferredSize(new Dimension(580, 200));
		cp.add(problemScrollPane);

		txtSolution = new JTextArea();
		txtSolution.setWrapStyleWord(true);
		txtSolution.setLineWrap(true);
		txtSolution.setText("Please state the proposed solution");
		
		JScrollPane solutionScrollPane = new JScrollPane(txtSolution);
		solutionScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		solutionScrollPane.setPreferredSize(new Dimension(580, 200));
		cp.add(solutionScrollPane);

		
		 btnBack = new JButton("Back");
		 btnBack.addActionListener(this);
		cp.add(btnBack);

		 btnSave = new JButton("Save");
		 btnSave.addActionListener(this);
		cp.add(btnSave);
		
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
			this.controller.popView();
		}
		
		if (e.getSource() == btnMainMenu){
			this.controller.popToRootView();
		}
		
		if(e.getSource() == btnSave){
		
			String problem = this.txtProblem.getText();
			String solution = this.txtSolution.getText();
			Baseline bl = this.baseline;
			
			ChangeRequest request = controller.createChangeRequest(problem, solution, bl);
			

			StringBuffer sb = new StringBuffer();
			
			sb.append("Suncessfully Created A New Change Request:\n");
			sb.append("SCI: "+ baseline.getSci().getName()+"\n");
			sb.append("Version:"+baseline.getVersionNumber()+"\n");
			sb.append("Requested Change: " + request.getProblem()+"\n");
			sb.append("Suggested Solution: " + request.getSolution()+"\n");
			JOptionPane.showMessageDialog(null, sb.toString());
						
			this.controller.getChangeRequests();
			this.controller.popToRootView();
		}
	}


	public CORootController getController() {
		return controller;
	}


	public void setController(CORootController controller) {
		this.controller = controller;
	}


	public Baseline getBaseline() {
		return baseline;
	}


	public void setBaseline(Baseline baseline) {
		this.baseline = baseline;
	}




}
