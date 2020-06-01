
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Iptables {

	public static void main(String[] args) throws IOException, InterruptedException {
		int length = args.length;
		String[] ss = new String[] {"raw", "mangle", "nat", "filter"};
		
		for(int i = 0;i<4;i++) {
			System.out.println("表：" + ss[i]);
			Process p = Runtime.getRuntime().exec("iptables -t "+ss[i]+" -L -n");
			if(length > 0) {
				console(p, "Chain " + args[0]);
			}else {
				console(p);
			}
			error(p);
		}
	}

	private static void console(Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	private static void console(Process p, String type) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
		String line;
		boolean started = false;
		while ((line = br.readLine()) != null) {
			if(line.startsWith(type) || started == true) {
				if(started == false) started = true;
				if(line.startsWith("Chain") && !line.startsWith(type) ) {
					started = false;
					break;
				}
				System.out.println(line);
			}
		}
		br.close();
	}

	private static void error(Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), "UTF-8"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
}