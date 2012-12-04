import java.io.Serializable;
import java.util.*;


public class Trunk implements Serializable {

	private Vector<SCI> scis;
	
	public Trunk() {

		scis = new Vector<SCI>();
	
	}
	
	public void addSCI(SCI sci){
		this.scis.add(sci);
	}
	
	public void removeSCI(SCI sci){
		this.scis.remove(sci);
	}

	public Vector<SCI> getSCIs(){
		return this.scis;
	}
	
	public void print(){
		for(SCI sci: scis){
			sci.print();
		}
	}
	
}
