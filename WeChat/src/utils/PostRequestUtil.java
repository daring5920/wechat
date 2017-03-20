/**
 * 
 */
package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

/**
 * @author daring
 *
 */
public class PostRequestUtil {
	private BasicCookieStore cookieStore;
	private BasicCredentialsProvider credentialsProvider;
	private RequestConfig defaultRequestConfig;
	private CloseableHttpClient httpclient;
	private List<Header> headers;

	public PostRequestUtil(Map<String,String> map) {
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
	
	public Map<String,String> getResult(URI uri,HttpEntity entity){
		
		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);
	    try {
			CloseableHttpResponse response = httpclient.execute(post);
//			if(response.getStatusLine().getStatusCode() != 200)
//				return null;
			StringBuffer strHtml = new StringBuffer();
			InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
			char[] chars = new char[4];
			int len = reader.read(chars);
			while(len != -1){
				strHtml.append(chars);
				len = reader.read(chars);
			}
			Map<String,String> map = null;
			if(strHtml.toString().startsWith("{")){
				map = Json_MapUtils.ParseJson(strHtml.toString());
			}
			else{
				map = MessagekUtil.xmlToMap(response.getEntity().getContent());
			}
			return map;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
