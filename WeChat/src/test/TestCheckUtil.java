/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.junit.Test;

import entity.TextMessage;
import utils.MessagekUtil;

/**
 * @author daring
 *
 */
public class TestCheckUtil {

	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		String str;
		try{
			str = list.get(3);
		}catch(ArrayIndexOutOfBoundsException e){
			str = list.get(2);
		}
		System.out.println(str);
	}


}
