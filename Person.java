import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Person Class are the actions possible for the main character / user of the game
 */
public class Person {

    public static String name;
    static String currentLocation = "entrance";
    static private ArrayList<String> inventory = new ArrayList<>(List.of("backpack"));
    int energyLevel;
    public static Scanner personInput = new Scanner(System.in);
    public static ArrayList<String> movements = new ArrayList<>(List.of("go", "move", "walk"));
    public static ArrayList<String> directions = new ArrayList<>(List.of("north", "south", "east", "west", "up", "down"));
    public static ArrayList<String> actions = new ArrayList<>(List.of("pick up", "take", "grab", "pick", "drop", "get rid of", "lose"));
    public static ArrayList<String> allItems = new ArrayList<>(List.of("key", "backpack", "flashlight"));

    /**
     * reads all user inputs and sends inputs to the right methods depending on what the user has types. Also sends an error message when the user inputs a command that is not applicable to the game. 
     * @param userInput
     */
    public static void readInputs(String userInput) {
        userInput = userInput.toLowerCase();
        String[] slicedInput = userInput.split(" ");
        if ( (userInput.equals("inventory")) || (userInput.equals("look at inventory")) || (userInput.equals("check inventory")) || (userInput.equals("what's in my inventory")) || (userInput.equals("what's in inventory")) || (userInput.equals("print inventory")) || (userInput.equals("show inventory")) || (userInput.equals("show my inventory")) || (userInput.equals("show the inventory"))) {
            printInventory();
            return;
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
                    String retryInput = personInput.nextLine();
                    readInputs(retryInput);
                }
            }
            if (userInput.equals("flashlight")) {
                if (currentLocation.equals("music room")) { 
                    Item.key("look");
                } else { 
                    System.out.println("This isn't a valid command.");
                    String retryInput = personInput.nextLine();
                    readInputs(retryInput);
                }
            }
        }
        if ( ( ((directionCounter == 0) && (movementsCounter == 0) && (itemCounter == 0) ) 
        || (((movementsCounter == 0) && (directionCounter == 0)) && (itemCounter > 0)) && ((userInput.equals("look around") == false) && (actionCounter == 0) ) 
        || ((movementsCounter > 0) && (directionCounter == 0)) && (itemCounter == 0)) && (userInput.equals("look around") == false )  
        || ((((movementsCounter == 0) && (directionCounter > 0)) && (itemCounter == 0)) && (userInput.equals("look around") == false ))) {
            System.out.println("This isn't a valid command.");
            String retryInput = personInput.nextLine();
            readInputs(retryInput);
        } else if (movementsCounter > 1) {
            System.out.println("Only use one verb in your commands.");
            String retryInput = personInput.nextLine();
            readInputs(retryInput);
        } else if (directionCounter > 1) {
            System.out.println("This isn't a valid command.");
            String retryInput = personInput.nextLine();
            readInputs(retryInput);
        } else if (itemCounter > 1) {
            System.out.println("You can't use two items at once!");
            String retryInput = personInput.nextLine();
            readInputs(retryInput);
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
            } }

        }

    /**
     * changes location by moving north, south, east, west, up or down depending on what's allowed in the room.
     * @param startingLocation
     * @param direction
     */ 
     public static void changeLocation(String startingLocation, String direction) {
            if  ( (direction.equals("up")) && (currentLocation.equals("basement") == false) ) { 
                System.out.println("This isn't a valid input.");
                String invalidLocationAsk = personInput.nextLine();
                readInputs(invalidLocationAsk);
            } else { 
                if (startingLocation.equals("entrance")) {
                    if(direction.equals("north")) {
                        currentLocation = "entrance hall";
                        Room.entranceHall("N/A");
                    }
                    if(direction.equals("south")) {
                        if  (inventory.contains("key") == false) { 
                            System.out.println("You can't leave! The gate has locked behind you and you don't have the key!");
                            String falseDirectionInput = personInput.nextLine();   
                            readInputs(falseDirectionInput);
                        } else if (inventory.contains("key")) { 
                            System.out.println("The gate is locked. Do you want to see if your key opens it?");
                            String exitInput = personInput.nextLine();
                            exitInput = exitInput.toLowerCase();
                            if ( (exitInput.equals("yes")) || (exitInput.equals("sure")) || (exitInput.equals("okay")) ||(exitInput.equals("ok")) ||(exitInput.equals("try key")) || (exitInput.equals("try the key")) || (exitInput.equals("use the key")) || (exitInput.equals("use key"))  ) {
                                Room.exit();
                            } else if (exitInput.equals("no")) {
                                System.out.println("Ok, if you say so.");
                                String nextInput = personInput.nextLine();
                                readInputs(nextInput);
                            }
                        }
                    }
                    if(direction.equals("east")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        System.out.println("Game over.");
                        System.out.println("--------------------");
                        System.out.println("--------------------");
                        System.exit(0);
                    }
                    if(direction.equals("west")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        System.out.println("Game over.");
                        System.out.println("--------------------");
                        System.out.println("--------------------");
                        System.exit(0);
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
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
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
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                }
                if (startingLocation.equals("library")) {
                    if(direction.equals("north")) {
                        currentLocation = "music room";
                        Room.musicRoom("N/A", inventory);
                    }
                    if(direction.equals("south")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("west")) {
                        currentLocation = "dining room";
                        Room.diningRoom("N/A");
                    }
                }
                if (startingLocation.equals("kitchen")) {
                    if(direction.equals("north")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("south")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("east")) {
                        currentLocation = "entrance hall";
                        Room.entranceHall("N/A");
                    }
                    if(direction.equals("west")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if (direction.equals("down")) {
                        currentLocation = "basement";
                        Room.basement("N/A", inventory);
                    }
                }
                if (startingLocation.equals("music room")) {
                    if(direction.equals("north")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("south")) {
                        currentLocation = "library";
                        Room.library("N/A", inventory);
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("west")) {
                        currentLocation = "terrace";
                        Room.terrace("N/A");
                    }
                }
                if (startingLocation.equals("terrace")) {
                    if(direction.equals("north")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        System.out.println("Game over.");
                        System.exit(0);
                    }
                    if(direction.equals("south")) {
                        currentLocation = "dining room";
                        Room.diningRoom("N/A");
                    }
                    if(direction.equals("east")) {
                        currentLocation = "music room";
                        Room.musicRoom("N/A", inventory);
                    }
                    if(direction.equals("west")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                }
                if (startingLocation.equals("basement")) {
                    if(direction.equals("north")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("south")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("east")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("west")) {
                        System.out.println("There's no door this way!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
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
     * @param slicedInput1
     * @param input1
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
                        String invalidLocationAsk = personInput.nextLine();
                        readInputs(invalidLocationAsk);
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
                        String invalidLocationAsk = personInput.nextLine();
                        readInputs(invalidLocationAsk);
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
                        String invalidLocationAsk = personInput.nextLine();
                        readInputs(invalidLocationAsk);
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
                        String invalidLocationAsk = personInput.nextLine();
                        readInputs(invalidLocationAsk);
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
                        String invalidLocationAsk = personInput.nextLine();
                        readInputs(invalidLocationAsk);
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
                        String invalidLocationAsk = personInput.nextLine();
                        readInputs(invalidLocationAsk);
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
                            String invalidLocationAsk = personInput.nextLine();
                            readInputs(invalidLocationAsk);
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
                            String invalidLocationAsk = personInput.nextLine();
                            readInputs(invalidLocationAsk);
                        }
                    }
                }
            } 
        } 
    

    public static void addToInventory(String item){ 
        inventory.add(item);
        System.out.println("You have added a " + item + " to your inventory.");
        String afterInventoryAsk = personInput.nextLine();
        readInputs(afterInventoryAsk);
    }

    public static void removeFromInventory(String item) {
        inventory.remove(item);
        System.out.println("You have removed a " + item + " from your inventory.");
        String afterInventoryAsk = personInput.nextLine();
        readInputs(afterInventoryAsk);

    }

    public static void printInventory() {
        System.out.println("Inventory:");
        for( String i : inventory ) {
            System.out.println(i);
        }
        String inventoryAsk = personInput.nextLine();
        readInputs(inventoryAsk);
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to Haunted! What's your first name? ");
        String name = personInput.nextLine();
        System.out.println("Welcome " + name + "!");
        Thread.sleep(1000);
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("You step out of the taxi cab with nothing but your backback over your shoulder. Looking to the north, you see a beautiful gothic mansion, to the east and west are sprawling grounds, and there are ominous storm clouds above. Behind you, to the south, big metal gates clang shut.");
        String firstInput = personInput.nextLine();
        readInputs(firstInput);
        personInput.close();
    } 
}
