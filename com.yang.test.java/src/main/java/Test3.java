import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test3 {

	public static void main(String[] args) throws IOException {
		String[] command = {"/bin/sh", "-c", args[0]};
		java.lang.Process p = Runtime.getRuntime().exec(command);

		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();

		br = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GBK"));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}
}