/**
 * 
 */
package entity;

/**
 * @author daring
 *
 */
public class Image {
	String MediaId;	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
