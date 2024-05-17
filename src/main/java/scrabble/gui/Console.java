package scrabble.gui;

import java.util.Scanner;

public class Console {

    protected static final String LINE = "---------------------------------------------";
    protected static final String LINE_BREAK = "\n";

    Scanner keyboard = new Scanner(System.in);

    public static void message(String text) {
        System.out.print(text);
    }
    
    public static void messageBreak(String message) {
    	System.out.println(message);
    }

    public static  void title(String title) {
        message(LINE);
        message(title);
        message(LINE);
    }

    public static void line() {
        message(LINE);
    }

    public static void lineBreak() {
        message(LINE_BREAK);
    }

}