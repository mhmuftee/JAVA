package com.mhmuftee.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetectTags {
	public static void main(String[] args){
		
		String aString = "asdfasfdasdf[brb]546[/brb]adsfasdfasdfasdf[brb]434[/brb]";
		
		Pattern pattern= Pattern.compile("\\[brb\\](\\d+)\\[\\/brb\\]");
		  Matcher matcher=pattern.matcher(aString);
		  
		  while(matcher.find())
		  {
		   String s=matcher.group(1);
		   System.out.println("<------ s :" + s + " ------>");

		  }
	}
}
