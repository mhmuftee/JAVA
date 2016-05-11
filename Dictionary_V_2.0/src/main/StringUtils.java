package main;

public class StringUtils {
	
	public static String removeExtraSpaces(String s){
		return s.trim();
	}
	
	public static String makeProperWord(String oldWord) {
		String newWord = null;
		char[] temp = oldWord.toCharArray();
		temp[0] = Character.toUpperCase(temp[0]);
		newWord = String.valueOf(temp);
		return newWord;
	}
}
