package com.mhmuftee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"D:\\Workspace\\Programming\\Files\\in.txt"));
		
		String line;
		
		while(true){
			line = br.readLine();
			//Integer inputStringLength = line.length();
			if(line.equals("."))
				break;
			HashMap<Character,Integer> indexing = new HashMap<>();
			for(int I=0;I<line.length();I++){
				Character c = line.charAt(I);
				Integer value = indexing.containsKey(c)?1+indexing.get(c):1;
				indexing.put(c, value);	
			}
			
			Set<Character> keys = indexing.keySet();
			
			Collection<Integer> values = indexing.values();
			
			Integer minValue = Collections.min(values);
			/*
			if(minValue==1)
				System.out.println("1");
			else
				if(minValue==inputStringLength)
					System.out.println(inputStringLength);
				else*/
			//System.out.println(minValue);
			
			Iterator<Character> it = keys.iterator();
			
		    StringBuilder temp = new StringBuilder();
			
		    int length = 0;
		    
			while(it.hasNext()){
               Character c = it.next();
               Integer value = indexing.get(c);
               int loop = value/minValue;
               length += loop;
               for(int I=0;I<loop;I++)
            	   temp.append(c);
			}
			
			String subString = line.substring(0, length);
			
			
			
			String[] t = subString.split(temp.toString());
			
			for(String s:t)
			System.out.println("s = " + s);
			
			System.out.println(subString);
			
			System.out.println("temp = " + temp.toString());
		}
		br.close();
	}
}
