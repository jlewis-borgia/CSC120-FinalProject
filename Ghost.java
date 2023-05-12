import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ghost class stores descriptions of the different ghosts in the house and what occurs from different choices made around the ghosts 
 */
public class Ghost {

    public static Scanner ghostInput = new Scanner(System.in);
    static int terraceGhostCounter = 0;
    static int libraryGhostCounter;
    static int basementGhostCounter;
    public static ArrayList<String> movements = new ArrayList<>(List.of("go", "move", "walk", "jump", "climb"));

    /**
     * prints out initial information about the terrace ghost, kills you if you don't leave the ghost 
     */
    public static void terraceGhost() {
        System.out.println("  ");
        terraceGhostCounter += 1;
        if (terraceGhostCounter == 1) {
            System.out.println("A ghost rises from the floor. It rises from the floor, its decayed arms reaching out to you. It starts moving towards you.");
            System.out.println("    ");
            String terraceGhostLine = ghostInput.nextLine();
            terraceGhostLine = terraceGhostLine.toLowerCase();
            String[] slicedInput2 = terraceGhostLine.split(" ");
            int movementsCounter = 0;
            for ( String i : slicedInput2) { 
                if (movements.contains(i)) {
                    movementsCounter += 1;
                }
            }
            if (movementsCounter == 0) { 
                System.out.println("   ");
                System.out.println("You needed to leave quickly! You are killed by the ghost.");
                try {
                    Person.restartUser();
                } catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }                
            } else {
                System.out.println("   ");
                Person.readInputs(terraceGhostLine);    
            }
        }
        if (terraceGhostCounter > 1) { 
            System.out.println("    ");
            System.out.println("The ghost has been waiting for you to come back. It surges at you at full speed. There's no time for you to escape.");
            System.out.println("You are killed by the ghost.");
            try {
                Person.restartUser();
            } catch (InterruptedException e) {
                System.out.println("got interrupted!");
                }               
            return;
        }
    }

    /**
     * prints out information about the library ghost and depending on whether the flashlight is in the inventory or not it will print out different information
     * @param ArrayList<String> inventory
     */
    public static void libraryGhost(ArrayList<String> inventory) {
        libraryGhostCounter += 1;
        if ( (inventory.contains("flashlight") == false) && (libraryGhostCounter == 1) ){
            System.out.println("Levitating out from one of the stacks comes a ghost. It's moving slowly, meandering lazily towards you. You turn to run but instead of it attacking  it opens it's mouth and says in a low voice, almost a whisper:");
            System.out.println(" 'You must use light to escape this place.' ");
        }
        if ( (inventory.contains("flashlight")) && (libraryGhostCounter == 1) ){
            System.out.println("Levitating out from one of the stacks comes a ghost. It's moving slowly, meandering lazily towards you. You turn to run but instead of it attacking  it opens it's mouth and says in a low voice, almost a whisper:");
            System.out.println(" 'You must use light to escape this place.' ");
        }
        if ((inventory.contains("flashlight") == false) && (libraryGhostCounter > 1) ){
            System.out.println("In reentering the room, you see the ghost has been waiting for your return. It says in a low voice:");
            System.out.println(" 'You must use light to escape this place.' ");
        }
        if ((inventory.contains("flashlight")) && (libraryGhostCounter > 1)) {
            System.out.println("In reentering the room, you see the ghost has been waiting for your return. It says in a low voice:");
            System.out.println(" 'You have found light. Now you can see the key to your escape.' ");
        }
        if ((inventory.contains("key"))) { 
            System.out.println("In reentering the room, you see the ghost has been waiting for your return. It says in a low voice:");
            System.out.println(" 'You have all you need. Now run from this place.' ");
        }
        Person.nextLine();
    }

    /**
     * prints information about the basement guest and determines how it acts and whether the user survives
     * @param ArrayList<String> inventory
     * @param int basementFlashlightCounter
     */
    public static void basementGhost(ArrayList<String> inventory, int basementFlashlightCounter) {
        System.out.println("    ");
        String userName = Person.name;
        if (basementFlashlightCounter <= 1) { 
            if (inventory.contains("flashlight") == false){
                System.out.println("You here a high pitched screaming noise. Whirling around you see a horrifying apparition before you. It reaches its hands out and crawls towards you, huge and beastlike." + "'There you are " + Person.name +  "', it says, it's voice a deep and terrifying growl. You scream and try to climb back up the stairs but the creature is too fast." );
                System.out.println("You are killed by the ghost.");
                try {
                    Person.restartUser();
                } catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                    }               
                return;
            } else if (inventory.contains("flashlight")) {
                System.out.println("You here a high pitched screaming noise. Whirling around you see a horrifying apparition before you. It reaches its hands out and crawls towards you, huge and beastlike.");
                System.out.println("'There you are " + userName +  "', it says, its voice a deep and terrifying growl.");
                System.out.println("You scream and try to climb back up the stairs but the creature is too fast. But just as it's about to get you, you shine the flashlight in it's face. The creature cries out in pain, the light blinding it, it writhes around on the basement floor, until finally it fades to dust.");
            }
            Person.nextLine();
        } 
        if (basementFlashlightCounter > 1) { 
            Person.nextLine();
        }
    }


}
