/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7 ].
Comment: I correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8 ].
Comment: My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
Comment: My program ensures the bet amount is not greater than the cash in the wallet 
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15 ]..
Comment:My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned 
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 15].
Comment:My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet 
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10 ].
Comment: y program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses
2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15 ]
Comment: I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it 
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5 ]
Comment: My program loops continuously until the user either enters quit or the cash in the wallet is 0 
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment: I ask the user to enter any of the four bet types or quit
My program calls resolveBet for each bet type entered [Mark out of 5: 5 ].
Comment: My program calls resolveBet for each bet type entered
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 2]
Comment: It is present but my Scanner error prevents it running

 Total Mark out of 100 (Add all the previous marks): 97
*/


//On second time round throwing an error exception line 26 in the Scanner.
import java.util.Scanner;

public class ChuckALuckDice {
	public static final String CASH_QUESTION = "Please enter the amount of cash present in your wallet.";
	public static final String BET_QUESTION = "How much cash would you like to bet?";
	public static final String betOne = "triple";
	public static final String betTwo = "field";
	public static final String betThree = "high";
	public static final String betFour = "low";
	public static final String QUIT = "quit";
	public static Wallet wallet;


	public static void main(String[] args) {
		boolean quit = false;

		Scanner diceInput = new Scanner(System.in);
		wallet = new Wallet();
		System.out.println(CASH_QUESTION);
		double money = diceInput.nextDouble();
		wallet.put(money);
		String bet = "";

		while(!quit) {
			System.out.println("Please place one of the four bets, a triple, a field, a high, a low.");
			bet = diceInput.next();
			if(bet.equals(QUIT) || wallet.check() == 0) {
				quit = true;
			}
			else {
				resolveBet(money, bet);
			}
		}
		if(wallet.check() > money) {
			System.out.println("Well done! You've made cash!");
		} else if(wallet.check() == money) {
			System.out.println("You broke even!");
		} else {
			System.out.println("You lost cash! Better luck next time");

		}
		diceInput.close();
	}


	public static void resolveBet(double money, String bet) {
		Scanner diceInput = new Scanner(System.in);
		System.out.println("This is the amount you have in your wallet: " + wallet.check());
		System.out.println(BET_QUESTION);
		double betAmount = diceInput.nextDouble();

		boolean quit = false;

		if(quit == true) {
			System.out.println("Thank you for playing!");
		}
		if(betAmount > wallet.check() ) {
			quit = true;	
		} else if(betAmount <= wallet.check() ) {
			quit = false;
		}

		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		Dice dice3 = new Dice();

		dice1.roll();
		dice2.roll();
		dice3.roll();

		double firstRoll = dice1.topFace();
		double secondRoll = dice2.topFace();
		double thirdRoll = dice3.topFace();
		double totalDiceRoll = firstRoll + secondRoll + thirdRoll;

		if(bet.equalsIgnoreCase(betOne) && firstRoll == secondRoll && secondRoll == thirdRoll && firstRoll == thirdRoll && firstRoll != 1 && firstRoll != 6 ) {  //need conditions
			System.out.println("Success! You have bet correctly, your payout is 30:1");
			betAmount = betAmount * 30;
			wallet.put(betAmount);
		} else if(bet.equalsIgnoreCase(betTwo) && totalDiceRoll < 8 || totalDiceRoll > 12) {
			System.out.println("Success! You have bet correctly, your payout is 1:1");
			betAmount = betAmount * 2;
			wallet.put(betAmount);
		} else if(bet.equalsIgnoreCase(betThree) && totalDiceRoll > 10) {
			System.out.println("Success! You have bet correctly, your payout is 1:1");
			betAmount = betAmount * 2;
			wallet.put(betAmount);
		} else if(bet.equalsIgnoreCase(betFour) && totalDiceRoll < 11) {
			System.out.println("Success! You have bet correctly, your payout is 1:1");
			betAmount = betAmount * 2;
			wallet.put(betAmount);
		} else {
			System.out.println("You Lose! Your have bet incorrectly, you lose your bet amount");
			wallet.get(betAmount);
		}
		diceInput.close(); 

	}
}