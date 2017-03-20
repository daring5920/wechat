/**
 * 
 */
package entity;


/**
 * @author daring
 *
 */
public class LocationMessage extends Message{
	String Location_X;	//地理位置维度
	String Location_Y;	//地理位置经度
	String Scale;	//地图缩放大小
	String Label;	//地理位置信息
	//String MsgId;	//消息id，64位整型
	
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
//	public String getMsgId() {
//		return MsgId;
//	}
//	public void setMsgId(String msgId) {
//		MsgId = msgId;
//	}
	
	
}
