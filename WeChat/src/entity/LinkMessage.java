/**
 * 
 */
package entity;


/**
 * @author daring
 *
 */
public class LinkMessage extends Message{
	String Title;	//消息标题
	String Description;	//消息描述
	String Url;	//消息链接
	
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
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
//	public String getMsgId() {
//		return MsgId;
//	}
//	public void setMsgId(String msgId) {
//		MsgId = msgId;
//	}
	
	
}
