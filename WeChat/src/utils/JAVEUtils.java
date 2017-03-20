/**
 * 
 */
package utils;

import java.io.File;
import java.io.InputStream;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

/**
 * @author daring
 *
 */
public class JAVEUtils {
	//最重要的JAVE类是it.sauronsoftware.jave.Encoder。编码对象公开的多媒体转码许多方法。为了使用JAVE，你总是要创建一个编码器
	Encoder encoder = new Encoder();
	public void amrToWav(File filesource) {
		InputStream in = null;
		try {
			File source = new File(filesource.getPath());
			File target = new File(filesource.getParent()+"/"+filesource.getName().substring(0, filesource.getName().lastIndexOf("."))+".wav");
			System.out.println(source.getPath());
			System.out.println(target.getPath());
			EncodingAttributes encodingAttributes = new EncodingAttributes();
			AudioAttributes audioAttributes = new AudioAttributes();
			//采样精度为16位，单声道，字序为Little-Endian
			audioAttributes.setCodec("pcm_s16le");
			//采样率为16K或8K
			audioAttributes.setBitRate(8000);
			audioAttributes.setChannels(1);
			audioAttributes.setVolume(256);
			audioAttributes.setSamplingRate(16000);
			
			
			
			encodingAttributes.setAudioAttributes(audioAttributes);
			encodingAttributes.setFormat("wav");
//		String[] strs= encoder.getSupportedEncodingFormats();
//		for(int i = 0;i<strs.hashCode();i++)
//		{
//			System.out.println(strs[i]);
//		}
			encoder.encode(source, target, encodingAttributes);
			
			System.out.println("sfsfsf");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			
		}
	}
}
