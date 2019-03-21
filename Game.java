import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  private Scanner scanGuess;
  //STRING
  private String movieTitle;
  private String wrongGuesses = "";
  private String rightGuesses = "";
  private String totalGuessed = "";
  //CHAR
  private char guess;
  //ARRAYS
  private char[] charMovieTitle;
  private char[] charMovie;
  private char[] hiddenTitle;
  private char[] updateTitle;
  private char[] charWrongGuesses;
  private char[] charTotalGuesses;
  //INT
  private int contaTentativo;
  private int score;
  private int spaces = 0;
  private int movieLength; //movieTitleLength - spaces;
  private int randomNumber = 1; // Random movie number
  //BOOLEAN
  private boolean isEntered = false;
  private boolean isGuessed = false;
  private boolean isDouble = false;
  //private boolean hasWon;

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

  public char[] hideTitle() {
    charMovie = movieTitle.toCharArray();
    for (int i = 0; i < movieTitle.length(); i++) {
      if (charMovie[i] != ' ') {
        charMovie[i] = 'x';
      }
    }
    return charMovie;
  }

  public char readGuess() throws AssertionError {
    isEntered = false;
    scanGuess = null;
    try {
      scanGuess = new Scanner(System.in);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (scanGuess == null) throw new AssertionError();

    guess = scanGuess.nextLine().charAt(0);
    isEntered = true;
    guess = Character.toUpperCase(guess);
    updateTitle();
    scanGuess.close();
    return guess;
  }

  private void updateTitle() {
    charMovieTitle = movieTitle.toCharArray();
    updateTitle = new char[charMovieTitle.length];
    updateTitle = charMovieTitle;
    isGuessed = false;
    if(isEntered) {
      for (int i = 0; i < movieLength; i++) {
        if (guess == charMovieTitle[i]) {
          hiddenTitle[i] = guess;
          isGuessed = true;
        }
      }
      updateTitle = hiddenTitle;
      options(guess);
    }
    do{
      initMatch();
      isGuessed = false;
    } while(contaTentativo != 0 && movieLength > 0);
  }
  private boolean checkIfDouble(char guessChar) {
    totalGuessed = rightGuesses + wrongGuesses;
    charTotalGuesses = totalGuessed.toCharArray();
    isDouble = false;
    for (int i = 0; i < charTotalGuesses.length; i++) {
      if (guessChar == charTotalGuesses[i]) {
        isDouble = true;
      }
    }
    return isDouble;
  }

  private void options(char myGuess){
    if(checkIfDouble(myGuess)){
      System.out.println("You already tried this letter. Try another one.");
    }
    if(!isGuessed){
      contaTentativo--;
      wrongGuesses += myGuess;
    }else {
      score++;
      rightGuesses += myGuess;
    }
    gameState();
  }

  private void gameState(){
    System.out.println(updateTitle);
    System.out.println("right guesses: " + rightGuesses);
    System.out.println("wrong guesses: " + wrongGuesses);
    System.out.println("letters already guessed: " + totalGuessed);
    System.out.println("SCORE: " + score);
    System.out.println("tries left: " + contaTentativo);
  }
}
