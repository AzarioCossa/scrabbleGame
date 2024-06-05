package scrabble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class User {
    private Rack rack;
    private final String name;
    private final IntegerProperty score;
    private List<Word> words;

    public User(String name, Rack rack) {
        this.name = name;
        this.rack = rack;
        this.score = new SimpleIntegerProperty(0);
        this.words = new ArrayList<Word>();
        
    }
    
    public Boolean addWord(Word word) {
    	this.incrementScore(this.calculateWordScore(word));
    	return this.words.add(word);
    }

    public Rack getRack() {
        return rack;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score.get();
    }

    public void incrementScore(int score) {
        this.score.set(this.score.get() + score);
    }

    public IntegerProperty scoreProperty() {
        return score;
    }
    
    private int calculateWordScore(Word word) {
        int score = 0;
        for (Tile tile : word.getTiles().values()) {
            score += tile.getWeight(); // Supposons que getWeight() retourne le poids de la tuile
        }
        return score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rack);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(name, other.name) && Objects.equals(rack, other.rack);
    }
}
