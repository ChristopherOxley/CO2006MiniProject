
import static org.junit.Assert.*;

import java.util.*;
import javax.swing.*;

import org.junit.Test;


public class CORootController {
	
	// Main method, checks if files exist that represent serialized objects. If they do,
	// we load them, otherwise we create some test data which will be saved on exit.
	public static void main(String[] args) {
	
		String developersFileNameString = "developers";
		String trunkFileNameString = "trunk";
		
		CORootController controller;
		
		// Check if files exist, if so load otherwise load dummy data
		if (COFileManager.fileExists(developersFileNameString) && 
				COFileManager.fileExists(trunkFileNameString)) {
			
			controller = new CORootController((Trunk)COFileManager.load(trunkFileNameString)) ;
			controller.setDevelopers((Vector<Developer>)COFileManager.load(developersFileNameString));
		}else{
			 controller = CORootController.setupDummyData();
		}
		
		controller.print(controller.getApprovedRequests(controller.getDevelopers().get(0)));
		
		
		GUIMenu menu = new GUIMenu(controller);
		controller.getViewStack().add(menu);		
	}
	
	

	
	
	
	// The view stack stores JFrames as they are created, this allows us to 
	// programmatically manage each "View" hiding previous views, showing next
	// and even jumping to the first view in the stack and automatically dispose
	// of unneeded views.
	private Vector<JFrame> viewStack;
	private Trunk trunk;
	private Vector<Developer> developers;
	
	public CORootController(){
	}
	
	public CORootController(Trunk trunk){
		setTrunk(trunk);
		setViewStack(new Vector<JFrame>());
		setDevelopers(new Vector<Developer>());
	}
	
	
	
	// Class method used purely to setup a dummy controller
	public static CORootController setupDummyData() {
		
		
		Trunk trunk = new Trunk();	
		
		// Create a new SCI and add dummy versions
		SCI sci1 = new SCI();
		sci1.setName("My SCI");
		trunk.addSCI(sci1);
		
		Version v11 = new Version(1.1, sci1);
		sci1.addVersion(v11);
		
		Version v12 = new Version(1.2, sci1);
		sci1.addVersion(v12);
		
		Baseline v13 = new Baseline(1.3, sci1);
		sci1.addVersion(v13);
		
		Version v14 = new Version(1.4, sci1);
		sci1.addVersion(v14);
		
		Baseline v15 = new Baseline(1.5, sci1);
		sci1.addVersion(v15);
		
		
		// Create a new SCI and add dummy versions

		SCI sci2 = new SCI();
		sci2.setName("My Other SCI");
		trunk.addSCI(sci2);
		
		Baseline v101 = new Baseline(1.01, sci2);
		sci2.addVersion(v101);
		
		Version v131 = new Version(1.31, sci2);
		sci2.addVersion(v131);
		
		Baseline v156 = new Baseline(1.56, sci2);
		sci2.addVersion(v156);
		
		Version v213 = new Version(2.13, sci2);
		sci2.addVersion(v213);
		
		// Create dummy change requests for given versions
		ChangeRequest cRequest = new ChangeRequest();
		cRequest.setVersion(v101);
		cRequest.setProblem("Some stupid problem");
		cRequest.setSolution("Fix the damn thing");
		
		ChangeRequest anotherRequest = new ChangeRequest();
		anotherRequest.setVersion(v13);
		anotherRequest.setProblem("How did that get in there?");
		anotherRequest.setSolution("Sack the newbie");
		
		ChangeRequest problemCentral = new ChangeRequest();
		problemCentral.setVersion(v15);
		problemCentral.setProblem("Shit hit the fan...");
		problemCentral.setSolution("Call superman");
		
		// create a new controller for the dummy trunk readyt to return
		CORootController controller = new CORootController(trunk);
		
		// Add developers to the controller for assigning change requests to.
		Developer dev1 = new Developer("Chris");
		Developer dev2 = new Developer("Steve");
		Developer dev3 = new Developer("John");
		
		controller.addDeveloper(dev1);
		controller.addDeveloper(dev2);
		controller.addDeveloper(dev3);
		
		
		return controller;
	}
	
	
	public  Vector<SCI> getListOfSCIs(Trunk trunk){
		return trunk.getSCIs();
	}
	
	// Returns ALL baselines for a given SCI
	public  Vector<Baseline> getBaselinesFromVSCI(SCI sci){
			
		Vector<Baseline> baselines = new Vector<Baseline>();
		for(Version v: sci.getVersions()){
			// Only add a version if the version's class matches "Baseline"
			if(v.getClass() == Baseline.class){
				baselines.add((Baseline)v);
			}			
		}		
		return baselines;
	}

	
	public Trunk getTrunk() {
		return trunk;
	}

