import java.util.ArrayList;
import java.util.Scanner;

/**
 * superclass that establishes basic info of a building
*/
public class Room {
    
    private String name;
    private String whatFloor;
    private ArrayList<String> items;
    private ArrayList<String> accessibleRooms; 
    public static Scanner roomInput = new Scanner(System.in);
    

    public Room(String name, String whatFloor) { 
        this.name = name;
    }

    public static void Entrance(String location) {
        String entranceDescription = "The mansion is to the north of you, grounds to the east and west, and a closed gate to the south.";
        if (location == "N/A") {
            System.out.println("Entrance:");
            System.out.println(entranceDescription);
        }
        if (location == "look around") {
            System.out.println("Entrance:");
            System.out.println(entranceDescription);
        }
        if (location == "north"){ 
            String northEntranceDescription = "To the north is the gothic mansion you are moving in to. Beautiful yet gloomy looking, the windows are shut and the front door is straight ahead.";
            System.out.println(northEntranceDescription);
        }
        if (location == "east") {
            String eastEntranceDescription = "The grounds to the east are made up of woods. The plant life is thick and overgrown, and so dark you can see hardly anything. It gives you the chills.";
            System.out.println(eastEntranceDescription);
        }
        if (location == "west") {
            String westEntranceDescription = "An old garden overrun with weeds lays here, with a large fountain no longer running with water. Past this are dark overgrown woods.";
            System.out.println(westEntranceDescription);
        }
        if (location == "south") {
            String southEntranceDescription = "Turning behind you to the south, the cab you have taken has driven away and the huge iron gates have shut behind you.";
            System.out.println(southEntranceDescription);
        }
        String entranceAsk = roomInput.nextLine();
        Person.readInputs(entranceAsk);
    }

    public static void entranceHall(String location) {
        String entranceHallDescription = "This room is dark and gloomy, it definitely needs a remodel. It's a huge space but dimly lit with cobwebs hanging from the walls. There's a large carved wooden door to the north, two slighter smaller doors, one to the east and one to the west, and a large door to the south.";
        if (location == "N/A") {
            System.out.println("Entrance Hall:");
            System.out.println(entranceHallDescription);
        }
        if (location == "look around") {
            System.out.println("Entrance Hall:");
            System.out.println(entranceHallDescription);
        }
        if (location == "north"){ 
            String northEntranceHallDescription = "To the north is there's a large carved wooden door in this direction.";
            System.out.println(northEntranceHallDescription);
        }
        if (location == "east") {
            String eastEntranceHallDescription = "To the east is a smaller door covered with cobwebs. ";
            System.out.println(eastEntranceHallDescription);
        }
        if (location == "west") {
            String westEntranceHallDescription = "To the west there's a smaller door covered with cobwebs. It's especially dark over there.";
            System.out.println(westEntranceHallDescription);
        }
        if (location == "south") {
            String southEntranceHallDescription = "A huge heavy door is to the south of you.";
            System.out.println(southEntranceHallDescription); 
        }
        String entranceHallAsk = roomInput.nextLine(); 
        Person.readInputs(entranceHallAsk);
    }

    public static void diningRoom(String location) {
        String diningRoomDescription = "This room is nearly pitchblack. When you move closer to see what's inside, you see there's a long wooden table with blood spattered against the table top. Huge claw marks tear open a tapestry on the far wall. There are doors to the north, south, and east.";
        if (location == "N/A") {
            System.out.println("Dining Room:");
            System.out.println(diningRoomDescription);
        }
        if (location == "Look around") {
            System.out.println("Library:");
            System.out.println(diningRoomDescription);
        }
        if (location == "north"){ 
            String northdiningRoomDescription = "There's a door in this direction, but you have to walk past the bloody table and the torn tapestry to get it.";
            System.out.println(northdiningRoomDescription);
        }
        if (location == "east") {
            String eastdiningRoomDescription = "There's a door in this direction.";
            System.out.println(eastdiningRoomDescription);
        }
        if (location == "west") {
            String westdiningRoomDescription = "The torn tapestry adorns this wall.";
            System.out.println(westdiningRoomDescription);
        }
        if (location == "south") {
            String southdiningRoomDescription = "There's a door in this direction.";
            System.out.println(southdiningRoomDescription); 
        }
        String diningRoomAsk = roomInput.nextLine(); 
        Person.readInputs(diningRoomAsk);
    }

    public static void library(String location) {
        String libraryDescription = "This room is huge, full of huge shelves of dusty old books. You can hardly see anything. Squinting, you see that there's a door to the north and a door to the west.";
        if (location == "N/A") {
            System.out.println("Library:");
            System.out.println(libraryDescription);
        }
        if (location == "Look around") {
            System.out.println("Library:");
            System.out.println(libraryDescription);
        }
        if (location == "north"){ 
            String northLibraryDescription = "To the north beside more bookshelves there's a door coated in cobwebs.";
            System.out.println(northLibraryDescription);
        }
        if (location == "east") {
            String eastLibraryDescription = "To the east are shelves and shelves of books, it's so dark you can't see down to the end of the rows of books.";
            System.out.println(eastLibraryDescription);
        }
        if (location == "west") {
            String westLibraryDescription = "There's a door this way full of cobwebs.";
            System.out.println(westLibraryDescription);
        }
        if (location == "south") {
            String southLibraryDescription = "To the west are shelves and shelves of books, it's so dark you can't see down to the end of the rows of books.";
            System.out.println(southLibraryDescription); 
        }
        String libraryAsk = roomInput.nextLine();
        Person.readInputs(libraryAsk);
    }

