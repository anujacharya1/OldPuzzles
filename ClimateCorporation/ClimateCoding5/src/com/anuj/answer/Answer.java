/*****
 * 
 * 
 * Author: Anuj Acharya
 * SJSU
 * Test
 * 408-637-6041
 * 
 * 
 */


package com.anuj.answer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Answer {

	static final int ROWS = 3; /* Number of Rows is Fixed */

	public static void printSuggestions(File f, String seq) {

		//log("Parsing file " + f.getAbsolutePath());
		char array2d[][] = new char[ROWS][];

		for (int p = 0; p < 3; p++) {

			Character qq = seq.charAt(p);
			char[] chara1 = KEYPAD_MAP.get(qq).toCharArray();
			array2d[p] = new char[chara1.length];
			for (int i = 0; i < chara1.length; i++)
				array2d[p][i] = chara1[i];
		}

		/* Creating array in array because we can have varied number of columns */
		int col1 = array2d[0].length;
		int col2 = array2d[1].length;
		int col3 = array2d[2].length;

		char[] array1 = new char[col1];
		char[] array2 = new char[col2];
		char[] array3 = new char[col3];

		for (int i = 0; i < col1; i++) {
			array1[i] = array2d[0][i];

		}
		String str1 = String.valueOf(array1);

		for (int i = 0; i < col2; i++)
			array2[i] = array2d[1][i];
		String str2 = String.valueOf(array2);

		for (int i = 0; i < col3; i++)
			array3[i] = array2d[2][i];
		String str3 = String.valueOf(array3);

		String finalstr = null;

		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < col1; i++) {
			for (int j = 0; j < col2; j++) {
				for (int k = 0; k < col3; k++) {

					finalstr = str1.valueOf(str1.charAt(i))
							+ str2.valueOf(str2.charAt(j))
							+ str3.valueOf(str3.charAt(k));

					newList.add(finalstr);
				}
			}
		}
		//log("New List"+newList);
		Iterator<String> iterator = newList.iterator();

		HashSet<String> wordSet = new HashSet<String>(newList);

		// System.out.println( "Enumerate the HashSet of NumberPad" );
		Iterator iterator1;
		iterator1 = wordSet.iterator();
		while (iterator1.hasNext()) {
			iterator1.next();
		}
		// System.out.println();

		BufferedReader br = null;
		Set fileset = new HashSet();
		HashMap<String, Integer> filedatasetcounter = new LinkedHashMap<String, Integer>(); /* For sorting will use */

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(f.getAbsolutePath()));

			while ((sCurrentLine = br.readLine()) != null) {

				Pattern p = Pattern.compile("[\\w']+");
				Matcher m = p.matcher(sCurrentLine);

				while (m.find()) {
					fileset.add(sCurrentLine.substring(m.start(), m.end()));
					Integer oldCount = filedatasetcounter.get(sCurrentLine
							.substring(m.start(), m.end()));
					if (oldCount == null) {
						oldCount = 0;
					}
					filedatasetcounter.put(
							sCurrentLine.substring(m.start(), m.end())
									.toString(), oldCount + 1);
					// System.out.println(sCurrentLine.substring(m.start(),
					// m.end())+filedatasetcounter.get(sCurrentLine.substring(m.start(),
					// m.end())));

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		//log("fileset"+fileset);
		//log("filedatasetcounter"+filedatasetcounter);
		// System.out.println( "Enumerate the HashSet of File" );
		Iterator iterator2;
		iterator2 = fileset.iterator();
		while (iterator2.hasNext()) {
			iterator2.next();
		}
		System.out.println();

		Set result = new HashSet();

		fileset.retainAll(wordSet);
		Map<String, Integer> sortedMaptoOutput = new LinkedHashMap<String, Integer>();
		sortedMaptoOutput = sortByValue(filedatasetcounter);
        //log(""+sortedMaptoOutput);
		log("Exact matches for " + seq + ": ");

		Map<String, Integer> sortedMapFinally = new LinkedHashMap<String, Integer>();

		for (Iterator<String> iter = fileset.iterator(); iter.hasNext();) {
			String matchedKey = iter.next();
			int max = sortedMaptoOutput.get(matchedKey);
			sortedMapFinally.put(matchedKey, max);

		}
		Map printKey = sortByValue(sortedMapFinally);
		Iterator it = printKey.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			log("" + pairs.getKey());
			it.remove(); // avoids a ConcurrentModificationException
		}

		/********************************************************* FOR EXTRA CREDIT **********************************************************************/

		/* get the content of file again */
		BufferedReader br2 = null;
		Set prefixMatch = new HashSet();
		try {

			String sCurrentLine;

			br2 = new BufferedReader(new FileReader(f.getAbsolutePath()));
			List<String> creditlist = new ArrayList<String>(fileset);

			while ((sCurrentLine = br2.readLine()) != null) {

				for (int i = 0; i < creditlist.size(); i++) {
					Pattern p = Pattern.compile("^"+creditlist.get(i)
							+ "[a-zA-Z]+ ");
					// System.out.println("Pattern is--->"+ p);
					Matcher m = p.matcher(sCurrentLine);

					while (m.find()) {
						prefixMatch.add(sCurrentLine.substring(m.start(), m.end()));
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br2 != null)
					br2.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		log("Prefix matches for " + seq + ": ");
	
		Map <String,Integer> stringlength = new LinkedHashMap<String, Integer>();
		for (Iterator<String> iterExtraCredit = prefixMatch.iterator(); iterExtraCredit.hasNext();) {
			String matchedKey = iterExtraCredit.next();
			int length = matchedKey.length();
			stringlength.put(matchedKey, length);
		}
		Map printKeyExtraCredit = sortByValue(stringlength);
		Iterator itExtraCredit = printKeyExtraCredit.entrySet().iterator();
		int count =0; /* For getting first 5 prefix string based on the Length */
		while (itExtraCredit.hasNext() && count < 5) {
			Map.Entry pairs = (Map.Entry) itExtraCredit.next();
			count++;
			log("" + pairs.getKey());
			itExtraCredit.remove(); // avoids a ConcurrentModificationException
		}
		
		
	}

	static Map sortByValue(Map map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {

			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
				/*
				 * return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo
				 * (((Map.Entry) (o2)).getValue());
				 *//* For ASC Sorting */
			}
		});

		Map result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			log("Usage: java Suggest filename seq");
			System.exit(1);
		}
		File f = new File(args[0]);
		if (!f.exists() || !f.isFile()) {
			log(args[0] + " is not a valid file");
			System.exit(2);
		}
		String seq = args[1];

		printSuggestions(f, seq);
	}

	private static final Map<Character, String> KEYPAD_MAP = new HashMap<Character, String>() {
		{
			put('2', "abc");
			put('3', "def");
			put('4', "ghi");
			put('5', "jkl");
			put('6', "mno");
			put('7', "pqrs");
			put('8', "tuv");
			put('9', "wxyz");
		}
	};

	private static void log(String s) {
		System.out.println(s);
	}

}
