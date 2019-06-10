
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BatRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec("netstat -ano | findstr 14196");
		console(p);
		error(p);
	}

	private static void console(Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	private static void error(Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
}