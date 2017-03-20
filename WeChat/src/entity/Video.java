/**
 * 
 */
package entity;

/**
 * @author daring
 *
 */
public class Video {
	String MediaId;	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	String Title;	//否	视频消息的标题
	String Description;	//否	视频消息的描述
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
}
