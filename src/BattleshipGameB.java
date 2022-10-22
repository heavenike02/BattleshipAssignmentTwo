/**
 * @author (Heaven Ike)
 * @version (22/10/2002)
 */
import java.util.*;

public class BattleshipGameB{
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
                case 2: // Dead ship
                    System.out.print("Y");
                    break;


            }//switch
            System.out.print("|");
        }//for
        System.out.println();
        System.out.println();
    }//displayRiver

    // main method
    /**
     * providing functionality for 2 ships
     */
    public static void main(String[] arg) {
        //ask user for river length
        //preventing more than two ships
        int shipCount = promptForInt("Please enter the number of ships (1 0r 2)");
        while (shipCount > 2) {
            System.out.println("Please enter a valid number of ships");
            shipCount = promptForInt("Please enter the number of ships (1 0r 2)");
        }
        //making sure river is greater than ships
        int riverLength = promptForInt("Please enter the river length");
        while (riverLength < shipCount) {
            System.out.println("Please enter a valid river length");
            riverLength = promptForInt("Please enter the river length");
        }


        //create array of river  length
        int[] river = new int[riverLength];
        //create random number between 0-river length
        Random random = new Random();
        int [] shipLocations = new int[shipCount];
        for  (int i = 0; i < shipCount; i++) {
            //assign numbers into allocations array
            shipLocations[i] = random.nextInt(riverLength);
        }

        //prevent ships from receiving the same locations
        while (shipLocations[0] == shipLocations[1] && shipCount > 1 ) {
            shipLocations[1] = random.nextInt(riverLength);
        }

        //assign ship location to array
        for (int i = 0; i < riverLength; i++){
                river[i] = 0;//unknown
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
            // if ship is not dead
            if ( river[guess] != 2) {
                if (guess != shipLocations[0] && guess != shipLocations[1]) {
                    //show location of the ship
                    showShip = false;
                    river[guess] = -1;
                    displayRiver(river, showShip);

                    System.out.println("You missed the ship");
                } else {
                    //if guess is correct show ship
                    showShip = true;
                    river[guess] = 1;
                    displayRiver(river, showShip);
                    System.out.println("Boom!");
                    //reducing ship count
                    shipCount--;
                    river[guess] = 2;
                }
            }
            else {
                System.out.println("You already found this ship");
            }
        }
        while (shipCount != 0 );
        System.out.println("Game Over");

    }//main
}//class
