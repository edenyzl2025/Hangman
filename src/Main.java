//import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args)
            throws FileNotFoundException
    {
        File easy  = new File("/Users/edenlim/Documents/hangmanWords/hangmanEasy.txt");
        File medium = new File("/Users/edenlim/Documents/hangmanWords/hangmanMedium.txt");
        File hard = new File("/Users/edenlim/Documents/hangmanWords/hangmanHard.txt");

        Scanner scanE = new Scanner(easy);
        String listEasy[] = new String[50];
        for(int i = 0; i<50; i++){
            listEasy[i]= scanE.nextLine();
        }
        Scanner scanM = new Scanner(medium);
        String listMedium[] = new String[50];
        for(int i = 0; i<50; i++){
            listMedium[i]= scanM.nextLine();
        }
        Scanner scanH = new Scanner(hard);
        String listHard[] = new String[50];
        for(int i = 0; i<50; i++){
            listHard[i]= scanH.nextLine();
        }
        System.out.println("Welcome to Hangman! You are guessing a random word, or difficulty 1, 2 or 3. \nWith each turn, you can either guess a letter/character, or guess a whole word!\nEach wrong guess of character results in a lost attempt, while a wrong WORD results in an automatic loss!\nYou have 8 total attempts...");
        String playAgain = "YES";
        while(playAgain.equals("YES"))
        {
            Scanner scanLevel = new Scanner(System.in);
            System.out.println("Enter your level selection below: 1, 2, or 3");
            String ans = "undecided";
            while(ans.equals("undecided")){
                int level = scanLevel.nextInt();
                if(level==1){
                    ans = getRandomWord(listEasy).toUpperCase();
                }
                else if(level==2){
                    ans = getRandomWord(listMedium).toUpperCase();
                }
                else if (level==3){
                    ans = getRandomWord(listHard).toUpperCase();
                }
                else{
                    ans = "undecided";
                    System.out.println("Please enter a valid level: 1, 2, or 3");
                }
            }
//        System.out.println(ans);
            //LOOP 1 while gameStatus = "ongoing";
            //Explain how game works + guessing specifics (i.e. can guess letter of word)
            //GAME RESET MECHANISM
            ///for loop ans.length()
            ///  display+="_ "
            String gameStatus = "ongoing";
            int attemptCounter = 8;
            String wrongChars = "";
            String[] display = new String[ans.length()];
            for (int i=0;i<display.length;i++) {
                display[i] = "_ ";
            }
            while (gameStatus.equals("ongoing")) {

                printDisplay(display);
                System.out.println("Attempts remaining: " +attemptCounter);
                if(attemptCounter!=8) System.out.println("Guessed letters: "+ wrongChars);
                ////DISPLAY HERE OTHER SPECS like wrong chars + attempt count

                ///create scanner
                ///Prompt user for letter
                ///userInput =scanner.next()

                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter your guess below!");
                String userInput = scanner.nextLine().toUpperCase();
//                System.out.println(userInput);

                if (userInput.length() == 1) {
                    if(displayToString(display).contains(userInput)){
                        System.out.println("You have already revealed this letter :)\nPlease guess a different letter!");
                    }
                    else{
                        int correctCount = 0;
                        for (int i = 0; i < ans.length(); i++) {
                            if (userInput.equals(String.valueOf(ans.charAt(i)))) {
                                correctCount++;
                                display[i] = String.valueOf(ans.charAt(i));
                            }
                        }
                        if (correctCount == 0) {
                            attemptCounter--;
                            wrongChars += userInput;
                        }
                        gameStatus = checkGameStatus(display, ans);
                        if(attemptCounter==0) gameStatus = "lost";
                    }
                }
                else{
                    System.out.println("Are you sure you would like to guess the word? If your guess is wrong, you will loose automatically!\n(yes or no)");
                    String yesOrNo = scanner.nextLine().toUpperCase();
                    if(yesOrNo.equals("YES")){
                        if (userInput.equals(ans)) gameStatus = "won";
                        else gameStatus = "lost";
                    }
                    else{
                        System.out.println("Got it. Please continue playing!");
                    }
                }


            }
            System.out.println("YOU HAVE "+gameStatus.toUpperCase()+" THE GAME. THE CORRECT WORD IS "+ans+"!");
            System.out.println("Would you like to play again?\n(yes or no)");
            Scanner again = new Scanner(System.in);
            playAgain = again.nextLine().toUpperCase();
        }
        System.out.println("Thank you for playing!");
    }
    public static String getRandomWord(String[] array){
        return array[(int) Math.floor((Math.random() *50)+1)];
    }
    public static String displayToString(String[] display){
        String string = "";
        for(int i = 0; i<display.length-1;i++) {
            string += display[i];
        }
        return string;
    }
    public static void printDisplay(String[] display){
        System.out.println("Your words looks like the following:");
        for(int i = 0; i<display.length-1;i++){
            System.out.print(display[i]);
        }
        System.out.println(display[display.length-1]);
    }
    public static String checkGameStatus(String[] userGuessed, String ans) {
        String toString = "";
        for(int i = 0; i<userGuessed.length;i++){
            toString+=userGuessed[i];
        }
        if(toString.equals(ans)) return "won";
        else return "ongoing";
    }
}







//to add in Part 2
//easy medium hard import of files