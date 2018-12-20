import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TestSendToNettyHeart {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		int countStart = Integer.parseInt(args[0]);
		int countEnd = Integer.parseInt(args[1]);
		
		long a = System.currentTimeMillis();
		List<OutputStream> osl = new ArrayList<OutputStream>();
		List<InputStream> isl = new ArrayList<InputStream>();
		List<Socket> sl = new ArrayList<Socket>();
		for (int i = 0; i < (countEnd - countStart); i++) {
			Socket request = new Socket("192.168.10.238", 3113);
			OutputStream os = request.getOutputStream();
			InputStream is = request.getInputStream();
			isl.add(is);
			osl.add(os);
			sl.add(request);
		}
		System.out.println("连接耗时："+(System.currentTimeMillis() - a));

		List<String> deviceNos = new ArrayList<>();
		for (int i = 0; i < (countEnd - countStart); i++) {
			String deviceNo = String.format("%014d", i+countStart);
			String aa = deviceNo.substring(0, 2);
			String bb = deviceNo.substring(2, 4);
			String cc = deviceNo.substring(4, 6);
			String dd = deviceNo.substring(6, 8);
			String ee = deviceNo.substring(8, 10);
			String ff = deviceNo.substring(10, 12);
			String gg = deviceNo.substring(12, 14);
			if(aa.equals("55")) {
				aa = "5401";
			}
			if(aa.equals("54")) {
				aa = "5402";
			}

			if(bb.equals("55")) {
				bb = "5401";
			}
			if(bb.equals("54")) {
				bb = "5402";
			}

			if(cc.equals("55")) {
				cc = "5401";
			}
			if(cc.equals("54")) {
				cc = "5402";
			}

			if(dd.equals("55")) {
				dd = "5401";
			}
			if(dd.equals("54")) {
				dd = "5402";
			}

			if(ee.equals("55")) {
				ee = "5401";
			}
			if(ee.equals("54")) {
				ee = "5402";
			}

			if(ff.equals("55")) {
				ff = "5401";
			}
			if(ff.equals("54")) {
				ff = "5402";
			}

			if(gg.equals("55")) {
				gg = "5401";
			}
			if(gg.equals("54")) {
				gg = "5402";
			}
			
			deviceNo = aa + bb + cc + dd + ee + ff + gg;
			
			long b = Long.parseLong(deviceNo);
			deviceNo = String.format("%014d", b);
			deviceNos.add(deviceNo);
		}
		
		a = System.currentTimeMillis();
		for (int i = 0; i < (countEnd - countStart); i++) {
			OutputStream os = osl.get(i);
			InputStream is = isl.get(i);
			
			byte[] bs = hexString2Bytes("554000E00102"+deviceNos.get(i)+"CA3F55");
			try {
				os.write(bs);
				is.read(new byte[1024]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("线程创建耗时："+(System.currentTimeMillis() - a));
	}
	
	public static byte[] hexString2Bytes(String hex) {
		if ((hex == null) || (hex.equals(""))) {
			return null;
		} else if (hex.length() % 2 != 0) {
			return null;
		} else {
			hex = hex.toUpperCase();
			int len = hex.length() / 2;
			byte[] b = new byte[len];
			char[] hc = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int p = 2 * i;
				b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
			}
			return b;
		}
	}
	
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}