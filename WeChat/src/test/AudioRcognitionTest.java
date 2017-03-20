/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import service.AudioRcognition;

/**
 * @author daring
 *
 */
public class AudioRcognitionTest {

	@Test
	public void test() {
		AudioRcognition ar = new AudioRcognition(null);
		ar.getVoiceToText("FdnGd-yxYA0_hfpu7SLdV0YHJZj9IMe-izaYaqZ8MsSQskse66jUf-GxdwLlNR_yJBJkuH0mvSyjaXkUhIx8J25kFTNIfNebP-Z-2g3DTJN6SJI6-dHNdhpYmBoJdT3TSPjAJAPOL", "OpFNfEOHInSfpOMfp7nHeh19Yfx7uUOTMNGwLOqnv3TLQF-KEy3uaQrMQk2yeE8_");
		System.out.println(5L/2);
//		byte[] buffer = new byte[4800];
//		buffer = "niadsflasdf".getBytes();
//		System.out.println(buffer.length);
	}

}
