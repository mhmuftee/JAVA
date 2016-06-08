package com.mhmuftee.regex;

public class SpaceRemover {
	public static void main(String[] args) {
		String numbers = "367,881 1,092,781  17,819     220   89,905   194,627    342    1,763,575";

		numbers = numbers.replaceAll("(\\d)\\s(\\d)", "$1  $2");
		// System.out.println(numbers.matches("[^ ][ ][^ ]"));
		// System.out.println(numbers.length());
		System.out.println(numbers);

	}
}
