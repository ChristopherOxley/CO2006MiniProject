import java.io.Serializable;
import java.util.*;


public class SCI implements Serializable{

	private Vector <Version> versions; 
	private Version first;
	private String name;
	
	public SCI(){
		
		versions = new Vector<Version>();
		
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setFirst(Version v){
		this.first = v;
	}
	
	public Version getFirst(Version v){
		return this.first;
	}
	
	public void addVersion(Version v){
		this.versions.add(v);
	}
	
	public void removeVersion(Version v){
		this.versions.remove(v);
	}
	
	public Vector<Version> getVersions(){
		return this.versions;
	}
	
	public void print(){
		System.out.println(this.name);
		System.out.println("First version:");
		
		if(this.first != null){
			this.first.print();
		}
		
		System.out.println("All versions:");
		for(Version v: versions){
			v.print();
		}
		System.out.println("\n");
	}
	
}
