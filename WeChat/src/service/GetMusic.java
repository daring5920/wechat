/**
 * 
 */
package service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.GetRequestUtils;
import utils.Json_MapUtils;

/**
 * @author daring
 *
 */
public class GetMusic {
	public static List<Map<String, String>> getMusic(String songName){
		GetRequestUtils gr = new GetRequestUtils(null);
		URI uri = null;
		try {
			
			String name = URLEncoder.encode(songName, "UTF-8");
//			src: lofter //可为空
//			type: 1
//			filterDj: true|false //可为空
//			s: //关键词
//			limit: 10 //限制返回结果数
//			offset: 0 //偏移
//			callback: //为空时返回json，反之返回jsonp callback
			uri = new URI("http://s.music.163.com/search/get/?src=&type=1&filterDj=false&s="+name+"&limit=5&offset=0&callback=");
			//uri = new URI("http://s.music.163.com/search/get/song?id="+name);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = gr.getResult(uri);
		List<Map<String, String>> songs = new ArrayList<Map<String,String>>();
		if(map.get("result") == null)
		{
			return null;
		}
		if(map.get("code").equals("200")){
			Map<String, String> result = Json_MapUtils.ParseJson(map.get("result"));
			String strSong = result.get("songs");
			if(strSong.startsWith("[")){
				List<String> list = Json_MapUtils.jsonToList(strSong);
				if(list.size() == 0)
					return null;
				for(String str:list)
				{
					Map<String, String> song = Json_MapUtils.ParseJson(str);
				
					String artistsName = "-";
					if(song.get("artists").startsWith("[")){
						List<String> lists = Json_MapUtils.jsonToList(song.get("artists"));
						for(String strs:lists)
						{
							Map<String, String> artists = Json_MapUtils.ParseJson(strs);
							
							artistsName=artistsName+" "+artists.get("name");
						}
						if(lists.size() == 1){
							artistsName = artistsName.substring(2);
						}
						else
							artistsName = artistsName.substring(1);
					}
					song.put("artistsName", artistsName);
					songs.add(song);
				}
					
			}
		}
		return songs;
	}
}
