/**
 * 
 */
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import entity.ArticlesMessage;
import entity.AudioMessage;
import entity.Image;
import entity.Music;
import entity.MusicMessage;
import entity.NewsImage;
import entity.PicMessage;
import entity.TextMessage;
import entity.Video;
import entity.VideoMessage;
import entity.Voice;

/**
 * @author daring
 *
 */
public class MessagekUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_SHROTVIDEO = "shortvideo";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_NES = "news";
	public static final String MESSAGE_THUMB = "thumb";
	public static final String MESSAGE_MUSIC = "music";
	public static final String NEWLINE = System.getProperty("line.separator");//System.lineSeparator();

	private static final String token = "daring5920";
	
	/**
	 * 微信公众平台验证
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		
		//1,排序
		String[] arr = new String[]{token,timestamp,nonce};
		Arrays.sort(arr);
		//2,连接字符串，sha1加密
		StringBuffer strB = new StringBuffer();
		for(String str:arr)
			strB.append(str);
		String signStr = DigestUtils.sha1Hex(strB.toString());
		System.out.println(signStr);
		System.out.println(signature);
		if(signStr.equals(signature))
			return true;
		return false;
		
	}
	/**
	 * 将xml消息放到map集合中
	 * @param request
	 * @return
	 */
	public static Map<String,String> messageToMap(HttpServletRequest request){
		//获取输入流
				InputStream in = null;
				try {
					in = request.getInputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SAXReader reader = new SAXReader();
				Map<String,String> map = new HashMap<String,String>();
				try {
					Document doc = reader.read(in);
					Element root = doc.getRootElement();
					List<Element> list = new ArrayList<Element>();
					list = root.elements();
					for(Element e:list){
						map.put(e.getName(), e.getText());
					}
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return map;
	}
	/**
	 * xml转成map
	 * @param in
	 * @return
	 */
	public static Map<String,String> xmlToMap(InputStream in){
		//获取输入流
				
				SAXReader reader = new SAXReader();
				Map<String,String> map = new HashMap<String,String>();
				try {
					Document doc = reader.read(in);
					Element root = doc.getRootElement();
					List<Element> list = new ArrayList<Element>();
					list = root.elements();
					for(Element e:list){
						map.put(e.getName(), e.getText());
					}
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return map;
	}
	/**
	 * 构建xml消息模式
	 * @param message
	 * @return
	 */
	public static String textMessageToXml(Object message){
		XStream xStream = new XStream();
		xStream.alias("xml", message.getClass());  
		if(message instanceof NewsImage)
		{
			xStream.alias("item", ArticlesMessage.class);
		}
		return xStream.toXML(message);
	}

	/**
	 * 封装图片信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param MediaId
	 * @return
	 */
	public static String initImage(String ToUserName,String FromUserName,String MediaId){
		PicMessage image = new PicMessage();
		Image img = new Image();
		img.setMediaId(MediaId);
		image.setImg(img);
		image.setCreateTime(String.valueOf(System.currentTimeMillis()));
		image.setFromUserName(ToUserName);
		image.setToUserName(FromUserName);
		//text.setMsgId(String.valueOf(System.currentTimeMillis()));
		image.setMsgType(MessagekUtil.MESSAGE_IMAGE);
		return MessagekUtil.textMessageToXml(image);
	}
	/**
	 * 封装视频信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param MediaId
	 * @return
	 */
	public static String initVideo(String ToUserName,String FromUserName,String MediaId){
		VideoMessage vm = new VideoMessage();
		Video video = new Video();
		video.setTitle("哈麻批");
		video.setDescription("真的是个哈麻批");
		video.setMediaId(MediaId);
		vm.setVideo(video);
		vm.setCreateTime(String.valueOf(System.currentTimeMillis()));
		vm.setFromUserName(ToUserName);
		vm.setToUserName(FromUserName);
		//text.setMsgId(String.valueOf(System.currentTimeMillis()));
		vm.setMsgType(MessagekUtil.MESSAGE_VIDEO);
		return MessagekUtil.textMessageToXml(vm);
	}
	/**
	 * 封装音乐信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param MediaId
	 * @return
	 */
	public static String initMusic(String ToUserName,String FromUserName,String MediaId){
		MusicMessage mm = new MusicMessage();
		Music music = new Music();
		music.setTitle("爱在哪里");
		music.setDescription("演唱：王力宏");
		music.setHQMusicUrl("http://222.55.13.49/m10.music.126.net/20160904011901/718b3ff9baaee5dbf60d36cf124c5875/ymusic/4589/7cdf/bdc1/2e42e8b79ff5d40742df3dadda684721.mp3");
		//music.setThumbMediaId(MediaId);
		music.setMusicURL("http://222.55.13.49/m10.music.126.net/20160904011901/718b3ff9baaee5dbf60d36cf124c5875/ymusic/4589/7cdf/bdc1/2e42e8b79ff5d40742df3dadda684721.mp3");
		mm.setMusic(music);;
		mm.setCreateTime(String.valueOf(System.currentTimeMillis()));
		mm.setFromUserName(ToUserName);
		mm.setToUserName(FromUserName);
		//text.setMsgId(String.valueOf(System.currentTimeMillis()));
		mm.setMsgType(MessagekUtil.MESSAGE_MUSIC);
		return MessagekUtil.textMessageToXml(mm);
	}
	/**
	 * 点歌台
	 * @param ToUserName
	 * @param FromUserName
	 * @param mm
	 * @return
	 */
	public static String listenMusic(String ToUserName,String FromUserName,MusicMessage mm){
		
		mm.setCreateTime(String.valueOf(System.currentTimeMillis()));
		mm.setFromUserName(ToUserName);
		mm.setToUserName(FromUserName);
		mm.setMsgType(MessagekUtil.MESSAGE_MUSIC);
		return MessagekUtil.textMessageToXml(mm);
	}
	/**
	 *封装音频信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param MediaId
	 * @return
	 */
	public static String initVoice(String ToUserName,String FromUserName,String MediaId){
		AudioMessage am = new AudioMessage();;
		Voice voice = new Voice();
		voice.setMediaId(MediaId);
		am.setVoice(voice);
		am.setCreateTime(String.valueOf(System.currentTimeMillis()));
		am.setFromUserName(ToUserName);
		am.setToUserName(FromUserName);
		//text.setMsgId(String.valueOf(System.currentTimeMillis()));
		am.setMsgType(MessagekUtil.MESSAGE_VOICE);
		return MessagekUtil.textMessageToXml(am);
	}
	/**
	 * 封装文本信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param Content
	 * @return
	 */
	public static String initText(String ToUserName,String FromUserName,String Content){
		TextMessage text = new TextMessage();
		text.setContent(Content);
		text.setCreateTime(String.valueOf(System.currentTimeMillis()));
		text.setFromUserName(ToUserName);
		text.setToUserName(FromUserName);
		//text.setMsgId(String.valueOf(System.currentTimeMillis()));
		text.setMsgType(MessagekUtil.MESSAGE_TEXT);
		
		return MessagekUtil.textMessageToXml(text);
	}
	/**
	 * 封装图文信息
	 * @param ToUserName
	 * @param FromUserName
	 * @param ArticleCount
	 * @return
	 */
	public static String initNews(String ToUserName,String FromUserName,String ArticleCount){
		NewsImage news = new NewsImage();
		news.setArticleCount(ArticleCount);
		news.setCreateTime(String.valueOf(System.currentTimeMillis()));
		news.setFromUserName(ToUserName);
		news.setToUserName(FromUserName);
		//text.setMsgId(String.valueOf(System.currentTimeMillis()));
		news.setMsgType(MessagekUtil.MESSAGE_NES);
		for(int i = 0;i<Integer.parseInt(ArticleCount);i++){
			ArticlesMessage am = new ArticlesMessage();
			am.setDescription("生命如此精彩!");
			am.setPicUrl("http://daring5920.cn/image/zhanshi1.jpg");
			am.setTitle("daring5920");
			am.setUrl("http://vg.ztgame.com/");
			news.getArticles().add(am);
		}
		
		return MessagekUtil.textMessageToXml(news);
	}
	/**
	 * 微信主菜单
	 * @return
	 */
	public static String getMainMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("感谢你关注daring5920的个人微信!!!"+MessagekUtil.NEWLINE);
		sb.append("****请按照菜单提示操作 ***"+MessagekUtil.NEWLINE);
		sb.append("*** 1,daring5920的个人介绍 ***"+MessagekUtil.NEWLINE);
		sb.append("*** 2,获取图文信息 ***"+MessagekUtil.NEWLINE);
		sb.append("*** 3,获取王力宏的“爱在哪里” ***"+MessagekUtil.NEWLINE);
		sb.append("*** ?,使用?即可再次调出该菜单 *"+MessagekUtil.NEWLINE);
		sb.append("文字点歌，直接发送文字即可"+MessagekUtil.NEWLINE);
		sb.append("中阴斜阳照,政心怯似忧"+MessagekUtil.NEWLINE);
		sb.append("语音点歌，直接发送语音即可"+MessagekUtil.NEWLINE);
		sb.append("卢汉歌一曲,久涯凉舟求"+MessagekUtil.NEWLINE);
		return sb.toString();
		
	}
	/**
	 * 微信子菜单
	 * @return
	 */
	public static String getMenuOne(){
		StringBuffer sb = new StringBuffer();
		sb.append("姓名:daring5920"+MessagekUtil.NEWLINE);
		sb.append("年龄:22"+MessagekUtil.NEWLINE);
		sb.append("个人博客:http://blog.sina.com.cn/daring5920"+MessagekUtil.NEWLINE);
		sb.append("幸运值:爆表"+MessagekUtil.NEWLINE);
		sb.append("爱情:喝酒"+MessagekUtil.NEWLINE);
		sb.append("我兰白石化,德有其心头"+MessagekUtil.NEWLINE);
		sb.append("推荐文字点歌，语音点歌完善中"+MessagekUtil.NEWLINE);
		sb.append("卢汉歌一曲,久涯凉舟求"+MessagekUtil.NEWLINE);
		return sb.toString();
		
	}
	/**
	 * 微信子菜单
	 * @return
	 */
	public static String getMenuTwo(){
		StringBuffer sb = new StringBuffer();
		sb.append("你的验证码为:"+"<font color=\"#FF0000\">"+GenerateCode.getLetter()+"</font>"+MessagekUtil.NEWLINE);
		sb.append("我兰白石化,德有其心头"+MessagekUtil.NEWLINE);
		sb.append("中阴斜阳照,政心怯似忧"+MessagekUtil.NEWLINE);
		sb.append("意酒学傍娅,永落闲云休"+MessagekUtil.NEWLINE);
		sb.append("卢汉歌一曲,久涯凉舟求"+MessagekUtil.NEWLINE);
		return sb.toString();
	}
}
