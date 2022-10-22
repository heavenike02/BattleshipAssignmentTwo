/**
 * @author (Heaven Ike)
 * @version (22/10/2002)
 */
import java.util.*;

public class BattleshipGame{
    private static Scanner input = new Scanner(System.in);

    /**
     * Prompts a user for a positive int value and returns it.
     *
     * @param prompt
     *            the desired prompt message to be displayed on screen
     */

    public static int promptForInt(String prompt) {
        int number;
        do
        {   System.out.print(prompt + ": ");
            number = input.nextInt();
            if (number <= 0)
                System.out.println("Enter a positive integer!");
        }while (number <= 0);
        return number;
    }

    /**
     * Displays a nice picture of the river in the Console View
     *
     * @param river
     *            the array that represents the river
     * @param showShip
     *            indicates if you wish to display the location of the ship
     */

    public static void displayRiver(int[] river, boolean showShip) {
        System.out.println();
        System.out.print("|");
        for (int val : river) {
            switch (val) {
                case -1: // No Ship
                    System.out.print("x");
                    break;
                case 0: // Unknown
                    System.out.print(" ");
                    break;
                case 1: // Ship Found
                    System.out.print(showShip ? "Y" : " ");
                    break;
            }//switch
            System.out.print("|");
        }//for
        System.out.println();
        System.out.println();
    }//displayRiver

    // main method
    /**
     * The	main	method	is	blank, and	you	are	to	complete	it.
     * First	ask	the	user	to	enter	the	"length"	of	the	river	(i.e.	the	number	of	locations	to	hide	the
     * ship) and	create	an	array with	the	specified	length.	Hide	the	ship	in	the	river	using	a
     * random number.
     */
    public static void main(String[] arg) {
        //ask user for river length
        int riverLength = promptForInt("Please enter the river length");

        //create array of river  length
        int[] river = new int[riverLength];
        //create random number between 0-river length
        Random random = new Random();
        int shipLocation = random.nextInt(riverLength);
        //assign ship location to array
        for (int i = 0; i < riverLength; i++){
            if(i == shipLocation ){
                river[i] = 1 ;//ship location
            }
            else {
                river[i] = 0;//unknown
            }

        }
        //setting default value to not show ship
        boolean showShip;
        do {
            //storing user input
            int guess = promptForInt("Please enter the location from 1 to "+ riverLength);
            //subtracting the guess in line with array index
            guess--;
            //checking if the guess is correct
            //if guess is not correct do not show ship , assign guess location in array  to no ship
            if(guess != shipLocation ) {
                showShip = false;
                river[guess] = -1;
                displayRiver(river,showShip);

                System.out.println("You missed the ship");
            }
            else {
                //if guess is correct show ship
                showShip = true;
                displayRiver(river,showShip);
                System.out.println("Boom!");
            }

        }
       while (!showShip);

    }//main
}//class
