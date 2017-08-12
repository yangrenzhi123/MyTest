import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SocketUtil {

	public static void writeStr2Stream(String str, OutputStream out) throws IOException {
		BufferedOutputStream writer = new BufferedOutputStream(out);
		writer.write(str.getBytes());
		writer.flush();
	}

	public static void readStrFromStream(InputStream in) throws IOException {
		StringBuffer result = new StringBuffer();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		char[] chars = new char[2048];
		int len;

		while ((len = reader.read(chars)) != -1) {
			if (len == 2028) {
				result.append(chars);
			} else {
				for (int i = 0; i < len; i++) {
					result.append(chars[i]);
				}
				break;
			}
		}
		System.out.println("Server:" + result);
	}
}