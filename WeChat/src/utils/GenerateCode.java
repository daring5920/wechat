package utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * 
 */


/**
 * @author daring
 *
 */
public class GenerateCode {

	/**
	 * @param args
	 */
	
	static Random random = new Random();
	
	public static String getLetter(){
		
		
		List<String> list = new ArrayList<String>();
		String codeString = "abcdefghijklmnopqrstuvwxyz0123456789";
		char[] codeChars = codeString.toCharArray();
		for(int temp = 0;temp <4;temp++)
		{
			int i = random.nextInt(36);
			list.add(String.valueOf(codeChars[i]));
		}
		StringBuffer sb = new StringBuffer();
		for(String str:list)
			sb.append(str);
		return sb.toString();
	}
}
