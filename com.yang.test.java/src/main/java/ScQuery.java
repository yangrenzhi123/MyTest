import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScQuery {

	public static void main(String[] args) throws IOException {
		String filter = args[0];

		String cmdA = "sc query type=all state=all";
		Process p = Runtime.getRuntime().exec(cmdA);
		console(p, filter);
	}

	private static void console(Process p, String filter) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		int start = 0;
		int i = 0;
		while ((line = br.readLine()) != null) {
			if (line.contains(filter)) {
				start = 1;
			}

			if (start == 1) {
				System.out.println(line);
				if (i++ > 10)
					break;
			}
		}
		br.close();
	}
}