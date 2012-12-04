import java.io.*;



public class COFileManager {

	public COFileManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	// Takes any type of object and saves it to the specified filename and 
	// adds the .dat file extension by default
	public static void save(Object o, String filename){
		
		File file = new File(filename+".dat");
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			ObjectOutputStream oos  = new ObjectOutputStream(outputStream);
			oos.writeObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	// Takes the required filename (excluding extension) and loads the file
	// and returns the object serialized there.
	public static Object load(String filename) {
		
		File file = new File(filename+".dat");
		try {
			FileInputStream iStream = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(iStream);
			Object o = ois.readObject();
			return o;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	// Simple check to see if the file exists for any given filename (excluding 
	// extension)
	public static boolean fileExists(String filename){
		File file = new File(filename+".dat");
		return file.exists();
	}

}
