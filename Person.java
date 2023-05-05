import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Person Class are the actions possible for the main character / user of the game
 */
public class Person {

    String name;
    static String currentLocation = "entrance";
    private ArrayList<String> inventory;
    int energyLevel;
    public static Scanner personInput = new Scanner(System.in);
    public static ArrayList<String> verbs = new ArrayList<>(List.of("go", "move", "walk", "jump", "climb"));
    public static ArrayList<String> directions = new ArrayList<>(List.of("north", "south", "east", "west", "up", "down"));

    Person(String name){
        energyLevel = 100;
        currentLocation = "entrance";
    }

    /**
     * reads all user inputs and sends inputs to the right methods depending on what the user has types. Also sends an error message when the user inputs a command that is not applicable to the game. 
     * @param userInput
     */
    public static void readInputs(String userInput) {
        userInput = userInput.toLowerCase();
        String[] slicedInput = userInput.split(" ");
        int directionCounter = 0;
        int verbCounter = 0;
        for ( String i : slicedInput) { 
            if (verbs.contains(i) || (i.equals("look"))) {
                verbCounter += 1;
            }
            if (directions.contains(i)) {
                directionCounter += 1;
            }
        } 
        if ( ( ((directionCounter == 0) && (verbCounter == 0)) || ((verbCounter > 0) && (directionCounter == 0)) ) && (userInput.equals("look around") == false ) ) {
            System.out.println("This isn't a valid command.");
            String retryInput = personInput.nextLine();
            readInputs(retryInput);
        } else if (verbCounter > 1) {
            System.out.println("Only use one verb in your commands.");
            String retryInput = personInput.nextLine();
            readInputs(retryInput);
        } else if (directionCounter > 1) {
            System.out.println("You can't go in two directions at once");
            String retryInput = personInput.nextLine();
            readInputs(retryInput);
        } 
        if (directions.contains(userInput)) {
            lookAround(slicedInput, userInput);
        }
        if (directions.contains(userInput) == false ) { 
            for ( String i : slicedInput) { 
                if (i.equalsIgnoreCase("Look")) {
                    lookAround(slicedInput, userInput);
                }
                for ( String j : slicedInput) { 
                    if (verbs.contains(j)) { 
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
                        // add key if-statement
                        System.out.println("You can't leave! You don't have a key!");
                        String falseDirectionInput = personInput.nextLine();   
                        readInputs(falseDirectionInput);
                    }
                    if(direction.equals("east")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        System.out.println("Game over.");
                    }
                    if(direction.equals("west")) {
                        System.out.println("You quickly get lost in the dark forest and are killed by ghosts!");
                        System.out.println("Game over.");
                    }
                }
                if (startingLocation.equals("entrance hall")) {
                    if(direction.equals("north")) {
                        currentLocation = "dining room";
                        Room.diningRoom("N/A");
                    }
                    if(direction.equals("south")) {
                        currentLocation = "entrance";
                        Room.Entrance("N/A");
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
                        Room.library("N/A");
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
                        Room.musicRoom("N/A");
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
                        Room.basement("N/A");
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
                        Room.library("N/A");
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
                        Room.musicRoom("N/A");
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
                Room.Entrance("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.Entrance("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.Entrance("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.Entrance("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.Entrance("west"); 
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
                Room.library("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.library("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.library("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.library("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.library("west"); 
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
                Room.musicRoom("look around");
            }
            if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                for ( String i : slicedInput1) { 
                    if (i.equalsIgnoreCase("north")){ 
                        Room.musicRoom("north"); 
                    }
                    if (i.equalsIgnoreCase("south")){ 
                        Room.musicRoom("south"); 
                    }
                    if (i.equalsIgnoreCase("east")){ 
                        Room.musicRoom("east"); 
                    }
                    if (i.equalsIgnoreCase("west")){ 
                        Room.musicRoom("west"); 
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
                    Room.basement("look around");
                }
                if (slicedInput1[0].equalsIgnoreCase("look") || directions.contains(slicedInput1[0])) { 
                    for ( String i : slicedInput1) { 
                        if (i.equalsIgnoreCase("north")){ 
                            Room.basement("north"); 
                        }
                        if (i.equalsIgnoreCase("south")){ 
                            Room.basement("south"); 
                        }
                        if (i.equalsIgnoreCase("east")){ 
                            Room.basement("east"); 
                        }
                        if (i.equalsIgnoreCase("west")){ 
                            Room.basement("west"); 
                        }
                        if (i.equalsIgnoreCase("up")) {
                            Room.basement("up");
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
    


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to Haunted! What's your first name? ");
        String name = personInput.nextLine();
        System.out.println("Welcome " + name + "!");
        Thread.sleep(1000);
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("You step out of the taxi cab with nothing but your backback over your shoulder. Looking up, you see a beautiful gothic mansion, sprawling grounds, and ominous storm clouds above.");
        String input = personInput.nextLine();
        readInputs(input);
        personInput.close();
    } 
}