	public void setTrunk(Trunk trunk) {
		this.trunk = trunk;
	}

	// Adding a JFrame to the viewStack 
	public void pushView(JFrame frame){
		// Hide the last frame, add the new one and show it.
		this.getViewStack().get(getViewStack().size()-1).setVisible(false);
		this.getViewStack().add(frame);
		frame.setVisible(true);
	}
	
	// Remove the topmost JFrame form the viewStack and show the previous one.
	public void popView(){
		
		JFrame lastView = this.getViewStack().get(this.getViewStack().size()-1);
		this.getViewStack().remove(lastView);
		lastView.dispose();
		JFrame newLastView = this.getViewStack().get(this.getViewStack().size()-1);
		newLastView.setVisible(true);
	}
	
	
	// Remove all but the first JFrame in the viewStack and make it visible
	public void popToRootView(){
	
		while(this.getViewStack().size() > 1){	
			JFrame lastView = this.getViewStack().get(this.getViewStack().size()-1);
			this.getViewStack().remove(lastView);
			lastView.dispose();			
		}
		this.getViewStack().get(0).setVisible(true);
	}
	
	
	public Vector<JFrame> getViewStack() {
		return viewStack;
	}

	public void setViewStack(Vector<JFrame> viewStack) {
		this.viewStack = viewStack;
	}
	
	// Return all the change requests for every SCI in the trunk
	public Vector<ChangeRequest> getChangeRequests(){
		
		Trunk trunk = this.getTrunk();
		Vector<SCI> scis = this.getListOfSCIs(trunk);
		Vector<ChangeRequest> to_return = new Vector<ChangeRequest>();
		
		for(SCI sci : scis){	
		Vector<Version> versions = sci.getVersions();
			for(Version v: versions){
				if(v.getClass() == Baseline.class){
					Baseline b = (Baseline)v;
					Vector<ChangeRequest> requests = b.getChanges();
					for(ChangeRequest r: requests){
						to_return.add(r);
					}		
				}	
			}
		}
		return to_return;
	}
	
	// Return only the change requests that are "pending"
	public Vector<ChangeRequest> getPendingChangeRequests() {
		
		// Get ALL requests first, then iterate over each one and return a vector
		// for each change request that has a "null" assessment, that is it has 
		// not been accepted or rejected.
		Vector<ChangeRequest> allRequests = getChangeRequests();
		Vector<ChangeRequest> to_return = new Vector<ChangeRequest>();
		for (ChangeRequest changeRequest : allRequests) {
			if (changeRequest.getAssessment() == null) {
				to_return.add(changeRequest);
			}
		}
		return to_return;
	}
	
	public Vector<ChangeRequest> getApprovedRequests(Developer dev){
		
		Vector <ChangeRequest> allRequests = getChangeRequests();
		Vector<ChangeRequest> to_return = new Vector<ChangeRequest>();
		
		for (ChangeRequest changeRequest : allRequests) {
			if (changeRequest.getAssessment()!=null && 
					changeRequest.isApproved() &&
					changeRequest.getCompletionDate() == null &&
					changeRequest.getDev().getName().equals(dev.getName())) {
				to_return.add(changeRequest);
			}
		}
		return to_return;
	}
	
	
	void print(Vector<ChangeRequest> requests){
		for(ChangeRequest c: requests){
			c.print();
		}
	}

	public Vector<Developer> getDevelopers() {
		return developers;
	}

	public ChangeRequest createChangeRequest(String problem, String solution, Baseline bl){
		
		ChangeRequest request = new ChangeRequest();
		request.setProblem(problem);
		request.setSolution(solution);
		request.setVersion(bl);
		return request;
		
	}
	
	public ChangeRequest approveChangeRequest(ChangeRequest req, Developer dev, String assess, String priority, Date deadlineDate ){
	
		req.setAssessment(assess);
		if (dev!=null) req.setDev(dev);
		if (priority!=null) req.setPriority(priority);
		if (deadlineDate!=null)req.setDeadlineDate(deadlineDate);
		return req;
	}
	
	public ChangeRequest completeChangeRequest(ChangeRequest request){
		
		request.setCompletionDate(new Date());
		
		return request;
		
	}
	
	public void setDevelopers(Vector<Developer> developers) {
		this.developers = developers;
	}
	
	public void addDeveloper(Developer developer) {
		this.developers.add(developer);
	}
	
	// Convenience method to save the state of the trunk, we also save the developers
	// as conceptually they do not belong as part of the trunk.
	public void saveState(){
		
		COFileManager.save(this.getDevelopers(), "developers");
		COFileManager.save(this.getTrunk(), "trunk");
		
	}
	
}
