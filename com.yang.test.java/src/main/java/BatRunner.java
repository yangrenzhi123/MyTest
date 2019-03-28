
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BatRunner {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(1);
		way7();

		Thread.sleep(100000);
	}

	public static void way7() throws IOException {
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Process p = Runtime.getRuntime().exec("netstat -ano | findstr 8416");
					console(p);
				} catch (IOException e) {
				}
			}
		}).start();
	}

	private static void console(Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
}