import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Person Class controls the possible actions possible for the user of the game including moving the character around, showing/changing the inventory, and reading user inputs and sending them to the proper methods
 */
public class Person {

    public static String name;
    static String currentLocation = "entrance";
    public static Scanner personInput = new Scanner(System.in);
    static private ArrayList<String> inventory = new ArrayList<>(List.of("backpack"));
    int energyLevel;
    public static ArrayList<String> movements = new ArrayList<>(List.of("go", "move", "walk"));
    public static ArrayList<String> directions = new ArrayList<>(List.of("north", "south", "east", "west", "up", "down"));
    public static ArrayList<String> actions = new ArrayList<>(List.of("pick up", "take", "grab", "pick", "drop", "get rid of", "lose"));
    public static ArrayList<String> allItems = new ArrayList<>(List.of("key", "backpack", "flashlight"));
    public static ArrayList<String> inventoryWords = new ArrayList<>(List.of("use the key", "use key", "take out key", "use the flashlight", "use flashlight", "take out flashlight", "take out the flashlight", "turn on flashlight", "turn on flashlight"));

    /**
     * reads all user inputs and sends inputs to the right methods depending on what the user has types. Also sends an error message when the user inputs a command that is not applicable to the game. 
     * @param String userInput
     */
    public static void readInputs(String userInput) {
        System.out.println("   ");
        if (userInput.equals("HELP")) { 
            printHelp();
            return;
        }
        userInput = userInput.toLowerCase();
        String[] slicedInput = userInput.split(" ");
        for ( String i : slicedInput) { 
            if (i.equals("fight") || i.equals("attack")) { 
                System.out.println("You can't fight!");
                nextLine();
            }
        }
        if ( (userInput.equals("inventory")) || (userInput.equals("look at inventory")) || (userInput.equals("check inventory")) || (userInput.equals("what's in my inventory")) || (userInput.equals("what's in inventory")) || (userInput.equals("print inventory")) || (userInput.equals("show inventory")) || (userInput.equals("show my inventory")) || (userInput.equals("show the inventory"))) {
            printInventory();
            return;
        } 
        if (inventoryWords.contains(userInput)) { 
            inventoryResponse(userInput, slicedInput);
        }
        if ((directions.contains(userInput))) {
            lookAround(slicedInput, userInput);
        }
        int directionCounter = 0;
        int movementsCounter = 0;
        int itemCounter = 0;
        int actionCounter = 0;
        for ( String i : slicedInput) { 
            if (movements.contains(i) || (i.equals("look"))) {
                movementsCounter += 1;
            }
            if (directions.contains(i)) {
                directionCounter += 1;
            }
            if (allItems.contains(i)) {
                itemCounter += 1;
            }
            if (actions.contains(i)) {
                actionCounter += 1;
            }
        } 
        if (allItems.contains(userInput)) {
            if (userInput.equals("key")) {
                if (currentLocation.equals("basement") && (inventory.contains("flashlight"))) { 
                    Item.key("look");
                } else {
                    System.out.println("This isn't a valid command.");
                    nextLine();
                }
            }
            if (userInput.equals("flashlight")) {
                if (currentLocation.equals("music room")) { 
                    Item.key("look");
                } else { 
                    System.out.println("This isn't a valid command.");
                    nextLine();
                }
            }
        }
        if ( ( ((directionCounter == 0) && (movementsCounter == 0) && (itemCounter == 0) ) 
        || (((movementsCounter == 0) && (directionCounter == 0)) && (itemCounter > 0)) && ((userInput.equals("look around") == false) && (actionCounter == 0) ) 
        || ((movementsCounter > 0) && (directionCounter == 0)) && (itemCounter == 0)) && (userInput.equals("look around") == false )  
        || ((((movementsCounter == 0) && (directionCounter > 0)) && (itemCounter == 0)) && (userInput.equals("look around") == false ))) {
            System.out.println("This isn't a valid command.");
            nextLine();
        } else if (movementsCounter > 1) {
            System.out.println("Only use one verb in your commands.");
            nextLine();
        } else if (directionCounter > 1) {
            System.out.println("This isn't a valid command.");
            nextLine();
        } else if (itemCounter > 1) {
            System.out.println("You can't use two items at once!");
            nextLine();
        }
        if (itemCounter > 0) {
            Item.itemInputReader(slicedInput, userInput, currentLocation);
        }
        if (directions.contains(userInput) == false) { 
            for ( String i : slicedInput) { 
                if ((i.equalsIgnoreCase("Look")) && (itemCounter == 0)){
                    lookAround(slicedInput, userInput);
                }
                for ( String j : slicedInput) { 
                    if (movements.contains(j)) { 
                        if (i.equalsIgnoreCase("go") || i.equalsIgnoreCase("walk") || i.equalsIgnoreCase("move")) {
                            if (userInput.contains("north")) {
                                changeLocation(currentLocation, "north");
                                break;
                            }
                            if (userInput.contains("south")) {
                                changeLocation(currentLocation, "south");
                                break;
                            }
                            if (userInput.contains("east")) {
                                changeLocation(currentLocation, "east");
                                break;
                            }
                            if (userInput.contains("west")) {
                                changeLocation(currentLocation, "west");
                                break;
                            }
                            if (userInput.contains("up")) {
                                changeLocation(currentLocation, "up");
                                break;
                            }
                            if (userInput.contains("down")) {
                                changeLocation(currentLocation, "down");
                                break;
                            } 
                        } 
                    }
                } 
            }
        }
    }

