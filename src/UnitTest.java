import static org.junit.Assert.*;

import javax.sound.midi.ControllerEventListener;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class UnitTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}



	
	//*********************************** 
	//JUnit Testing
	//***********************************
	
	@Test	
	public void checkChangeRequestCreated(){
			
		 CORootController rootController =  CORootController.setupDummyData(); 
	
		 Baseline bl =  rootController.getBaselinesFromVSCI(rootController.getTrunk().getSCIs().get(0)).get(0);
		 
		 System.out.print(bl);
		 
		 ChangeRequest c  = new ChangeRequest();
		 c.setVersion(bl);
		 c.setProblem("Test Problem");
		 c.setSolution("test solution");
	
		 rootController.print(bl.getChanges());
		 
		 assertTrue(rootController.getChangeRequests().contains(c));
	}

	@Test
	public void checkChangeRequestCompleted(){
		

	}
	

}
