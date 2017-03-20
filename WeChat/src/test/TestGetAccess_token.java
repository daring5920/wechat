/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import service.GetAccess_token;

/**
 * @author daring
 *
 */
public class TestGetAccess_token {

	/**
	 * Test method for {@link service.GetAccess_token#getToken()}.
	 */
	@Test
	public void testGetToken() {
		GetAccess_token getToken = new GetAccess_token("client_credential", "wx47e3e56ab9fd00ed", "b9ab055165f7194e40d547f5c5623067");
		Map<String, String> map = getToken.getToken();
		System.out.println(map);
	}

}
