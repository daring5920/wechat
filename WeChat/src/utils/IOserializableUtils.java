package utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author daring
 *
 */
public class IOserializableUtils {
	//设置日期格式
	private static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	//设置服务端日志存储位置
	private static File filelog = new File("/weixin/log/"+format1.format(new Date()));
	//构造方法
	 public IOserializableUtils() {
	}
	 public static void main(String args[]){
		 //本地测试
		 System.out.println(System.getProperty("user.home")+"/Desktop");
	 }
	 public static void writeLog(String str) throws IOException{
		 //判断当前文件是否存在，否则创建该文件
		 if(!filelog.exists())
			 filelog.mkdirs();
		 //设置文件可执行//仅测试所用
		 filelog.setExecutable(true);
		 //设置文件可读
		 filelog.setReadable(true);
		 //设置文件可写
		 filelog.setWritable(true);
		 //设置消息日期格式
		 DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		 FileOutputStream fout =  new FileOutputStream(filelog.getPath()+"/"+(format1.format(new Date())+".log"), true);
		 //设置文本编码
		 OutputStreamWriter owriter = new OutputStreamWriter(fout, "UTF-8");
		 owriter.write(str);
		 //添加换行符，自动判断时windows系统还是linux系统
		 owriter.write(format2.format(new Date())+"-------"+MessagekUtil.NEWLINE);
		 owriter.flush();
		 owriter.close();
	 }

}
