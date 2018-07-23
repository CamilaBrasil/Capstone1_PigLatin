import java.util.Scanner;
import java.util.regex.Pattern;

public class PigLatin {

	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);
		String userInput;
		boolean isValid = false;
		String response;
		
		System.out.println("Welcome to the Pig Latin Translator!\n");
		
		do {
			
			System.out.println("Enter a line to be translated:");
			userInput = scnr.nextLine();
			
			//Calling method to validate input, ask for another input if invalid
			while (!isValidInput(userInput)) {
				System.out.println("This is invalid, the input must not contain numbers or special characters.");
				System.out.println("Enter a line to be translated:");
				userInput = scnr.nextLine();
			}
					
			//Calling method that will get the translation
			System.out.println("\n" + translateSentence(userInput));
			
			System.out.println("\nDo you want to keep translating? (y/n)");
			response = scnr.nextLine();
			
		} while (response.equalsIgnoreCase("y"));
		
		System.out.println("\nTchau!");
		
		scnr.close();
		
	}	
	
		
	private static boolean isValidInput (String userInput) {
		
		boolean isValidInput = false;
		
		//Create pattern using regular expressions that contain alphabeth and comma.
		final Pattern pattern = Pattern.compile("^[A-Za-z, \\.]++$");
		
		//Compare sentence with pattern
		if(pattern.matcher(userInput).matches()) {
			isValidInput = true;
		}
		
		
		return isValidInput;
	}


	private static String translateSentence(String userInput) {

		String word = null;
		StringBuilder translatedSentence = new StringBuilder();
		
		//Split the userInput between spaces and commas.
		//Doesn't split in between period and dont't add the comma back
		String[] wordsArray = userInput.split(" |, ");
		
		//Separate the words inside the array
		for (int i = 0; i < wordsArray.length; i++) {
			 //Send word to findFirstVowel
			word = wordsArray[i];
			translatedSentence.append(translateWord(word));
			translatedSentence.append(" ");
		}
		
		return 	translatedSentence.toString();
	}

	
	
	public static String translateWord (String word) {
		
		String translatedWord;
		int charIndex = findFirstVowel(word);
//		System.out.println("firstVowel " + charIndex);
		
		//Check if it starts with a vowel or a consonant, separate words if necessary
		// and add the appropriate end.
		if (charIndex == 0) {
			translatedWord = word + "way";
			} else {
				String firstHalf = word.substring(0, charIndex);
				word = (word.substring(charIndex, word.length()).concat(firstHalf));
				translatedWord = word + "ay";
			}
		
		
		return translatedWord;
	}
	
	
	
	public static int findFirstVowel (String word) {

		int charIndex = 0;
		char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
		boolean foundVowel = false;
		
		//Take the index from one letter per time and send to isVowel
		for (charIndex = 0; charIndex < word.length(); charIndex++) {
//			System.out.println("charIndex first for: " + charIndex + " " + word.charAt(charIndex));
			//Check if the index recieved is a vowel
			for (int vowelIndex = 0; vowelIndex < vowels.length; vowelIndex++) {
//				System.out.println("vowelIndex second for: " + vowelIndex + " " + vowels[vowelIndex]);
				if (word.charAt(charIndex) == vowels[vowelIndex]) {
					foundVowel = true;
//					System.out.println("Vowel matched " + vowels[vowelIndex] + " charIndex matched " + charIndex);
					break;
				}
			}
			if(foundVowel == true) {
				break;
			}
		}
		
		return charIndex;
	}
	


}
