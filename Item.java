
/**
 * The item class figures out user inputs about items, stores the description of the key and flashlight, allows the user to add or remove the item by sending information about the inventory back to the Person Class 
 */
public class Item {

   /**
    * prints out a description of the key if the user asks or removes/adds key to inventory by sending this information to the Person class where the inventory arraylist is stored
    * @param String action
    */     
    public static void key(String action) {
        if (action.equals("look")) { 
            String keyDescription = "The key is rusty and old and heavy as you pick it up. Now it's in your inventory.";
            System.out.println(keyDescription);
            Person.nextLine();
        }
        if ((action.equals("take")) ) { 
            Person.addToInventory("key");
        }
        if ((action.equals("drop")) ) { 
            Person.removeFromInventory("key");
        }
    }

    /**
    * prints out a description of the flashlight if the user asks or removes/adds flashlight to inventory by sending this information to the Person class where the inventory arraylist is stored
     * @param String action
     */
    public static void flashlight(String action) {
        if (action.equals("look")) { 
            String flashlightDescription = "The flashlight looks a little dusty, but otherwise it's not in terrible shape.";
            System.out.println(flashlightDescription);
            Person.nextLine();
        }
        if (action.equals("take")) { 
            Person.addToInventory("flashlight");
        }
        if ((action.equals("drop")) ) { 
            Person.removeFromInventory("flashlight");
        }
    }

    /**
     * takes user input that have to do with items sent from the readInputs() method of the Person class, and calls either the key method, flashlight method, or prints out a reason that the command doesn't make sense in the context of the game
     * @param String[] slicedInput
     * @param String userInput
     * @param String currentLocation
     */
    public static void itemInputReader(String[] slicedInput, String userInput, String currentLocation) {
        String action = " ";
        String itemName = " ";
        for ( String i : slicedInput) { 
            if (i.equals("take") || (i.equals("pick")) || (i.equals("grab"))) {
                action = "take";
            }
            if (i.equals("drop") || (i.equals("get rid of")) || (i.equals("leave")) || (i.equals("lose"))) {
                action = "drop";
            }
            if (i.equals("look")) {
                action = "look";
            }
            if (i.equals("flashlight")) {
                itemName = "flashlight";
            }
            if (i.equals("key")) {
                itemName = "key";
            }
        }
        if (itemName.equals("flashlight")) {
            if (currentLocation.equals("music room")) {
                flashlight(action);
            } else if (currentLocation.equals("entrance")){
                System.out.println("There is no " + itemName + " out here.");
            } else if (action.equals("drop")){
                System.out.println("Don't you know you should leave things where you found them, especially in a haunted house. If you want to get rid of the flashlight, go back to where you got it from!");
            } else {
                System.out.println("There is no " + itemName + " in this room.");
            }
            Person.nextLine();
        }
        if (itemName.equals("key")) {
            if (currentLocation.equals("basement")) {
                key(action);
            } else if (currentLocation.equals("entrance")){
                System.out.println("There is no " + itemName + " out here.");
            } 
            else if (action.equals("drop")){
                System.out.println("Don't you know you should leave things where you found them, especially in a haunted house. If you want to get rid of the key, go back to where you got it from!");
            } else { 
                System.out.println("There is no " + itemName + " in this room.");
            }
            Person.nextLine();
        }
    }
}

