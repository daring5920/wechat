/**
 * 
 */
package test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import entity.Music;
import entity.MusicMessage;
import service.GetMusic;
import utils.MessagekUtil;

/**
 * @author daring
 *
 */
public class TestGetInfo {

	@Test
	public void test() {
//		String ss = "天上人间。，、?.,';][打发33kk,.";
//		ss = ss.replaceAll("[\\p{Punct}\\p{Space}\\pP]+", ""); 
//		System.out.println(ss);
//		String content = "dd";
		List<Map<String, String>> songs = GetMusic.getMusic("你好");
		
		for(Map<String, String> song:songs)
		{
			MusicMessage mm = new MusicMessage();
			Music music = new Music();
			
			music.setDescription("演唱："+song.get("artistsName"));
			music.setHQMusicUrl(song.get("audio"));
			music.setMusicURL(song.get("audio"));
			mm.setMusic(music);
			String str = MessagekUtil.initMusic("", "", "");
			System.out.println(str);
		}
		//ySso-L9uMO07g-regcD1W3a6LNs2nRBIiL4y3KThEmPkfwTU0LA8fitP7xJPk9cF
		//GetInfo.getMedia_Id("ySso-L9uMO07g-regcD1W3a6LNs2nRBIiL4y3KThEmPkfwTU0LA8fitP7xJPk9cF");

	}

}
