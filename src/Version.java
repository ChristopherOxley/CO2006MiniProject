import java.io.Serializable;


public class Version implements Serializable {

	private double versionNumber;
	private SCI sci;
	public Version(){
	
	}
	
	public Version(double versionNumber, SCI sci){
		this.setVersionNumber(versionNumber);
		this.setSci(sci);
	}
	
	public void setVersionNumber(double num){
		this.versionNumber = num;
	}
	
	public double getVersionNumber(){
		return this.versionNumber;
	}
	
	public void print(){
		System.out.println(this.versionNumber);
	}

	public SCI getSci() {
		return sci;
	}

	public void setSci(SCI sci) {
		this.sci = sci;
	}
	
}
