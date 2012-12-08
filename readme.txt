Version 1.0

// To Compile and Execute
/////////////////////////

Add the src files to an eclipse project and run as "CORootController"
Alternatively, to use the JUnit tests run as "JUnitTest" 


// Please Note
//////////////

The App saves the state ONLY when you press EXIT on the main menu. This is
intentional behavior primarily for testing, although, I acknowledge, if 
present in a shipping product this would not be acceptable behavior.

We load template data on first launch (when there is no serialized objects), this
 is to make manual testing faster and easy to revert to a specific state. If the
 App detects appropriate files to load, the objects (state) are loaded from file
 instead. To reset the state, quit the App and delete the Trunk.dat and Developers.dat
 files that are created in the same folder as the src folder.
 
 The GUI kicks the user to the main menu if there are no more change requests to
 process in a given section.
 
 The GUI prevents users accessing areas where there are no change requests to 
 process for a given section.

 // Assessment Criteria Compliance
 /////////////////////////////////
 
 Client Acceptance Tests

 ** Scenario 1: "Create Change Request" **
 
 Click create, Select an SCI, Select a Version, Add a problem and solution to the
 text boxes. Click save to complete change request. A message box summary will be 
 displayed. The list of versions shown are ONLY baselines and NOT "versions". This 
 can be verified by looking at the dummy data that is created when starting the App.
 
 Perceived compliance 100%
 
 ** scenario 2: ‘assess change request’ **
 
 Select Assess, choose a change request, Decide if you want to approve or reject.
 Fill in Priority, Assign a developer and fill in a date, please observe the 
 correct format. Click save. The form resets to allow you to repeat the process or
 until there are no more requests. At which point it kicks the user to the main 
 menu.
 
 Alternatively, click back to go to the main menu.
 
 Perceived compliance 100%
 
  ** scenario 3: ‘complete change request’ **
 
 Select a developer from the list, if they have change requests you will be allowed
 to proceed to the next view.
 
 Select a change request from the list, when you select complete, it automatically
 inserts todays date (makes logical sense). If there is another request, it gets
 displayed, alternatively you could choose another from the list. If there are no
 more remaining, you will be sent back to the main menu.
 
 Perceived compliance 95-100% as the date allocation is automatic.