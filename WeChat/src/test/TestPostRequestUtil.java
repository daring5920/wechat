/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import utils.PostRequestUtil;

/**
 * @author daring
 *
 */
public class TestPostRequestUtil {

	@Test
	public void test() {
		PostRequestUtil pr = new PostRequestUtil(null);
		URI uri = null;
		try {
			uri = new URI("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=111");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		  // 接收参数json列表  
        JSONObject jsonParam = new JSONObject();  
        try {
			jsonParam.put("chnl_id", "11");
			jsonParam.put("title", "");// 标题  
	        jsonParam.put("content", "");// 资讯内容  
	        jsonParam.put("source_url", "");// 资讯源地址  
	        jsonParam.put("source_name", "");// 来源网站名称  
	        jsonParam.put("img_urls", "");// 采用 url,url,url 的格式进行图片的返回  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}// 红谷滩新闻资讯，channelId 77  
        
          
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        //entity.setContentType("application/json");  
        Map<String, String> map = pr.getResult(uri, entity);
        System.out.println(map);
	}

}
