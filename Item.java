import java.util.Scanner;

public class Item {
    
    public static Scanner itemInput = new Scanner(System.in);
    
    public static void key(String action) {
        if (action.equals("look")) { 
            String keyDescription = "The key is rusty and old and heavy as you pick it up. Now it's in your inventory.";
            System.out.println(keyDescription);
            String keyAsk = itemInput.nextLine();
            Person.readInputs(keyAsk);
        }
        if ((action.equals("take")) ) { 
            Person.addToInventory("key");
        }
        if ((action.equals("drop")) ) { 
            Person.removeFromInventory("key");
        }
    }

    public static void flashlight(String action) {
        if (action.equals("look")) { 
            String flashlightDescription = "The flashlight looks a little dusty, but otherwise it's not in terrible shape.";
            System.out.println(flashlightDescription);
            String flashlightAsk = itemInput.nextLine();
            Person.readInputs(flashlightAsk);
        }
        if (action.equals("take")) { 
            Person.addToInventory("flashlight");
        }
        if ((action.equals("drop")) ) { 
            Person.removeFromInventory("flashlight");
        }
    }

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
            String wrongLocation = itemInput.nextLine();
            Person.readInputs(wrongLocation);
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
            String wrongLocation = itemInput.nextLine();
            Person.readInputs(wrongLocation);
        }
    }
}

