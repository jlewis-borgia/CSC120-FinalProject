import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ghost {

    public static Scanner ghostInput = new Scanner(System.in);
    static int terraceGhostCounter = 0;
    static int libraryGhostCounter;
    static int basementGhostCounter;
    public static ArrayList<String> movements = new ArrayList<>(List.of("go", "move", "walk", "jump", "climb"));

    public static void terraceGhost() {
        terraceGhostCounter += 1;
        if (terraceGhostCounter == 1) {
            System.out.println("A ghost rises from the floor. It rises from the floor, its decayed arms reaching out to you. It starts moving towards you.");
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
                System.out.println("You needed to act quickly. You killed by the ghost.");
                System.out.println("End Game.");
                System.out.println("--------------------");
                System.out.println("--------------------");    
            } else {
                Person.readInputs(terraceGhostLine);    
            }
        }
        if (terraceGhostCounter > 1) { 
            System.out.println("The ghost has been waiting for you to come back. It surges at you at full speed. There's no time for you to escape.");
            System.out.println("You are killed by the ghost.");
            System.out.println("End Game.");
            System.out.println("--------------------");
            System.out.println("--------------------");
            return;
        }
    }

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
            System.out.println("You have found light. Now you can see the key to your escape.");
        }
        String libraryGhostLine = ghostInput.nextLine();
        Person.readInputs(libraryGhostLine);
    }

    public static void basementGhost(ArrayList<String> inventory) {
        basementGhostCounter += 1;
        if (inventory.contains("flashlight") == false){
            System.out.println("You here a high pitched screaming noise. Whirling around you see a horrifying apparition before you. It reaches its hands out and crawls towards you, huge and beastlike." + "'There you are " + Person.name +  "', it says, it's voice a deep and terrifying growl. You scream and try to climb back up the stairs but the creature is too fast." );
            System.out.println("You are killed by the ghost.");
            System.out.println("End Game.");
            System.out.println("--------------------");
            System.out.println("--------------------");
            return;
        } else if (inventory.contains("flashlight")) {
            System.out.println("You here a high pitched screaming noise. Whirling around you see a horrifying apparition before you. It reaches its hands out and crawls towards you, huge and beastlike." + "'There you are " + Person.name +  "', it says, it's voice a deep and terrifying growl. You scream and try to climb back up the stairs but the creature is too fast. But just as it's about to get you, you shine the flashlight in it's face. The creature cries out in pain, the light blinding it, it writhes around on the basement floor, until finally it fades too dust.");
        }
        String basementGhostLine = ghostInput.nextLine();
        Person.readInputs(basementGhostLine);
    }


}
