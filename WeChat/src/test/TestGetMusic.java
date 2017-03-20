/**
 * 
 */
package test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import service.GetMusic;

/**
 * @author daring
 *
 */
public class TestGetMusic {

	@Test
	public void test() {
		String str = "爱在哪里";
		List<Map<String, String>> list = GetMusic.getMusic(str);
		for(Map<String, String> map:list)
		{
			System.out.println(map.get("id"));
			System.out.println(map.get("artistsName"));
			System.out.println(map.get("name"));
			System.out.println(map.get("audio"));
		}
		System.out.println(str);
	}

}
