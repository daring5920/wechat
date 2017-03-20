/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.baidu.speech.serviceapi.Sample;

import service.AudioRcognition;
import utils.AudioRecognitionUtil;

/**
 * @author daring
 *
 */
public class AudioRecognitionUtilTest {

	@Test
	public void test() throws Exception {
		AudioRcognition ar = new AudioRcognition(null);
		URI uri = ar.getVoiceFileUrl("p_TBwGl4W-uWkfXfPizme76IZGP2sHWHaUd3Uk1vsmLtUrOV-wzG9U_8hHB4A6MObHXyBJRkfYQ23aGSA6q9wmoijGuPh3mxeUSdgFkaxyuVpksvQ_u525b4_4H4Suj_DXLbAAAGAO", "OpFNfEOHInSfpOMfp7nHeh19Yfx7uUOTMNGwLOqnv3TLQF-KEy3uaQrMQk2yeE8_");
		AudioRecognitionUtil.getAudiorecognitionutil().setToken(Sample.getToken());
		System.out.println(AudioRecognitionUtil.getAudiorecognitionutil().getAudioText(uri.toString()));
	}

}
