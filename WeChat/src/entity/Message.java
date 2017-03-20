/**
 * 
 */
package entity;

/**
 * @author daring
 *
 */
public class Message {
	String ToUserName;	//开发者微信号
	String FromUserName;	//发送方帐号（一个OpenID）
	String CreateTime;	//消息创建时间 （整型）
	String MsgType;	//语音为voice
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
	
}
