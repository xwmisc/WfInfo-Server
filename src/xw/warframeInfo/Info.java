package xw.warframeInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;


public class Info {
	static ArrayList<String> msgQueue = new ArrayList<>();
	static String ROOT = "C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.29-windows-x64\\apache-tomcat-8.5.29\\webapps\\ROOT";
	// static String LOG = ROOT + "\\log.txt";
	static String LOG = "E:\\360Download\\CAQ\\log.txt";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String ss = "5rKh5pyJ6bifcOaIkeimgeatu S6hgA=";
		OnMessage(ss, ss, ss, ss, ss, ss);
		if(true)return;
		
		String a = "5rKh5pyJ6bifcOaIkeimgeatu+S6hgA=";
		a="5rKh5pyJ6bifcOaIkeimgeatu S6hgA=";
		a=a.replace(' ', '+');
		System.out.println(a);
		// a = Base64.getEncoder().encodeToString("啊".getBytes());
		System.out.println(Base64.getEncoder().encodeToString("啊".getBytes()));

		System.out.println("1");
//		System.out.println(new String(d.decodeBuffer(a), "UTF-8"));
//		for (byte b : a.getBytes("UTF-8")) {
//			System.out.print(b + " ");
//		}
		System.out.println("\n2");
		for (byte b : Base64.getDecoder().decode(a.getBytes("UTF-8"))) {
			System.out.print(b + " ");
		}
		System.out.println("\n3");
		for (byte b : base64Decode(a).getBytes("UTF-8")) {
			System.out.print(b + " ");
		}
		System.out.println("\n-");

		System.out.println(base64Decode(a));
	}

	public static void add() {
		msgQueue.add(new Date().toString());
	}

	public static String GetRespond() {
		if (msgQueue.size() > 0) {
			String respond = msgQueue.get(0);
			msgQueue.remove(0);
			return respond;
		}
		return "";
	}

	public static String OnMessage(String qq, String msgChildType, String fromGroup, String fromQQ, String toQQ,
			String msg) {
		String record = null;
		try {
			record += "\n---" + new Date().toString() + "\n qq=" + qq + "\n msgChildType=" + msgChildType
					+ "\n fromGroup=" + fromGroup + "\n fromQQ=" + fromQQ + "\n toQQ=" + toQQ + "\n msg=" + msg;

//			if (msg.equals("5a6z5oCVAA==")) {
//				record += "\n合法";
//				record += "\n翻译i" + base64Decode(msg);
//				record += "\n翻译c" + base64Decode("5a6z5oCVAA==");
//			}
//			if (base64Decode(msg).equals("害怕")) {
//				record += "\n害怕合法";
//				record += "\n翻译raw|" + msg;
//				record += "\n翻译c" + "害怕";
//			} else {
//				record += "\n害怕不合法";
//				record += "\n翻译raw|" + msg;
//				record += "\n翻译c" + "害怕";
//			}
//			record += "\n";
//			for (byte b : msg.getBytes("UTF-8")) {
//				record += b + " ";
//			}
//			record += "\n";
//			for (byte b : base64Decode(msg).getBytes("UTF-8")) {
//				record += b + " ";
//			}
//			record += "\n";
//			for (byte b : base64Decode("5a6z5oCVAA==").getBytes("UTF-8")) {
//				record += b + " ";
//			}

			String msg2 = msg;
			qq = base64Decode(qq.trim());
			msgChildType = base64Decode(msgChildType.trim());
			fromGroup = base64Decode(fromGroup.trim());
			fromQQ = base64Decode(fromQQ.trim());
			toQQ = base64Decode(toQQ.trim());
			msg = base64Decode(msg.trim());

			record += "\n" + new Date().toString() + "\n qq=" + qq + "\n msgChildType=" + msgChildType + "\n fromGroup="
					+ fromGroup + "\n fromQQ=" + fromQQ + "\n toQQ=" + toQQ + "\n msg=" + msg + "\n msg2=" + msg2;

			writeLog(record);

			msgQueue.add(record);

			// if(fromGroup!="734687193")

			/*
			 * Info info = new Info();
			 * 
			 * File file = new File("E:\\360Download\\CAQ\\a.txt"); OutputStream out = new
			 * FileOutputStream(file); info.getURL(out,
			 * "https://www.cnblogs.com/listened/p/4248990.html"); out.close();
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		return record;
	}

	private static void writeLog(String log) throws IOException, FileNotFoundException {
		System.out.println(log);
		File file = new File(LOG);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
		out.write(log);
		out.flush();
		out.close();

	}

	private static String base64Decode(String str) throws IOException {
		try {
			str = str.replace(' ', '+');
			writeLog(str);
			//byte[] result = Base64.getDecoder().decode(str.getBytes("UTF-8"));
			byte[] result = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(str);
			int zero_p = result.length;
			while (result[zero_p - 1] == 0 && zero_p - 1 > 0)
				zero_p--;

			writeLog(new String(result, 0, zero_p, "UTF-8"));
			return new String(result, 0, zero_p, "UTF-8");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			String record = "";
			for (byte b : str.getBytes("UTF-8")) {
				record += b + " ";
			}
			writeLog("\n--!!|" + record + "|!!--\n");
		}
		return str;
	}

	private static void getURL(OutputStream out, String spec) throws IOException {
		URL url = new URL(spec);
		URLConnection connect = url.openConnection();
		InputStream in = connect.getInputStream();
		int b = in.read();
		while (b != -1) {
			out.write(b);
			b = in.read();
		}
		in.close();
	}

}
