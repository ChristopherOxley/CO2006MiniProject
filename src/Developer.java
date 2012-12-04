import java.io.Serializable;
import java.util.*;


public class Developer implements Serializable{

	private String name;
	private Vector<ChangeRequest> changeRequests;
	
	
	public Developer() {

	}

	public Developer(String name){
		 changeRequests = new  Vector<ChangeRequest>();

		this.setName(name);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<ChangeRequest> getChangeRequests() {
		return changeRequests;
	}

	public void addChangeRequest(ChangeRequest req){
		this.changeRequests.add(req);
	}
	
	public void removeChangeRequest(ChangeRequest req){
		this.changeRequests.remove(req);
	}
	
	public void print(){
		System.out.println(this.name);
		System.out.println("Number of Change Reguests: " + this.changeRequests.size());
	}

	
}
