package testAutomationProject.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class MapUtil {
	
	

	/**
	 * Create a HashMap using a list of strings
	 * 
	 * TODO: Rename this method in order to give its name more meaning
	 * 
	 * @param data : list of strings to place in HashMap
	 * @return HashMap of strings
	 */
	public static HashMap<String, String> setHashMap(List<String> data) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		final String delimiter = ",";
		for (String line : data) {
			if (line.contains(delimiter)) {
				String[] pair = line.split(delimiter);
				if(pair.length >=2){
					for(int i=0; i < pair.length; i++) {
						hashMap.put(pair[i],  pair[i+1]);
						i++;
					}
				}

			}
		}
		return hashMap;
	}
	
	
	/**
	 * Get Value from Key Method
	 * 
	 * @param map : map to retrieve value from
	 * @param key : key to retrieve
	 * @return value at key
	 */
	public static <K, V> V getHashMapValue(Map<? extends K,? extends V> map, K key) {
		if(map != null && !map.isEmpty()) {
			V value = map.get(key);
			if(value != null) { return value; }
			else {
				throw new NullPointerException("The value for Key: '" + key.toString() + "' is null!");
			}
		}
		else if(map == null){
			throw new NullPointerException();
		}
		else {
			throw new IllegalArgumentException("A Map having no Key,Value pairs cannot provide a value for Key: '" + key.toString() + "'.\n"
					                          +"Please ensure the Map has been populated with at least one Key,Value pair." );
		}
	
	}
	
	
	public static <K, V> K getKeyByValue(Map<K, V> map) {
	    for (Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key "+entry.getKey());
            System.out.println("Value "+entry.getValue());
	    }
	    
	    return null;
	}
	
	public static HashMap<String, String> hashEncryptedData(List<String> data) {
		
		List<String> decryData = new ArrayList<String>();
		
		final String delimiter = ",";		
		for (String line : data) {
			if(!line.contains(",,")) {
				
				AES.decrypt(line);
				line = AES.getDecryptedString();
				if (line.contains(delimiter)) {
					decryData.add(line);
					 //System.out.println("Decrypted:  "+line);
				}

			}
	
		}
		return setHashMap(decryData);
	}
	

}
