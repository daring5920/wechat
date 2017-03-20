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

/**
 * @author daring
 *
 */
public class SampleTest {

	@Test
	public void test() {
		try {
			AudioRcognition ar = new AudioRcognition(null);
			URI uri = ar.getVoiceFileUrl("p_TBwGl4W-uWkfXfPizme76IZGP2sHWHaUd3Uk1vsmLtUrOV-wzG9U_8hHB4A6MObHXyBJRkfYQ23aGSA6q9wmoijGuPh3mxeUSdgFkaxyuVpksvQ_u525b4_4H4Suj_DXLbAAAGAO", "OpFNfEOHInSfpOMfp7nHeh19Yfx7uUOTMNGwLOqnv3TLQF-KEy3uaQrMQk2yeE8_");
			Sample.getToken();
			Sample.method1(uri);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
