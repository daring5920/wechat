/**
 * 
 */
package entity;

/**
 * @author daring
 *
 */
public class Music {
	String Title;	//否	视频消息的标题
	String Description;	//否	视频消息的描述
	String MusicUrl;	//否	音乐链接
	String HQMusicUrl;	//否	高质量音乐链接，WIFI环境优先使用该链接播放音乐
	String ThumbMediaId;	//否	缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
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
	public String getMusicURL() {
		return MusicUrl;
	}
	public void setMusicURL(String musicURL) {
		MusicUrl = musicURL;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