    public static void kitchen(String location) {
        String kitchenDescription = "You are in a dingy kitchen. The surfaces are covered with dirt. A window is nailed tightly shut. There's doorway to the east and a small door open to a rickety staircase down.";
        if (location == "N/A") {
            System.out.println("Kitchen:");
            System.out.println(kitchenDescription);
        }
        if (location == "Look around") {
            System.out.println("Kitchen:");
            System.out.println(kitchenDescription);
        }
        if (location == "north"){ 
            String northkitchenDescription = "To the north is a row of cabinets with their doors hanging off the hinges loosely.";
            System.out.println(northkitchenDescription);
        }
        if (location == "east") {
            String eastkitchenDescription = "To the east there's a door.";
            System.out.println(eastkitchenDescription);
        }
        if (location == "west") {
            String westkitchenDescription = "There's a window nailed shut.";
            System.out.println(westkitchenDescription);
        }
        if (location == "south") {
            String southkitchenDescription = "There's a bunch of cabinets and an old table.";
            System.out.println(southkitchenDescription); 
        }
        if (location == "down") {
            String downkitchenDescription = "There's a small door open to a rickety staircase going down.";
            System.out.println(downkitchenDescription); 
        }
        String kitchenAsk = roomInput.nextLine();
        Person.readInputs(kitchenAsk);
    }

    public static void musicRoom(String location) {
        String musicRoomDescription = "You are in the music room. Old instruments line the walls, a grand piano is in the center. As you enter the piano begins to play ominously. There's a door to the west and another to the south.";
        if (location == "N/A") {
            System.out.println("Music Room:");
            System.out.println(musicRoomDescription);
        }
        if (location == "Look around") {
            System.out.println("Music Room:");
            System.out.println(musicRoomDescription);
        }
        if (location == "north"){ 
            String northMusicRoomDescription = "To the north are string instruments, cellos, violoas and violins.";
            System.out.println(northMusicRoomDescription);
        }
        if (location == "east") {
            String eastMusicRoomDescription = "Big curtains hang from the walls and the grand piano is in this direction. When you look at it it starts playing louder.";
            System.out.println(eastMusicRoomDescription);
        }
        if (location == "west") {
            String westMusicRoomDescription = "There's a door this way.";
            System.out.println(westMusicRoomDescription);
        }
        if (location == "south") {
            String southMusicRoomDescription = "There's a door this way.";
            System.out.println(southMusicRoomDescription); 
        }
        String musicRoomAsk = roomInput.nextLine();
        Person.readInputs(musicRoomAsk);
    }

    public static void terrace(String location) {
        String terraceDescription = "You are on the terrace. You look out to see the gloomy and dark grounds all around the mansion. There are doors to the south and the east.";
        if (location == "N/A") {
            System.out.println("Terrace:");
            System.out.println(terraceDescription);
        }
        if (location == "Look around") {
            System.out.println("Terrace:");
            System.out.println(terraceDescription);
        }
        if (location == "north"){ 
            String northTerraceDescription = "To the north are the steps down from the mansion into the grounds.";
            System.out.println(northTerraceDescription);
        }
        if (location == "east") {
            String eastTerraceDescription = "There's a door to the east.";
            System.out.println(eastTerraceDescription);
        }
        if (location == "west") {
            String westTerraceDescription = "Nothing in this direction except more expanse of the terrace.";
            System.out.println(westTerraceDescription);
        }
        if (location == "south") {
            String southTerraceDescription = "To the south is a doorway.";
            System.out.println(southTerraceDescription); 
        }
        String terraceAsk = roomInput.nextLine();
        Person.readInputs(terraceAsk);
    }

    public static void basement(String location) { 
        String basementDescription = "The basement is dark and dingy, you can't see anything in any direction. You just know that right behind you is the stairs back up. You hear a shrieking noise echoing through the room.";
        String shortBasementDescription = "The basement is dark and dingy, you can't see anything in any direction.";
        if (location == "N/A") {
            System.out.println("Basement:");
            System.out.println(basementDescription);
        }
        if (location == "Look around") {
            System.out.println("Basement:");
            System.out.println(shortBasementDescription);
        }
        if (location == "north"){ 
            System.out.println(shortBasementDescription);
        }
        if (location == "east") {
            System.out.println(shortBasementDescription);
        }
        if (location == "west") {
            System.out.println(shortBasementDescription);
        }
        if (location == "south") {
            System.out.println(shortBasementDescription); 
        }
        if (location == "up") {
            String upBasementDescription = "A rickety staircase leads back up.";
            System.out.println(upBasementDescription);
        }
        String basementAsk = roomInput.nextLine();
        Person.readInputs(basementAsk);
    }
    
}

