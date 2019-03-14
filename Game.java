import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
  private Scanner scanGuess;
  //STRING
  private String totalGuess;
  private String movieTitle;
  //CHARS
  private char guess;
  //ARRAYS
  private char charMovieTitle[];
  private char charMovie[];
  private char hiddenMovie[];

  //private char charTemp[];
  //private char[] charGuesses = rightLetters.toCharArray();
  //INT
  private int spaces = 0;
  private int movieLength; //movieTitleLength - spaces;
  private int randomNumber = 1; // Random movie number
  private int contaTentativo = 10;
  private int score;
  //BOOLEAN
  //private boolean[] isEntered = new boolean[movieTitle.length()];
  private boolean isEntered;
  private boolean result;
  //boolean hasWon;
  //costruttore
  Game() {
    fileReader();
    movieTitle = fileReader();
    hiddenMovie = hideTitle();
    System.out.println("\n" + movieTitle.toUpperCase());
    System.out.println(charMovie);
    initMatch();
    /*next commit:
      > check if movieTitle contains guess
        if guess is found replace that with guess and display update
    */
  }
  public void initMatch() {
    movieLength = movieTitle.length() - spaces; // without the spacing
    readGuess();
  }

  // @return a random number using math.random() function
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
      if(charMovie[i] != ' '){
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
    guess = scanGuess.nextLine().charAt(0);
    //System.out.println(guess);
    contaTentativo--;
    guess = Character.toUpperCase(guess);
    return guess;
  }

}
