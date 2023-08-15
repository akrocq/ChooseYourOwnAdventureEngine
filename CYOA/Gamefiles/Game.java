package Gamefiles;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {
    public static final Map<String, Event> storyTree = new HashMap<>();
    
    public static void main(String[] a)throws FileNotFoundException{
        //initialize starting variables
        Scanner player = new Scanner(System.in);
        Hero hero = new Hero();
        
        //scan events file, formatted to construct story events and options
        Scanner narrator = new Scanner(new File("C:\\Users\\akroc\\Documents\\2A\\CYOA\\Gamefiles\\eventStories.txt"));
        while(narrator.hasNextLine()){
            
            //initialize event
            Event event = new Event();

            //checks for debugging, move scanner forward
            String check = narrator.nextLine();
            String checkTwo = narrator.nextLine();

            //key for map
            String prevKey = narrator.nextLine();
            
            //narrative for event
            event.story = narrator.nextLine();

            //if story has options
            if (narrator.hasNext()){

                //for conditionals
                String next = narrator.next();

                //loop through
                while (next.equalsIgnoreCase("option")){
                    
                    //for debugging, move scanner forward
                    check = narrator.nextLine();

                    //enter option and its key for next event
                    Choice option = new Choice(narrator.nextLine(), narrator.nextLine());
    
                    //for if statements
                    next = narrator.next();
    
                    //add for requirements for strength, intelligence and bravery
                    if (next.equalsIgnoreCase("intReqs")){
                        option.addIntReqs(narrator.nextInt(), narrator.nextInt(), narrator.nextInt());
                        next = narrator.next();
                    }

                    //add possible moods for the option
                    if (next.equalsIgnoreCase("moodReqs")){
                        Set<String> moods = new HashSet<>();
                        next = narrator.next();
                        while (next.equalsIgnoreCase("m")){
                            narrator.nextLine();
                            moods.add(narrator.nextLine());
                            next = narrator.next();
                        }
                        option.addMoodReqs(moods);
                    }

                    //add possible auras for the option
                    if (next.equalsIgnoreCase("auraReqs")){
                        Set<String> auras = new HashSet<>();
                        next = narrator.next();
                        while (next.equalsIgnoreCase("a")){
                            narrator.nextLine();
                            auras.add(narrator.nextLine());
                            next = narrator.next();
                        }
                        option.addMoodReqs(auras);
                    }

                    //add required eggs to access option
                    if (next.equalsIgnoreCase("eggsReqs")){
                        Set<String> eggs = new HashSet<>();
                        next = narrator.next();
                        while (next.equalsIgnoreCase("e")){
                            narrator.nextLine();
                            eggs.add(narrator.nextLine());
                            next = narrator.next();
                        }
                        option.addMoodReqs(eggs);
                    }

                    //add profile changes
                    if  (next.equalsIgnoreCase("levelUp")){
                        option.addLevelUp(narrator.nextInt(), narrator.nextInt(), narrator.nextInt());
                        next = narrator.next();
                    }
                    if (next.equalsIgnoreCase("profileUp")){
                        narrator.nextLine();
                        option.addProfileUp(narrator.nextLine(), narrator.nextLine());
                        next = narrator.next();
                    }
                    //add option to event
                    event.choices.add(option);
                }
            
            }
            //add event to map
            storyTree.put(prevKey, event);
        }

        //start game
        play(storyTree.get("game"), hero, player);
    }

    public static void play(Event event, Hero hero, Scanner player){

        //print narrative + player profile with minimal formatting
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(event.story);
        System.out.println();
        System.out.println("    Profile    ");
        hero.printHero();
        System.out.println();

        //if event is an ending
        if (event.choices.isEmpty()){
            System.out.println("Game over");
            System.out.println();
            System.out.println("Do you wish to restart?");
            System.out.println();
            System.out.println("1: yes");
            System.out.println("2: no");
            System.out.print("Enter choice: ");

            //player restarts or ends game
            if (player.next().equals("1") || player.next().equalsIgnoreCase("yes")){
                hero.reset();
                play(storyTree.get("key:start"), hero, player);
            } else {
                System.out.println("Thanks for playing");
            }

        } else {

            //print out number of options and accessible options
            for (int i = 0; i < event.choices.size(); i++){
                System.out.println((i+1) + ": " + event.choices.get(i).appear(hero));
            }
            System.out.println();
    
            //user enters choice
            System.out.print("Enter choice: ");
            int choice = 0; 
            boolean valid = false;
            
            //check choice was valid
            while (!valid) {
                try {
                    choice = player.nextInt() - 1;
                    while (choice < 0 || choice >= event.choices.size() || !event.choices.get(choice).valid(hero)){
                        System.out.print("Please enter valid number: ");
                        choice = player.nextInt() - 1;
                    }
                    valid = true; 
                } catch (Exception e) {
                    System.out.print("Please enter valid number: ");
                    valid = false;
                    player.nextLine();
                }
            }

            //change player profile
            hero.levelUp(event.choices.get(choice));
    
            //play event based on key of player's chosen option
            play(storyTree.get(event.choices.get(choice).getNext()), hero, player);
        }     
    }
}