    /**
     * changes location by moving north, south, east, west, up or down depending on what's allowed in the room and changing currentLocation variable to reflect this. Also sends error when you can't move in a certain direction
     * @param Sliced[] startingLocation
     * @param String direction
     */ 
     public static void changeLocation(String startingLocation, String direction) {
            if  ( (direction.equals("up")) && (currentLocation.equals("basement") == false) ) { 
                System.out.println("This isn't a valid input.");
                nextLine();
            } else { 
                if (startingLocation.equals("entrance")) {
                    if(direction.equals("north")) {
                        currentLocation = "entrance hall";
                        Room.entranceHall("N/A");
                    }
                    if(direction.equals("south")) {
                        if  (inventory.contains("key") == false) { 
                            System.out.println("You can't leave! The gate has locked behind you and you don't have the key!");
                            nextLine();
                        } else if (inventory.contains("key")) { 
                            System.out.println("The gate is locked. Do you want to see if your key opens it?");
                            String exitInput = personInput.nextLine();
                            exitInput = exitInput.toLowerCase();
                            if ( (exitInput.equals("yes")) || (exitInput.equals("sure")) || (exitInput.equals("okay")) ||(exitInput.equals("ok")) ||(exitInput.equals("try key")) || (exitInput.equals("try the key")) || (exitInput.equals("use the key")) || (exitInput.equals("use key"))  ) {
                                Room.exit();
                            } else if (exitInput.equals("no")) {
                                System.out.println("Ok, if you say so.");
                                nextLine();
                            }
                        }
                    }
                    if(direction.equals("east")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        try {
                            restartUser();
                        } catch (InterruptedException e) {
                            System.out.println("got interrupted!");
                            }                            
                    }
                    if(direction.equals("west")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        try {
                            restartUser();
                        } catch (InterruptedException e) {
                            System.out.println("got interrupted!");
                            }                            
                    }
                }
                if (startingLocation.equals("entrance hall")) {
                    if(direction.equals("north")) {
                        currentLocation = "dining room";
                        Room.diningRoom("N/A");
                    }
                    if(direction.equals("south")) {
                        currentLocation = "entrance";
                        Room.entrance("N/A");
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("west")) {
                        currentLocation = "kitchen";
                        Room.kitchen("N/A");
                    }
                }
                if (startingLocation.equals("dining room")) {
                    if(direction.equals("north")) {
                        currentLocation = "terrace";
                        Room.terrace("N/A");
                    }
                    if(direction.equals("south")) {
                        currentLocation = "entrance hall";
                        Room.entranceHall("N/A");
                    }
                    if(direction.equals("east")) {
                        currentLocation = "library";
                        Room.library("N/A", inventory);
                    }
                    if(direction.equals("west")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                }
                if (startingLocation.equals("library")) {
                    if(direction.equals("north")) {
                        currentLocation = "parlor";
                        Room.parlor("N/A");
                    }
                    if(direction.equals("south")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("west")) {
                        currentLocation = "dining room";
                        Room.diningRoom("N/A");
                    }
                }
                if (startingLocation.equals("kitchen")) {
                    if(direction.equals("north")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("south")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("east")) {
                        currentLocation = "entrance hall";
                        Room.entranceHall("N/A");
                    }
                    if(direction.equals("west")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if (direction.equals("down")) {
                        currentLocation = "basement";
                        Room.basement("N/A", inventory);
                    }
                }
                if (startingLocation.equals("parlor")) {
                    if(direction.equals("north")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("south")) {
                        currentLocation = "library";
                        Room.library("N/A", inventory);
                    }
                    if(direction.equals("east")) {
                        currentLocation = "music room";
                        Room.musicRoom("N/A", inventory);
                    }
                    if(direction.equals("west")) {
                        currentLocation = "parlor";
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                }
                if (startingLocation.equals("music room")) {
                    if(direction.equals("north")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("south")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("west")) {
                        currentLocation = "parlor";
                        Room.parlor("N/A");
                    }
                }
                if (startingLocation.equals("terrace")) {
                    if(direction.equals("north")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        try {
                            restartUser();
                        } catch (InterruptedException e) {
                            System.out.println("got interrupted!");
                        }               
                    }
                    if(direction.equals("south")) {
                        currentLocation = "dining room";
                        Room.diningRoom("N/A");
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("west")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                }
                if (startingLocation.equals("basement")) {
                    if(direction.equals("north")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("south")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if(direction.equals("west")) {
                        System.out.println("There's no door this way!");
                        nextLine();
                    }
                    if (direction.equals("up")) {
                        currentLocation = "kitchen";
                        Room.kitchen("N/A");
                    }
                }
            }
    }

    /**
     * prints out information about the room that the user is in (either general or in one specific direction)
     * @param String[] slicedInput1
     * @param String input1
     */
    public static void lookAround(String[] slicedInput1, String input1){
        if (currentLocation.equals("entrance")){ 
            if (input1.equalsIgnoreCase("look around")) {
                Room.entrance("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.entrance("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.entrance("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.entrance("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.entrance("west"); 
                    }
                    if ((i.equalsIgnoreCase("up")) || (i.equalsIgnoreCase("down"))) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            }
        } else if (currentLocation.equals("entrance hall")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.entranceHall("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.entranceHall("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.entranceHall("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.entranceHall("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.entranceHall("west"); 
                    }
                    if ((i.equalsIgnoreCase("up")) || (i.equalsIgnoreCase("down"))) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            }
        } else if (currentLocation.equals("dining room")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.diningRoom("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.diningRoom("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.diningRoom("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.diningRoom("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.diningRoom("west"); 
                    }
                    if ((i.equalsIgnoreCase("up")) || (i.equalsIgnoreCase("down"))) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            }
        } else if (currentLocation.equals("library")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.library("look around", inventory);
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.library("north", inventory); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.library("south", inventory); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.library("east", inventory); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.library("west", inventory); 
                    }
                    if ((i.equalsIgnoreCase("up")) || (i.equalsIgnoreCase("down"))) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            }
        } else if (currentLocation.equals("kitchen")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.kitchen("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.kitchen("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.kitchen("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.kitchen("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.kitchen("west"); 
                    }
                    if (i.equalsIgnoreCase("down")) { 
                        Room.kitchen("down");
                    } 
                    if (i.equalsIgnoreCase("up")) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }               
                }
            }
        } else if (currentLocation.equals("music room")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.musicRoom("look around", inventory);
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.musicRoom("north", inventory); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.musicRoom("south", inventory); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.musicRoom("east", inventory); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.musicRoom("west", inventory); 
                    }
                    if ((i.equalsIgnoreCase("up")) || (i.equalsIgnoreCase("down"))) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            } 
        } else if (currentLocation.equals("terrace")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.terrace("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.terrace("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.terrace("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.terrace("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.terrace("west"); 
                    }
                    if ((i.equalsIgnoreCase("up")) || (i.equalsIgnoreCase("down"))) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            }
        } else if (currentLocation.equals("parlor")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.parlor("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.parlor("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.parlor("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.parlor("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.parlor("west"); 
                    }
                    if ((i.equalsIgnoreCase("up")) || (i.equalsIgnoreCase("down"))) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            }
        } else if (currentLocation.equals("basement")){ 
            if (input1.equalsIgnoreCase("Look around")) {
                Room.basement("look around", inventory);
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.basement("north", inventory); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.basement("south", inventory); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.basement("east", inventory); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.basement("west", inventory); 
                    }
                    if (i.equalsIgnoreCase("up")) {
                        Room.basement("up", inventory);
                    }
                    if (i.equalsIgnoreCase("down")) {
                        System.out.println("This isn't a valid input.");
                        nextLine();
                    }
                }
            }
        } 
    }    

    /**
     * adds item to inventory as long as they don't already have it in inventory
     * @param String item
     */
    public static void addToInventory(String item){ 
        if (inventory.contains(item) == false) { 
            inventory.add(item);
            if (item.equals("flashlight")) {
                System.out.println("You have added a flashlight to your inventory. Now that you're shining a light around, you can see the house much better.");
            } 
            if (item.equals("key")) {
                System.out.println("You now have a key in your inventory.");
            }
            nextLine();
        } else { 
            System.out.println("You have already taken the " + item + ".");
            nextLine();
        }
    }

    /**
     * Removes an item from inventory
     * @param String item
     */
    public static void removeFromInventory(String item) {
        if (inventory.contains(item)) { 
            inventory.remove(item);
            System.out.println("You have removed a " + item + " from your inventory.");
        } else { 
            System.out.println("You don't have a " + item + " in your inventory to remove.");
        }
        nextLine();
    }

    /** 
     * prints out inventory when user asks for it
     */
    public static void printInventory() {
        System.out.println("Inventory:");
        for( String i : inventory ) {
            System.out.println(i);
        }
        nextLine();
    }
   
    /**
     * prints out why "use key/flashlight" doesn't apply to the game
     * @param String userInput
     * @param String[] slicedInput
     */
    public static void inventoryResponse(String userInput, String[] slicedInput) { 
        if ( (userInput.equals("use the key")) || (userInput.equals("use key")) )  { 
            if (inventory.contains("key")) { 
                if (currentLocation.equals("entrance") == false) { 
                    System.out.println("You don't have anything to use the key on right now.");
                } else {
                    System.out.println("Go south to the gate first!");
                }
            } else { 
                System.out.println("You don't have a key.");
            }   
        }
        for ( String i : slicedInput) {   
            if (i.equals("flashlight")) {
                if (inventory.contains("flashlight")) { 
                    System.out.println("You're flashlight is currently out and being used already.");
                } else {
                    System.out.println("You don't have a flashlight");
                }
            }
        } 
        nextLine();
    }

    /**
     * restarts game when user dies by clearing inventory of flashlight and key and changing currentLocation back to 'entrance'
     * @throws InterruptedException
     */
    public static void restartUser() throws InterruptedException { 
        currentLocation = "entrance";
        if (inventory.contains("flashlight")) { 
            inventory.remove("flashlight");
        }
        if (inventory.contains("key")) { 
            inventory.remove("key");
        }
        Thread.sleep(2000);
        System.out.println("Game over.");
        System.out.println("--------------------");
        System.out.println("--------------------");
        Thread.sleep(1000);
        System.out.println("Restarting the game!");
        Thread.sleep(1000);
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("You step out of the taxi cab with nothing but your backback over your shoulder. Looking to the north, you see a beautiful gothic mansion, to the east and west are sprawling grounds, and there are ominous storm clouds above. Behind you, to the south, big metal gates clang shut. You turn back around to the south of you to open the gate back up but it's locked! You'll need to figure out some way to leave!");
        nextLine();
    }

    /**
     * reads every user input and sends it to readInputs() method 
     */
    public static void nextLine() { 
        System.out.println("   ");
        String userInput = personInput.nextLine();
        readInputs(userInput);
    }

    /**
     * prints out the possible commands and challenges when user types in 'HELP'
     */
    public static void printHelp() { 
        System.out.println("Commands: ");
        System.out.println("  ");
        System.out.println("possible directions: up/down (where staricases are located), north, south, east, west");
        System.out.println("movement related commands: go, move, walk, look (in a certain direction), look around");
        System.out.println("item related commands: pick, pick up, grab, take, drop, get rid of, lose");
        System.out.println("print out inventory: print, show, look at, what's in, check");
        System.out.println("items in game to collect: flashlight, key");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Challenges to complete in the game:");
        System.out.println(" ");
        System.out.println("1. Find flashlight");
        System.out.println("2. Find key ");
        System.out.println("3. Use key to leave the house");
        nextLine();
    }

    /**
     * main method which begins game by taking the user's name and giving first instructions 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("   ");
        System.out.println("Welcome to Haunted! What's your first name? ");
        System.out.println("   ");
        String name = personInput.nextLine();
        Person.name = name;
        System.out.println("   ");
        System.out.println("Welcome " + name + "!");
        System.out.println("  ");
        Thread.sleep(900);
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("You step out of the taxi cab with nothing but your backback over your shoulder. Looking to the north, you see a beautiful gothic mansion, to the east and west are sprawling grounds, and there are ominous storm clouds above. Behind you, to the south, big metal gates clang shut. You turn back around to the south of you to open the gate back up but it's locked! You'll need to figure out some way to leave!");
        nextLine();
        personInput.close();
    } 
}

