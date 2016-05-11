package com.Spreadsheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class ManageWordMapping {
	private HashMap<String,String> wordsMap;
	
	ManageWordMapping(){
//		System.out.println("----manageWordMap called-----");
		wordsMap = new HashMap<String, String>();
//		System.out.println(wordsMap.size());
	}

	/**
	 * @return the wordsMap
	 */
	public HashMap<String,String> getWordsMap() {
		return wordsMap;
	}

	/**
	 * @param wordsMap the wordsMap to set
	 */
	public void setWordsMap(HashMap<String,String> wordsMap) {
		this.wordsMap = wordsMap;
	}
	
	public void printMap(){
		Iterator<Entry<String, String>> mapIterator = wordsMap.entrySet().iterator();
		while(mapIterator.hasNext()){
			Map.Entry<String,String> pair = (Map.Entry<String,String>)mapIterator.next();
			System.out.println(pair.getKey() + " :: " + pair.getValue());
		}
	}
	
	public ArrayList<Data> getDictionary(){
		ArrayList<Data> datas = new ArrayList<Data>();
		
		Set<String> keys = wordsMap.keySet();
		
		for(String  word:keys){
			String meaning = wordsMap.get(word);
			Data tempData = new Data(word,meaning);
			datas.add(tempData);
		}
		
		Collections.sort(datas, new Comparator<Data>() {

			public int compare(Data o1, Data o2) {
				// TODO Auto-generated method stub
				return o1.getWord().compareTo(o2.getWord());
			}
		});
		
		return datas;
	}
}
