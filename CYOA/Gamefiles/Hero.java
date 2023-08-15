package Gamefiles;


import java.util.*;

public class Hero {
    int str;
    int intl;
    int brv;
    String mood;
    String aura;
    Set<String> eggs = new HashSet<>();

    public Hero(){
        str = 0;
        intl = 0;
        brv = 0;
        mood = "neutral";
        aura = "neutral";
        eggs.add("exist");
    }

    public void printHero(){
        System.out.println("Strength------" + str);
        System.out.println("Intelligence--" + intl);
        System.out.println("Courage-------" + brv);
        System.out.println("Mood----------" + mood);
        System.out.println("Aura----------" + aura);
    }

    public Hero(int str, int intl, int brv, String mood, String aura){
        this.str = str;
        this.intl = intl;
        this.brv = brv;
        this.mood = mood;
        this.aura = aura;
    }

    public void levelUp(Choice choice){
        str += choice.strUp;
        intl += choice.intlUp;
        brv += choice.brvUp;
        if (choice.moodUp != "" && choice.moodUp != null){
            mood = choice.moodUp;
        }
        if (choice.auraUp != "" && choice.auraUp != null){
            aura = choice.auraUp;
        }
    }

    public void reset(){
        str = 0;
        intl = 0;
        brv = 0;
        mood = "neutral";
        aura = "neutral";
        eggs.clear();
        eggs.add("exist");
    }
}
