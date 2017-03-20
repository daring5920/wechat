/**
 * 
 */
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Music;
import entity.MusicMessage;
import utils.MessagekUtil;
import utils.IOserializableUtils;

/**
 * @author daring
 *
 */
public class WeChatSign extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WeChatSign() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		
		
		boolean sign = MessagekUtil.checkSignature(signature, timestamp, nonce);
		//验证签名
		if(sign){
			PrintWriter pw = response.getWriter();
			pw.print(echostr);
		}
//		PrintWriter pw = response.getWriter();
//		pw.print("wajot");

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String,String> map = MessagekUtil.messageToMap(request);
		PrintWriter pw = response.getWriter();
		String ToUserName = map.get("ToUserName");	//开发者微信号
		String FromUserName = map.get("FromUserName");	//发送方帐号（一个OpenID）
		//String CreateTime = map.get("CreateTime");	//消息创建时间 （整型）
		String MsgType = map.get("MsgType");	//text
		//String MsgId = map.get("MsgId");	//消息id，64位整型
		
		if(MessagekUtil.MESSAGE_TEXT.equals(MsgType)){//文本消息
			String content = map.get("Content");
			IOserializableUtils.writeLog("文本消息："+content+" ip :"+request.getRemoteAddr()+" ");
			if("1".equals(content))
			{
				pw.print(MessagekUtil.initText(ToUserName, FromUserName, MessagekUtil.getMenuOne()));
			}
			else if("2".equals(content))
			{
				pw.print(MessagekUtil.initNews(ToUserName, FromUserName, "1"));
			}
			
			else if("3".equals(content)){
				pw.print(MessagekUtil.initMusic(ToUserName, FromUserName, ""));
			}
			else if("?".equals(content) || "？".equals(content))
			{
				pw.print(MessagekUtil.initText(ToUserName, FromUserName, MessagekUtil.getMainMenu()));
			}
			else{
				
				//content = content.replaceAll("[\\p{Punct}\\p{Space}\\pP]+", "");
				List<Map<String, String>> songs = GetMusic.getMusic(content);
				if(songs == null)
				{
					pw.print(MessagekUtil.initText(ToUserName, FromUserName, "没有搜索到歌曲"));
					return;
				}
				Map<String, String> song = songs.get(0);
					content = song.get("name");
					MusicMessage mm = new MusicMessage();
					Music music = new Music();
					music.setTitle(content);
					music.setDescription("演唱："+song.get("artistsName"));
					music.setHQMusicUrl(song.get("audio"));
					music.setMusicURL(song.get("audio"));
					mm.setMusic(music);
					IOserializableUtils.writeLog(MessagekUtil.listenMusic(ToUserName, FromUserName, mm));
					pw.print(MessagekUtil.listenMusic(ToUserName, FromUserName, mm));

			}
		}
		else if(MessagekUtil.MESSAGE_IMAGE.equals(MsgType)){//图片消息
			String url = map.get("PicUrl");	
			pw.print(MessagekUtil.initText(ToUserName, FromUserName, url));
		}
		else if(MessagekUtil.MESSAGE_VOICE.equals(MsgType)){
			//Recognition
			String content = map.get("Recognition");
//			MediaId	语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
//			Format	语音格式，如amr，speex等
			String mediaId = map.get("MediaId");
			String format = map.get("Format");
			IOserializableUtils.writeLog("语音文本消息："+content+" ip :"+request.getRemoteAddr());
			IOserializableUtils.writeLog("语音消息----MediaId:"+mediaId+" Format :"+format);
			content = content.replaceAll("[\\p{Punct}\\p{Space}\\pP]+", "");
			
				
			content = content.replaceAll("[\\p{Punct}\\p{Space}\\pP]+", "");
			List<Map<String, String>> songs = GetMusic.getMusic(content);
			
			if(songs == null)
			{
				pw.print(MessagekUtil.initText(ToUserName, FromUserName, "没有搜索到歌曲"));
				return;
			}
			Random random = new Random();
			Map<String, String> song = songs.get(random.nextInt(songs.size()));
				content = song.get("name");
				MusicMessage mm = new MusicMessage();
				Music music = new Music();
				music.setTitle(content);
				music.setDescription("演唱："+song.get("artistsName"));
				music.setHQMusicUrl(song.get("audio"));
				music.setMusicURL(song.get("audio"));
				mm.setMusic(music);
				IOserializableUtils.writeLog(MessagekUtil.listenMusic(ToUserName, FromUserName, mm));
				pw.print(MessagekUtil.listenMusic(ToUserName, FromUserName, mm));
				
			
		}
		else if(MessagekUtil.MESSAGE_VIDEO.equals(MsgType)){
			String Content = map.get("MediaId");	//视频消息
			pw.print(MessagekUtil.initText(ToUserName, FromUserName, Content));
		}
		else if(MessagekUtil.MESSAGE_SHROTVIDEO.equals(MsgType)){
			String Content = map.get("MediaId");	//段视频
			pw.print(MessagekUtil.initText(ToUserName, FromUserName, Content));
		}
		else if(MessagekUtil.MESSAGE_LOCATION.equals(MsgType)){
			String Content = map.get("Label");	//位置消息
			pw.print(MessagekUtil.initText(ToUserName, FromUserName, Content));
		}
		else if(MessagekUtil.MESSAGE_LINK.equals(MsgType)){
			String Content = map.get("Title");	//链接消息
			pw.print(MessagekUtil.initText(ToUserName, FromUserName, Content));
		}
		else if(MessagekUtil.MESSAGE_EVENT.equals(MsgType)){//事件消息
			String event = map.get("Event");
			if(MessagekUtil.MESSAGE_SUBSCRIBE.equals(event))
			{
				pw.print(MessagekUtil.initText(ToUserName, FromUserName, MessagekUtil.getMainMenu()));
			}
			else if(MessagekUtil.MESSAGE_UNSUBSCRIBE.equals(event)){
				
			}
		}
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		Access_Token_Control atc = new Access_Token_Control();
		new Thread(atc).start();
	}

}
