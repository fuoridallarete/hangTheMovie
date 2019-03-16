import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  private Scanner scanGuess;
  //STRING
  private String movieTitle;
  String wrongGuesses;
  String rightGuesses;
  String totalGuessed;
  //CHAR
  private char guess;
  //ARRAYS
  private char[] charMovieTitle;
  private char[] charMovie;
  private char[] hiddenTitle;
  private char[] updateTitle;
  //INT
  private int contaTentativo = 10;
  private int score;
  private int spaces = 0;
  private int movieLength; //movieTitleLength - spaces;
  private int randomNumber = 1; // Random movie number
  //BOOLEAN
  //private boolean[] isEntered = new boolean[movieTitle.length()];
  private boolean isEntered = false;
  private boolean isGuessed = false;
  //boolean hasWon;

  //costruttore
  Game() {
    contaTentativo = 10;
    score = 0;
    fileReader();
    movieTitle = fileReader().toUpperCase();
    hiddenTitle = hideTitle();
    System.out.println("\n" + movieTitle);
    System.out.println(charMovie);
  }

  public void initMatch() {
    System.out.print("ENTER LETTER: ");
    movieLength = movieTitle.length() - spaces; // without the spacing
    readGuess();
    //updateTitle();
  }

  private int randomMovieNumber() {
    randomNumber = (int) (Math.random() * 25);
    return randomNumber;
  }

  private String fileReader() {
    File fileMovies = new File("fileFilm.txt");
    ArrayList<String> arrayFilm = new ArrayList<>();
    try {
      FileReader letturaFile = new FileReader(fileMovies);
      BufferedReader bufferFile = new BufferedReader(letturaFile);
      String line = null;
      while ((line = bufferFile.readLine()) != null) {
        arrayFilm.add(line);
      }
      bufferFile.close();
    } catch (FileNotFoundException e) {
      System.out.println("File non trovato.");
    } catch (IOException eIO) {
      System.out.println("Errore di lettura sul file.");
    }
    return arrayFilm.get(randomMovieNumber());
  }

  char[] hideTitle() {
    charMovie = movieTitle.toCharArray();
    for (int i = 0; i < movieTitle.length(); i++) {
      if (charMovie[i] != ' ') {
        charMovie[i] = 'x';
      }
    }
    return charMovie;
  }

  char readGuess() {
    scanGuess = null;
    try {
      scanGuess = new Scanner(System.in);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (scanGuess == null) throw new AssertionError();
    do {
      guess = scanGuess.nextLine().charAt(0);
      //System.out.println(guess);
      contaTentativo--;
      isEntered = true;
      guess = Character.toUpperCase(guess);
      updateTitle();
      scanGuess.close();
      return guess;
    } while (contaTentativo < 10 || movieLength > 0);
  }
  private void updateTitle() {
    charMovieTitle = movieTitle.toCharArray();
    updateTitle = new char[charMovieTitle.length];
    updateTitle = charMovieTitle;
    for (int i = 0; i < movieLength; i++) {
      if (updateTitle[i] == guess) {
        hiddenTitle[i] = guess;
        isGuessed = true;
      }
    }

    updateTitle = hiddenTitle;
    options();
  }
  private void options(){
    //System.out.println(updateTitle);
    //System.out.println("already entered: " + totalGuessed);

    if(isGuessed) {
      score++;
      System.out.println(updateTitle);
    } else {
      wrongGuesses += guess;
      System.out.println(wrongGuesses);
    }
    System.out.println("letters already guessed: " + guess);
    System.out.println("SCORE: " + score);
    System.out.println("tentativi rimasti: " + contaTentativo);
  }
}
