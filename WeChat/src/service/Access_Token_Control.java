/**
 * 
 */
package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author daring
 *
 */
public class Access_Token_Control implements Runnable{
	
	long startTime = 0L;
	long endTime = 0L;
	
	/**
	 * 设定accesstoken的值
	 */
	public void setToken(){
//		z0WzYPg5Hk5bwtyyOccbV5g3QIC_THlX0WC1OlDuwRpDMp5hr7Zm6Uu1MpAvG0Duu8JZSdPmbJzdltRMI48tv6b3_hmMdFzrAZVkHUOvrKMrlo8TD8L2jzP9mCR9pQEuJUAdAJASDJ
//		1478054191696
//		1478061391696
		String token;
		Scanner scan = new Scanner(Access_Token_Control.class.getClassLoader().getResourceAsStream("service/access_token.txt"));
		token = scan.nextLine();
		startTime = scan.nextLong();
		endTime = scan.nextLong();
		scan.close();
		long currentTime = System.currentTimeMillis();
		if(currentTime>=endTime)
		{
			try {
				tokenControl();
				Scanner scan1 = new Scanner(Access_Token_Control.class.getClassLoader().getResourceAsStream("service/access_token.txt"));
				token = scan1.nextLine();
				startTime = scan1.nextLong();
				endTime = scan1.nextLong();
				scan.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GetInfo.access_token =  token;
	}
	
	/**
	 * 用于两个小时之内（一个小时50分钟）获取微信accesstoken的值
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void tokenControl() throws IOException, URISyntaxException{
		GetAccess_token getToken = new GetAccess_token("client_credential", "wx47e3e56ab9fd00ed", "b9ab055165f7194e40d547f5c5623067");
		File file = new File(Access_Token_Control.class.getClassLoader().getResource("service/access_token.txt").toURI());
		FileWriter writer = new FileWriter(file);
		startTime = System.currentTimeMillis();
		Map<String, String> map = getToken.getToken();
		
		
			String token = map.get("access_token");
			writer.write(token);
			writer.write(System.lineSeparator()+startTime);
			//有效时间
			String canTime = map.get("expires_in");
			endTime = startTime+Long.parseLong(canTime)*1000;
			writer.write(System.lineSeparator()+endTime);
		writer.close();
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Access_Token_Control atc = new Access_Token_Control();
		while(true){
			
			atc.setToken();
			try {
				Thread.sleep(1000*60*110);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
