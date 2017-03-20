/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import org.junit.Test;

import utils.GetRequestUtils;
import utils.IOserializableUtils;
import utils.Json_MapUtils;

/**
 * @author daring
 *
 */
public class TestGetRequestUtils {

	/**
	 * Test method for {@link utils.GetRequestUtils#getResult(java.net.URI)}.
	 */
	@Test
	public void test() {
		GetRequestUtils gr = new GetRequestUtils(null);
		URI uri = null;
		try {
			
			String name = URLEncoder.encode("春天里", "UTF-8");
//			src: lofter //可为空
//			type: 1
//			filterDj: true|false //可为空
//			s: //关键词
//			limit: 10 //限制返回结果数
//			offset: 0 //偏移
//			callback: //为空时返回json，反之返回jsonp callback
			uri = new URI("http://s.music.163.com/search/get/?src=&type=1&filterDi=true&s="+name+"&limit=3&callback=");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = gr.getResult(uri);
		//System.out.println(map.toString());
		if(map.get("code").equals("200")){
		Map<String, String> result = Json_MapUtils.ParseJson(map.get("result"));
		String strSong = result.get("songs");
		if(strSong.startsWith("[")){
			strSong = strSong.substring(strSong.indexOf("[")+1,strSong.lastIndexOf("]"));
		}
		Map<String, String> songs = Json_MapUtils.ParseJson(strSong);
		System.out.println(songs);
		}
		
	}

}
