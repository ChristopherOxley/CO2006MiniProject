import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		 		 
		 ChangeRequest c  = new ChangeRequest();
		 c.setVersion(bl);
		 c.setProblem("Test Problem");
		 c.setSolution("test solution");
	
		 rootController.print(bl.getChanges());
	
		 // If when we return a collection of all change requests, we can safely 
		 // assume that the change request was created correctly.
		 assertTrue(rootController.getChangeRequests().contains(c));
	}
	
	@Test
	public void checkChangeRequestIsApproved(){
		
		CORootController rootController = CORootController.setupDummyData();
		Baseline bl = rootController.getBaselinesFromVSCI(rootController.getTrunk().getSCIs().get(0)).get(0);
		
		 ChangeRequest c  = new ChangeRequest();
		 c.setVersion(bl);
		 c.setProblem("Test Problem");
		 c.setSolution("test solution");
		 
		 Developer dev = rootController.getDevelopers().get(0);
		 
		 SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy"); 
		 Date convDate = null;
		try {
			convDate = dformat.parse("02/09/1984");
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 
		 rootController.approveChangeRequest(c,dev , ChangeRequest.ASSESSMENT_APPROVED, ChangeRequest.PRIORITY_HIGH, convDate);
	
		 
		 // If the collection of approved requests contains the created change 
		 // request the test passes.
		 assertTrue(rootController.getApprovedRequests(dev).contains(c));
	}

	
	
	@Test
	public void checkChangeRequestCompleted(){
	
		CORootController rootController = CORootController.setupDummyData();
		Baseline bl = rootController.getBaselinesFromVSCI(rootController.getTrunk().getSCIs().get(0)).get(0);
		
		 ChangeRequest c  = new ChangeRequest();
		 c.setVersion(bl);
		 c.setProblem("Test Problem");
		 c.setSolution("test solution");
		 
		 Developer dev = rootController.getDevelopers().get(0);
	
		 SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy"); 
		 Date convDate = null;
		try {
			convDate = dformat.parse("02/09/1984");
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 
		 rootController.approveChangeRequest(c,dev , ChangeRequest.ASSESSMENT_APPROVED, ChangeRequest.PRIORITY_HIGH, convDate);
		 rootController.completeChangeRequest(c);
		 
		 System.out.println( c.getDeadlineDate().toString());
		 
		 // If the change requests completion date data member is not null,
		 // the c can be assumed to be completed.
		 assertTrue(c.getCompletionDate()!=null);	 
	}
	
	

}
