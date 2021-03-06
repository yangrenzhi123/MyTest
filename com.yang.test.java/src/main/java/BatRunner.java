
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BatRunner {

	public static void main(String[] args) throws IOException, InterruptedException {
		String[] cmdA = { "/bin/sh", "-c", "df -h | awk '{print $5}'" }; // 这个样子可以带参数
		Process p = Runtime.getRuntime().exec(cmdA);
		console(p);
		error(p);
	}

	private static void console(Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
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