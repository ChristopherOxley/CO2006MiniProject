import java.io.Serializable;
import java.util.*;


public class Baseline extends Version implements Serializable{

	private Vector<ChangeRequest> changes; 
	public Baseline(){	
	}
	
	public Baseline(double versionNumber, SCI sci){
		super(versionNumber, sci);
		changes = new Vector<ChangeRequest> ();
	}
	
	public Vector<ChangeRequest> getChanges(){
		return this.changes;
	}
	
	public void addChange(ChangeRequest c){
		this.changes.add(c);
	}
	
	public void removeChange(ChangeRequest c){
		this.changes.remove(c);
	}

	
}
