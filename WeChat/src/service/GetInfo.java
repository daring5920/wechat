/**
 * 
 */
package service;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import utils.GetRequestUtils;
import utils.PostRequestUtil;

/**
 * @author daring
 *
 */
public class GetInfo {
	
	
	public static String access_token = "fu09q8WJA-iZITgFrwXERFyFVk5ni-fPnyKmZHUNR-xNsmmmOuWX5P2tuOfnV24OrWuAFkqMfwOJuwhjPtFgLMTJcDIRcdM5YQ5cOlFIz_levnGl61-MzLTr74uqfCWMPBVaACAFRL";
	
	
	
	/**
	 * 获取永久素材
	 * @param type
	 * @param offset
	 * @param count
	 * @return
	 */
	public static Map<String,String> getMediaId(String type,int offset,int count){
		PostRequestUtil pr = new PostRequestUtil(null);
		URI uri = null;
		try {
			uri = new URI("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+access_token);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		 JSONObject jsonParam = new JSONObject();  
	        try {
				jsonParam.put("type", type);//素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
				jsonParam.put("offset", offset);// 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
		        jsonParam.put("count", count);// 返回素材的数量，取值在1到20之间
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			} 
	        
	        System.out.println(jsonParam.toString());
	        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
	        entity.setContentEncoding("UTF-8");  
	        Map<String, String> map = pr.getResult(uri, entity);
		return map;
	}
	/**
	 * 获取临时素材
	 * @param media_id
	 * @return
	 */
	public static Map<String,String> getMedia_Id(String media_id){
		
		//1przG7wCOrB8UUwj_Z3PiIWzG51Qy6yKrRp60axXUZP3Ue_UC1DhKX0OfAa__Ejp
		//-CBVCESTN8sNUP0LXtTvtgG5ZuZw0y6keITcnqFHD0nXLqsvNx0bBT3eDpP-6gPCyCnwmg3ATdzuhfQzPaUT64yHy7DYc7F8mZWJJ-lcqJ0URqrWfrAQfex0ano76oKgGYCaAGAVNR
		GetRequestUtils gr = new GetRequestUtils(null);
	//	https://api.weixin.qq.com/cgi-bin/media/get?access_token=VLoq4ehnugjh_IK8nHAhB5yz0wvUNuKLzF5kC0Tu7Omj0dIVmXFhEKAB2dz1NEchRDzqsSFenieZ2hmLzny8K8u25gBAPaMK_sPn_3xHAf9CyOVhuGrEdffhfWyQ72STIBIgABAXQX&media_id=1przG7wCOrB8UUwj_Z3PiIWzG51Qy6yKrRp60axXUZP3Ue_UC1DhKX0OfAa__Ejp
		URI uri = null;
		try {
			uri = new URI("https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	        Map<String, String> map = gr.getResult(uri);
		return map;
	}
	
	/**
	 * 获取微信服务器ip
	 * @param access_token
	 * @return
	 */
	public static Map<String,String> getIp(){
		
		GetRequestUtils gr = new GetRequestUtils(null);
	    //测试access_token
	    //access_token = "2i-k813W5F58XTL-08_CXB8BkO-nu6rJNZhub_HhHUo23ypOIfLdxVuIGFw-1JU-UzGaoyUPXi4jm6tpbQK5mnWQEwkZUwWOlGCHtW8_cXkGTYgAAAVIN";
		StringBuffer url = new StringBuffer();
	    url.append("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="+access_token);
		URI uri = null;
		try {
			uri = new URI(url.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		Map<String, String> map = gr.getResult(uri);
		return map;
	}
	/**
	 * 上传素材
	 * @param type
	 * @param file
	 * @return
	 * 图片（image）: 1M，支持JPG格式
	 *语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 *视频（video）：10MB，支持MP4格式
	 *缩略图（thumb）：64KB，支持JPG格式
	 */
	public static Map<String,String> uploadFile(String type,File file){
		PostRequestUtil pr = new PostRequestUtil(null);
		URI uri = null;
		try {
			uri = new URI("https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+access_token+"&type="+type);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		String filename = file.getName();
		HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody("media", file, ContentType.MULTIPART_FORM_DATA, filename).build();
	    Map<String, String> map = pr.getResult(uri, reqEntity);
		return map;
	}
	
}
