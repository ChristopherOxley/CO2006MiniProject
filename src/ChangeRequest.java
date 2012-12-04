import java.io.Serializable;
import java.util.Date;



public class ChangeRequest implements Serializable {

	private Version version; 
	
	private String problem, solution;
	
	private String assessment = null;
	private String priority = null;
	private Developer dev;
	private Date completionDate;
	
	public ChangeRequest(){
	}

	public Version getVersion() {
		return version;
	}

	// When we set the version to a change request, we also set the change 
	// request on the given version.
	public void setVersion(Version version) {
		this.version = version;
		((Baseline)(this.version)).addChange(this);
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	// We only allow the setting of assessment indirectly to help ensure that 
	// there are only 2 strings that can be set.
	public void setAssessmentRejected(){
		this.assessment = "Rejected";
	}
	
	public void setAssessmentApproved(){
		this.assessment = "Approved";
	}
	
	public String getAssessment(){
		return this.assessment;
	}

	public boolean isApproved(){
		return this.getAssessment().equals("Approved");
	}
	
	public Developer getDev() {
		return dev;
	}

	public void setDev(Developer dev) {
		this.dev = dev;
		dev.addChangeRequest(this);
	}

	public void print(){
				
		System.out.println("Version Number: "+ this.version.getVersionNumber());
		System.out.println("Problem: " +this.problem);
		System.out.println("Solution: " +this.solution);
		System.out.println("Priority: "+this.priority);
		System.out.println("Status: " +this.getAssessment());
		if(this.dev != null){
			System.out.println("Assigned to Developer: " + this.dev.getName());
		}else{
			System.out.println("Assigned to Developer: Not Assigned");
		}
		if (this.completionDate == null) {
			System.out.println("Not Completed");
		}else {
			System.out.println("Completed On: "+ this.completionDate.toString());
		}
		System.out.println("");
	}

	public String getPriority() {
		return priority;
	}

	// We only allow the setting of priority indirectly to help ensure that 
	// there are only 3 strings that can be set.
	public void setPriorityHigh(){
		this.priority = "High";
	}
	
	public void setPriorityMedium(){
		this.priority = "Medium";
	}
	
	public void setPriorityLow(){
		this.priority = "Low";
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	
	
}
