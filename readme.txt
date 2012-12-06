Version 1.1

// To compile
/////////////
change to src directory in terminal;
Run: javac CORootController.java

Alternatively, you can add the src folder to a new eclipse project and run 
from inside eclipse.



// To execute
/////////////
Run: java CORootController
I have also enclosed a .jar file for quick testing



// Please note
//////////////

The App saves the state ONLY when you press EXIT on the main menu. This is
intentional behavior primarily for testing, although, I acknowledged if 
present in a shipping product this would not be acceptable behavior.

We load template data on first launch (when there is no serialized objects), this
 is to make manual testing faster and easy to revert to a specific state. If the
 App detects appropriate files to load, the objects (state) are loaded from file
 instead.
 
 The GUI kicks the user to the main menu if there are no more change requests to
 process in a given section.
 


 The GUI prevents users accessing areas where there are no change requests to 
 process for a given section.
