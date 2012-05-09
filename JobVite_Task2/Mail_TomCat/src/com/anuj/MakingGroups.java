package com.anuj;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MakingGroups {

	public JSONArray returnJsonArray(MultiMap mp) {
		StringBuffer b = null;
		ArrayList<String> ar = new ArrayList<String>();
		JSONArray jsonArray = new JSONArray();
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			{
				JSONObject o = new JSONObject();
				try {
					o.put("Group:" + pairs.getKey().toString(),
							pairs.getValue());

				} catch (JSONException e) {
					e.printStackTrace();
				}
				ar.add(o.toString());
				jsonArray.put(o);
			}

		}
		return jsonArray;
	}

	public JSONArray makingGroups() {

		MultiMap mhm = new MultiHashMap();
		JSONArray jsonArry1 = null;
		String[] tokens;
		try {
			// Open the file that is the first
			URL url = MakingGroups.class.getClassLoader().getResource(
					"logFile.txt");
			FileInputStream fstream = new FileInputStream(url.getPath());
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			Integer i = 0;
			while ((strLine = br.readLine()) != null) {
				int flag = 0;
				tokens = strLine.split(" ");
				String tobefound = new String();
				String valueToInsert = new String(); // To remove duplicates
				for (int k = 0; k < tokens.length; k++) {

					if (mhm.containsValue(tokens[k].toString())) {
						flag = 1; // contain
						tobefound = tokens[k].toString();
					}

				}
				Integer keyg = 0;
				if (flag == 1) {
					Set keySet = mhm.keySet();
					Iterator keyIterator = keySet.iterator();

					while (keyIterator.hasNext()) {
						Object key = keyIterator.next();
						Collection values = (Collection) mhm.get(key);

						Iterator valuesIterator = values.iterator();
						while (valuesIterator.hasNext()) {

							String value = (String) valuesIterator.next();
							if (value.equals(tobefound)) {
								keyg = (Integer) key;
							}
						}
					}
					// Inserting only which is not present. So no duplicates
					// elements inserted and we don't required set
					if (tobefound.equals(tokens[0])) {
						valueToInsert = tokens[1];
					} else
						valueToInsert = tokens[0];
					mhm.put(keyg, valueToInsert);
				} else {
					i += 1;
					mhm.put(i, tokens[0]);
					mhm.put(i, tokens[1]);
				}

			}
			// Close the input stream
			in.close();
			jsonArry1 = returnJsonArray(mhm);

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return jsonArry1;

	}

}
