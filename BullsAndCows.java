import java.util.ArrayList; //used to test the result
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {

	public static void main(String[] args) {
//		1a) Test contains() Method
//		int[] x = {-2, 7};
//		int[] y = {9, 0, 2, 6};
//		int[] z = {};
//		contains(x, -3);
//		contains(y, 2);
//		contains(z, 5);
		
//		1b) Test generateSecretDigits() Method
//		System.out.println(Arrays.toString(generateSecretDigits(123)));
//		System.out.println(Arrays.toString(generateSecretDigits(45)));
//		System.out.println(Arrays.toString(generateSecretDigits(987)));
		
		
//		1c) Test getNumOfBulls() Method
//		int[] secret = {2, 0, 6, 9};
//		int[] guessOne = {9, 5, 6, 2};
//		int[] guessTwo = {1, 0, 6, 2};
//		int[] guessThree = {1, 2, 3, 4, 5, 6};
//		int[] guessFour = {1, 3, 4, 4, 0, 5};
//		getNumOfBulls(secret, guessOne);
//		getNumOfBulls(secret, guessTwo);
//		getNumOfBulls(secret, guessThree);
//		getNumOfBulls(guessThree, guessFour);
		
		
//		1d) Test getNumOfCows() Method
//		getNumOfCows(secret, guessOne);
//		getNumOfCows(secret, guessTwo);
//		getNumOfCows(secret, guessThree);
//		getNumOfCows(guessThree, guessFour);
		
//		1e) Simulate the game! playBullsAndCows() Method
		
//		playBullsAndCows(123);

		
	}
	//0) Non repeating check method
	public static int repeatCheck(int[] array) {
		for(int i=0; i< array.length; i++) {
			for(int j=0; j< array.length; j++) {
			if(i != j && array[i]==array[j]) {
				return array[i];
				//break;
			}
		}
		}
		return -1;
	}
	
	
	//1a) Method to check if an element is contained in a given array
	public static boolean contains(int[] array, int number) {
		boolean success = false;
		for(int i=0; i<array.length; i++) {
			if(array[i] == number) {
				//System.out.println(array[i]);
				success = true;
//				System.out.println("returns " + success);
				return success;
			}
		}
//		System.out.println("returns " + success);
		return success;
	}
	//1b) Method to generate the secret number
	public static int[] generateSecretDigits(int seed) {
		Random rnd =new Random(seed);
		int[] array = new int[4];
		//int count=0;
		for(int i=0; i<4; i++) {
			array[i]=rnd.nextInt(10);
			for(int j=0; j<i; j++) {
				if(array[i] == array[j]) {
					//array[i]=rnd.nextInt(10);
					i--;
			}
		}
	}
//		System.out.println("returns the array " + array);
		return array;
	}
	
	//1c) Method to get the number of bulls in a guess
	public static int getNumOfBulls(int[] secretArray, int[] guessArray) {
		int count =0;
			if(secretArray.length != guessArray.length) {
				throw new IllegalArgumentException("length error");
			}else if(repeatCheck(guessArray) !=-1) {
				throw new IllegalArgumentException("no repeat digit");
			}else {
				for(int i=0; i<secretArray.length; i++) {
				if(secretArray[i]==guessArray[i]) {
					count++;
				}
			}
			}
//		System.out.println("returns "+ count);
		return count;
	}
	
	//1d)Method to get the number of cows in a guess
	public static int getNumOfCows(int[] secretArray, int[] guessArray) {
		int count=0;
		if(secretArray.length != guessArray.length) {
			throw new IllegalArgumentException("length error");
		}else if(repeatCheck(guessArray) !=-1) {
			throw new IllegalArgumentException("no repeat digit");
		}else {
			for(int i=0; i<secretArray.length; i++) {
				for(int j=0; j<guessArray.length; j++) {
					if(i !=j && secretArray[i] == guessArray[j]) {
						count ++;
					}
			}
		}
		}
//		System.out.println("returns "+ count);
		return count;
	}
	
	//1e) Method to simulate a game
	public static int[] playBullsAndCows(int seed) {
//		generate a random secret # to be the seed
//		Random rnd = new Random();
//		int seed = Math.abs(rnd.nextInt(1000));
		
//		use the integer to generate a four-digit secret #		
		int[] secretArray = generateSecretDigits(seed);
		try (Scanner nameInput = new Scanner(System.in)) {  //close the scanner automatically
			try (Scanner input = new Scanner(System.in)) {
				try (Scanner quitOrNot = new Scanner(System.in)) {
					//generate the Welcome message
					long startTime =System.currentTimeMillis();
					System.out.print("Welcome to the game! Please enter your name : " ); 
					String playerName = nameInput.next();//ASK PLAYER TO INPUT HIS/HER NAME
					
					System.out.println("Welcome " + playerName+ "!"+" Let's play! Have fun!");
					
					int guessNo=1; 
//					ask the player to guess
//		first scenario when the guess number is below 6 times. 
					for(;guessNo<6; guessNo++ ) {
						try {			
							System.out.println("Guess #" + guessNo + ","+ " Enter a four-digit number, each digit should be unique :");
							String guessString=input.next();
							int[] inputArray = new int[guessString.length()];
							if(guessString.length()>4 ||guessString.length()<4) {
								System.out.println("You must enter a four-digit number! You just wasted one guess!");
							}if(guessString.length()==4) {
								for(int i=0; i<4; i++) {
									inputArray[i]= Integer.parseInt(guessString.charAt(i)+"");
									}
								getNumOfBulls(secretArray, inputArray);
								getNumOfCows(secretArray,inputArray);
//					System.out.println("4-digit Seed: "+seed +"/"+ Arrays.toString(secretArray));
								System.out.println("Bulls:" + getNumOfBulls(secretArray, inputArray));
								System.out.println("Cows:" +getNumOfCows(secretArray,inputArray));
								if(Arrays.equals(secretArray,inputArray) == true) {
									long stopTime = System.currentTimeMillis();
									long playTime = stopTime-startTime;
									System.out.println("Congratulations " +playerName +"! "+ "You guessed the secret number! It took you " + guessNo +" attemps." );
									System.out.println("Time used :"+ playTime);
									break;
									}
								}
						}catch(IllegalArgumentException e){
							System.out.println("You must enter a four-digit number! You just wasted one guess!");
						}
						
//		second scenario: when the player guess more than 5 times 
					}		
					for(;guessNo >5; guessNo++ ) {
						try {
							System.out.println("Do you want to quit the game? Answers: y/n ");
							String yOrN = quitOrNot.nextLine();
//				ask the player to decide quit the game or continue
							if(yOrN.equals("y")) {
								System.out.println("You've decided to quit the game, after making "+(guessNo-1) +" attemps.");
								long stopTime = System.currentTimeMillis();
								long playTime = startTime-stopTime;
								System.out.println("Time used :"+playTime);
								break;
								}
							else {
								System.out.println("Guess #" + guessNo + ","+ " Enter a four-digit number, each digit should be unique :");
								String guessString=input.next();
								int[] inputArray = new int[guessString.length()];
								if(guessString.length()>4 ||guessString.length()<4) {
									System.out.println("You must enter a four-digit number! You just wasted one guess!");
								}if(guessString.length()==4) {
									for(int i=0; i<4; i++) {
										inputArray[i]= Integer.parseInt(guessString.charAt(i)+"");
										}
									getNumOfBulls(secretArray, inputArray);
									getNumOfCows(secretArray,inputArray);
//						System.out.println("4-digit Seed: "+seed +"/"+ Arrays.toString(secretArray));
									System.out.println("Bulls:" + getNumOfBulls(secretArray, inputArray));
									System.out.println("Cows:" +getNumOfCows(secretArray,inputArray));
									if(Arrays.equals(secretArray,inputArray) == true) {
										long stopTime = System.currentTimeMillis();
										long playTime = stopTime-startTime;
										System.out.println("Congratulations " +playerName + "! "+ " You guessed the secret number! It took you " + guessNo +" attemps." );
										System.out.println("Time used :"+playTime);
										break;
										}
									}
							}
						}catch(IllegalArgumentException e){
							System.out.println("You must enter a four-digit number! You just wasted one guess!");
						}
					}
				}
			}
		}
		return secretArray;
	}
}
