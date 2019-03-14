import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
  private Scanner scanGuess;
  //STRING
  private String movieTitle;
  //CHARS
  private char guess;
  //ARRAYS
  private char charMovieTitle[];
  private char charMovie[];
  private char hiddenMovie[];
  private char updateTitle[];
  private char wrongGuesses[];
  private char rightGuesses[];

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
  private boolean isEntered = false;
  private boolean isGuessed = false;
  //boolean hasWon;
  //costruttore
  Game() {
    fileReader();
    movieTitle = fileReader().toUpperCase();
    hiddenMovie = hideTitle();
    System.out.println("\n" + movieTitle);
    System.out.println(charMovie);
    /*next commit:
      > check if movieTitle contains guess
        if guess is found replace that with guess and display update
    */
  }
  public void initMatch() {
    System.out.print("ENTER LETTER: ");
    movieLength = movieTitle.length() - spaces; // without the spacing
    readGuess();
    updateTitle();
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
    isEntered = true;
    guess = Character.toUpperCase(guess);
    return guess;
  }
  private void updateTitle() {
    charMovieTitle = movieTitle.toCharArray();
    updateTitle = new char[charMovieTitle.length];
    updateTitle = charMovieTitle;
    for (int i = 0; i < movieLength; i++) {
      if (updateTitle[i] == guess) {
        hiddenMovie[i] = guess;
        isGuessed = true;

      }
    }
    if(isGuessed){
      score++;
      //rightGuesses += guess;
      updateTitle = hiddenMovie;
      System.out.println(updateTitle);
    }
    System.out.println("SCORE: " + score);
    System.out.println("tentativi rimasti: " + contaTentativo);
  }
}
