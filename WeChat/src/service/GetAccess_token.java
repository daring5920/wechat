/**
 * 
 */
package service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import utils.Json_MapUtils;

/**
 * @author daring
 *
 */
public class GetAccess_token {
	String grant_type = "client_credential";	//是	获取access_token填写client_credential
	String appid;	//是	第三方用户唯一凭证
	String secret;	//是	第三方用户唯一凭证密钥，即appsecret
	
	 CookieStore cookieStore;
    // Use custom credentials provider if necessary.
	  CredentialsProvider credentialsProvider;
    // Create global request configuration
	 RequestConfig defaultRequestConfig;

    // Create an HttpClient with the given custom dependencies and configuration.
	 CloseableHttpClient httpclient;
	
	 
	public GetAccess_token(String grant_type, String appid, String secret) {
		super();
		this.grant_type = grant_type;
		this.appid = appid;
		this.secret = secret;
	}

	public Map<String,String> getToken(){
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
	    
	 // Create an HttpClient with the given custom dependencies and configuration.
	    httpclient = HttpClients.custom()//全局设置
	        .setDefaultCookieStore(cookieStore)
	        .setDefaultCredentialsProvider(credentialsProvider)
	        .setDefaultRequestConfig(defaultRequestConfig)
	        .build();
	    
	    StringBuffer url = new StringBuffer();
	    url.append("https://api.weixin.qq.com/cgi-bin/token?grant_type="+getGrant_type());
	    url.append("&appid="+getAppid());
	    url.append("&secret="+getSecret());
	    URI uri = null;
		try {
			uri = new URI(url.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println("非网络连接");
		}
	    HttpGet get = new HttpGet(uri);
	    try {
			CloseableHttpResponse response = httpclient.execute(get);
			if(response.getStatusLine().getStatusCode() != 200)
				return null;
			StringBuffer strHtml = new StringBuffer();
			InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
			char[] chars = new char[4];
			int len = reader.read(chars);
			while(len != -1){
				strHtml.append(chars);
				len = reader.read(chars);
			}
			Map<String,String> map = Json_MapUtils.ParseJson(strHtml.toString());
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
	
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	

}
