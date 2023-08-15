package Gamefiles;

import java.util.*;

public class Choice{
    private String choice;
    private String next;
   
    private int strReq;
    private int intlReq;
    private int brvReq;

    private boolean moodReq;
    private Set<String> reqMood = new HashSet<>();
    private boolean auraReq;
    private Set<String> reqAura = new HashSet<>();
    private Set<String> eggsReq = new HashSet<>();

    int strUp;
    int intlUp;
    int brvUp;
    String moodUp;
    String auraUp;
    
    public String getNext(){
        return next;
    }

    public String getChoice(){
        return choice;
    }

    //print or leave option postion blank if accessible by player
    public String appear(Hero hero){

        //check if requirements of the options are met
        if (!moodReq && !auraReq){
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            hero.eggs.containsAll(eggsReq)){
                return choice;
            } else {
                return "";
            }
        } else if (moodReq){
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            reqMood.contains(hero.mood) &&
            hero.eggs.containsAll(eggsReq)){
                return choice;
            } else {
                return "";
            }
        } else if (auraReq){
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            reqAura.contains(hero.aura) &&
            hero.eggs.containsAll(eggsReq)){
                return choice;
            } else {
                return "";
            }
        } else {
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            reqMood.contains(hero.mood) &&
            reqAura.contains(hero.aura) &&
            hero.eggs.containsAll(eggsReq)){
                return choice;
            } else {
                return "";
            }
        }
        
        
    }

    //to make sure a player does not enter a value that is inaccessible
    public boolean valid(Hero hero){
        if (!moodReq && !auraReq){
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            hero.eggs.containsAll(eggsReq)){
                return true;
            } else {
                return false;
            }
        } else if (moodReq){
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            reqMood.contains(hero.mood) &&
            hero.eggs.containsAll(eggsReq)){
                return true;
            } else {
                return false;
            }
        } else if (auraReq){
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            reqAura.contains(hero.aura) &&
            hero.eggs.containsAll(eggsReq)){
                return true;
            } else {
                return false;
            }
        } else {
            if (hero.str >= strReq &&
            hero.intl >= intlReq &&
            hero.brv >= brvReq &&
            reqMood.contains(hero.mood) &&
            reqAura.contains(hero.aura) &&
            hero.eggs.containsAll(eggsReq)){
                return true;
            } else {
                return false;
            }
        }
        
        
    }

    //set story, string for choice, string for next story key
    public Choice(String choice, String next){
        this.choice = choice;
        this.next = next;
        eggsReq.add("exist");
    }

    //set required int stats
    public void addIntReqs(int strReq, int intlReq, int brvReq){
        this.strReq = strReq;
        this.intlReq = intlReq;
        this.brvReq = brvReq;
    }

    //set required mood/aura/eggs
    public void addMoodReqs(Set<String> reqMood){
        this.moodReq = true;
        this.reqMood = reqMood;
    }
    public void addAuraReqs(Set<String> reqAura){
        this.auraReq = true;
        this.reqAura = reqAura;
    }
    public void addEggsReqs(Set<String> eggsReq){
        this.eggsReq = eggsReq;
    }

    //set new stats
    public void addLevelUp(int strUp, int intlUp, int brvUp){
        this.strUp += strUp;
        this.intlUp += intlUp;
        this.brvUp += brvUp;
    }
    public void addProfileUp(String moodUp, String auraUp){
        this.moodUp = moodUp;
        this.auraUp = auraUp;
    }

    
    public void printReqs(){
        System.out.println("str " + strReq);
        System.out.println("intl " + intlReq);
        System.out.println("brv " + brvReq);
        System.out.println("mood " + reqMood);
        System.out.println("aura " + reqAura);
        System.out.print("eggs " + eggsReq);
    }
}