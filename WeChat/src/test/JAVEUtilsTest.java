/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;

import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import org.junit.Test;

import service.AudioRcognition;

/**
 * @author daring
 *
 */
public class JAVEUtilsTest {

	@Test
	public void test() throws URISyntaxException, InputFormatException, EncoderException {
		File fileParent = new File(AudioRcognition.class.getClassLoader().getResource("amr_wav").toURI());
		File file = new File(fileParent.getPath()+"/1478575138216.wav");
		Encoder encoder = new Encoder();
		MultimediaInfo info = encoder.getInfo(file);
		System.out.println("getDuration:"+info.getDuration());
		System.out.println("getFormat:"+info.getFormat());
		System.out.println("getAudio:"+info.getAudio());
		AudioInfo audioInfo = info.getAudio();
		System.out.println("getBitRate:"+audioInfo.getBitRate());
		System.out.println("getChannels:"+audioInfo.getChannels());
		System.out.println("getDecoder:"+audioInfo.getDecoder());
		System.out.println("getSamplingRate:"+audioInfo.getSamplingRate());
		
	
	}

}
