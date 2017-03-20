/**
 * 
 */
package entity;

/**
 * @author daring
 *
 */
public class EventMessage {
	String ToUserName;	//开发者微信号
	String FromUserName;	//发送方帐号（一个OpenID）
	String CreateTime;	//消息创建时间 （整型）
	String MsgType;	//text
	String Event;	//事件
	
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Event;
	}
	public void setContent(String content) {
		Event = content;
	}
}
