/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author daring
 *
 */
public class Json_MapUtils {
	
	public static JSONArray listToJson(List<?> list){
		JSONArray jsonArray2 = JSONArray.fromObject( list );
		return jsonArray2;
	}
	public static JSONObject mapToJson(Map<?, ?> map){
		
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	public static JSONArray arrayToJson(Object[] array){
		
		JSONArray jsonArray1 = JSONArray.fromObject(array);
		return jsonArray1;
	}
	public static List<String> jsonToList(String jsonStr){
		List<String> list = new ArrayList<String>();
		try {
			org.json.JSONArray array = new org.json.JSONArray(jsonStr);
			for(int i = 0;i<array.length();i++)
				list.add(array.getString(i));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	 public static Map<String,String> ParseJson(String jsonString) {

	       
		 org.json.JSONObject jb = null;
		try {
			jb = new org.json.JSONObject(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> map = new HashMap<String,String>();
		Iterator<?> it = jb.keys();  
		 while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           String value = null;
			try {
				value = jb.getString(key);
			} catch (JSONException e) {
				try {
					value = String.valueOf(jb.getInt(key));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
			map.put(key, value);
	           
	       }  
	        return map;


	    }
}
