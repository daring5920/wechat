/**
 * 未启用
 */
package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import utils.JAVEUtils;

import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechUtility;


/**
 * 语音识别
 * @author daring
 *
 */
public class AudioRcognition {
	
	private long lengthF;
	private BasicCookieStore cookieStore;
	private BasicCredentialsProvider credentialsProvider;
	private RequestConfig defaultRequestConfig;
	private CloseableHttpClient httpclient;
	private List<Header> headers;
	
	static{
		SpeechUtility.createUtility("appid=5815e75f");  
	}
	public AudioRcognition(Map<String,String> map) {
		cookieStore = new BasicCookieStore();
	    // Use custom credentials provider if necessary.
	    credentialsProvider = new BasicCredentialsProvider();
	    // Create global request configuration
	    defaultRequestConfig = RequestConfig.custom()//Cookie自动在session中保存
	        .setCookieSpec(CookieSpecs.STANDARD_STRICT)
	        .setExpectContinueEnabled(true)
	        .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
	        .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
	        .build();
	    //请求头设置
	    headers = new ArrayList<Header>();
	    if(map != null)
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	        headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
	    }  
	 // Create an HttpClient with the given custom dependencies and configuration.
	    httpclient = HttpClients.custom()//全局设置
	        .setDefaultCookieStore(cookieStore)
	        .setDefaultCredentialsProvider(credentialsProvider)
	        .setDefaultRequestConfig(defaultRequestConfig)
	        .setDefaultHeaders(headers)
	        .build();
	}
	/**
	 * 获取语音流
	 * @param access_token
	 * @param media_id
	 * @return
	 * @throws URISyntaxException 
	 * @throws FileNotFoundException 
	 */
	private InputStream getVoiceFile(String access_token,String media_id) throws URISyntaxException{
		String fileName = String.valueOf(System.currentTimeMillis());
		File fileParent = new File(AudioRcognition.class.getClassLoader().getResource("amr_wav").toURI());
		String filePath = null;
		if(fileParent.isDirectory()){
			filePath = fileParent.getPath()+"/"+fileName+".amr";
		}
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		URI uri = null;
		try {
			uri = new URI("https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	    InputStream reader = null;
		try {
			HttpGet get = new HttpGet(uri);
				CloseableHttpResponse response = httpclient.execute(get);
				if(response.getStatusLine().getStatusCode() != 200)
					return null;
				HttpEntity entity = response.getEntity();
				lengthF = entity.getContentLength();
				InputStream reader1 = entity.getContent();
				System.out.println(lengthF);
				FileOutputStream fout = new FileOutputStream(file);
				int i = IOUtils.copy(reader1, fout);
				System.out.println(i);
				reader1.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JAVEUtils jave = new JAVEUtils();
		jave.amrToWav(file);
		try {
			reader = new FileInputStream(file.getParent()+"/"+file.getName().substring(0, file.getName().lastIndexOf("."))+".wav");
		} catch (FileNotFoundException e) {
			return null;
		}
		return reader;
	}
	public URI getVoiceFileUrl(String access_token,String media_id) throws URISyntaxException, FileNotFoundException{
		URI uri = null;
		try {
			uri = new URI("https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	   
		return uri;
	}
	/**
	 * 语音流分割
	 * @param voiceBuffer
	 * @param size
	 * @return
	 */
	public ArrayList<byte[]> splitBuffer(InputStream voiceBuffer,int size){
		
		ArrayList<byte[]> buffers = new ArrayList<byte[]>();
		byte[] buffer = new byte[size];
	
			int fileLength = -1;
			try {
				fileLength = voiceBuffer.read(buffer);
				while(fileLength != -1){
					buffers.add(buffer);
					fileLength = voiceBuffer.read(buffer);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			voiceBuffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffers;
	}
	/**
	 * 识别语音内容
	 * @param access_token
	 * @param media_id
	 * @return
	 */
	public void getVoiceToText(String access_token,String media_id){
		InputStream voiceBuffer = null;
		try {
			
				voiceBuffer = getVoiceFile(access_token, media_id);
		
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//1.创建SpeechRecognizer对象  
		SpeechRecognizer mIat= SpeechRecognizer.createRecognizer( );  
		//2.设置听写参数，详见《iFlytek MSC Reference Manual》SpeechConstant类  
		mIat.setParameter(SpeechConstant.DOMAIN, "iat");  
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");  
		mIat.setParameter (SpeechConstant.ACCENT, "mandarin ");  
		mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");  
		//3.开始听写  
		mIat.startListening(mRecoListener);  
		//voiceBuffer为音频数据流，splitBuffer为自定义分割接口，将其以4.8k字节分割成数组  
		if(voiceBuffer == null)
		{
			return ;
		}
		ArrayList<byte[]> buffers = splitBuffer(voiceBuffer, 4800);  
		  for (int i = 0; i < buffers.size(); i++) {  
			// 每次写入msc数据4.8K,相当150ms录音数据  
			  System.out.println(buffers.get(i).length);
			                mIat.writeAudio(buffers.get(i), 0, buffers.get(i).length);  
			                try {  
			                    Thread.sleep(150);  
			                } catch (InterruptedException e) {  
			                    e.printStackTrace();  
			                }  
			            }  
	mIat.stopListening();  
		
		
	}
	//听写监听器  
	RecognizerListener mRecoListener = new RecognizerListener() {
		
		@Override
		public void onVolumeChanged(int arg0) {
			// TODO Auto-generated method stub
			System.out.println("onVolumeChanged:"+arg0);
		}
		
		@Override
		public void onResult(RecognizerResult arg0, boolean arg1) {
			// TODO Auto-generated method stub
			System.out.println("onResult:"+arg0.getResultString());
		}
		
		@Override
		public void onEvent(int arg0, int arg1, int arg2, String arg3) {
			// TODO Auto-generated method stub
			System.out.println("onEvent:"+arg0);
		}
		
		@Override
		public void onError(SpeechError arg0) {
			// TODO Auto-generated method stub
			System.out.println(arg0.getErrorDesc()+":错误码："+arg0.getErrorCode());
		}
		
		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub
			System.out.println("**********");
		}
		
		@Override
		public void onBeginOfSpeech() {
			// TODO Auto-generated method stub
			System.out.println("***********");
		}
	};
}
