/**
 * 
 */
package utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author daring
 *
 */
public class AudioRecognitionUtil {
//	format	sting	必填	语音压缩的格式，请填写上述格式之一，不区分大小写
//	rate	int	必填	采样率，支持 8000 或者 16000
//	channel	int	必填	声道数，仅支持单声道，请填写 1
//	cuid	string	必填	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
//	token	string	必填	开放平台获取到的开发者 access_token
//	ptc	int	选填	协议号，下行识别结果选择，默认 nbest 结果
//	lan	string	选填	语种选择，中文=zh、粤语=ct、英文=en，不区分大小写，默认中文
//	url	string	选填	语音下载地址
//	callback	string	选填	识别结果回调地址
//	speech	string	选填	真实的语音数据 ，需要进行base64 编码
//	len	int	选填	原始语音长度，单位字节
	
//	
//	其中，开发者可以把语音数据放在 JSON 序列的“speech”字段中，
//	需要将语音先进行 base64编码，并标明语音数据的原始长度，
//	填写“len”字段；也可以直接提供语音下载地址放在“url”字段中，
//	并且提供识别结果的回调地址，放在“callback”参数中。
//	因此“speech”和“len”参数绑定，“url”和“callback”参数绑定，
//	这两组参数二选一填写，如果都填，默认处理第一种。
//	表单类型在 HTTP-HEADER 里的 content-type 表明，例：
//	Content-Type:application/json
	
	
	
	
String	cuid;	//string	必填	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内
String	token;	//string	必填	开放平台获取到的开发者 access_token
	private AudioRecognitionUtil() {
		
	}
	final static AudioRecognitionUtil audioRecognitionUtil = new AudioRecognitionUtil();
	
	public static AudioRecognitionUtil getAudiorecognitionutil() {
		return audioRecognitionUtil;
	}
	
	public String getAudioText(String urlAudio){
		Map<String, String> map = new HashMap<String, String>();
		
		URI uri = null;
		try {
			uri = new URI("http://vop.baidu.com/server_api");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		 JSONObject jsonParam = new JSONObject();  
	    try {
	    	jsonParam.put("format" , "amr");
			jsonParam.put("rate", 8000);
			jsonParam.put("channel", 1);
			jsonParam.put("cuid", "daring_5920");
			jsonParam.put("token", token);
			jsonParam.put("url", urlAudio);
			jsonParam.put("callback", "");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		} 
	    map.put("Content-Type", "application/json");
	    //map.put("Content-length", String.valueOf(jsonParam.length()));
	    StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
	    entity.setContentEncoding("UTF-8");  
	    PostRequestUtil post = new PostRequestUtil(map);
        Map<String, String> mapResult = post.getResult(uri, entity);
        System.out.println(mapResult);
		return null;
		
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
