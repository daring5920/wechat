/**
 * 
 */
package entity;


/**
 * @author daring
 *
 */
public class PicMessage extends Message{
	Image Image;
	//String PicUrl;	//图片链接（由系统生成）
	//String MsgId;	//消息id，64位整型
	
	public Image getImg() {
		return Image;
	}
	public void setImg(Image img) {
		this.Image = img;
	}
	

	
	
}
