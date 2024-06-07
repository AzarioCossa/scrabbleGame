package scrabble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        this.words = new ArrayList<>();
    }
    
	public Boolean addWord(Word word,GameBoard gameBoard) {
    	this.incrementScore(this.calculateWordScore(word, gameBoard));
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
    
    private int calculateWordScore(Word word, GameBoard gameBoard) {
        int wordScore = 0;
        int wordMultiplier = 1;

        for (Map.Entry<Position, Tile> entry : word.getTiles().entrySet()) {
            Position position = entry.getKey();
            Tile tile = entry.getValue();

            int letterMultiplier = 1;

     
            Square square = gameBoard.getSquares()[position.row()-1][position.column()-1];
            SquareType bonus = square.getSquareType();

            switch (bonus) {
                case DOUBLE_LETTER:
                    letterMultiplier = 2;
                    break;
                case TRIPLE_LETTER:
                    letterMultiplier = 3;
                    break;
                case DOUBLE_WORD:
                    wordMultiplier *= 2;
                    break;
                case TRIPLE_WORD:
                    wordMultiplier *= 3;
                    break;
                default:
                	break;
                
            }

            System.out.println(tile.getWeight());
            wordScore  += tile.getWeight() * letterMultiplier;
        }

        wordScore *= wordMultiplier;

        return wordScore ;
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
