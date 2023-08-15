package Gamefiles;

import java.util.*;

public class Event {
    String story;
    ArrayList<Choice> choices = new ArrayList<>();   
    
    public Event(){
        this.story = "";
    }

    public Event(String story){
        this.story = story;
    }

    public Event(String story, ArrayList<Choice> choices){
        this.story = story;
        this.choices = choices;
    }

}
