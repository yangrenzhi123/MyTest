import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Test4 {

	public static void main(String[] args) throws IOException {
		try {
			test();
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream(32);
			PrintStream pw = new PrintStream(os);

			e.printStackTrace(pw);
			pw.println("456");
			pw.println("789");

			os.flush();
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

			InputStreamReader isr = new InputStreamReader(is);
			
			BufferedReader r = new BufferedReader(isr);
			System.out.println(r.readLine());
			System.out.println(r.readLine());
			System.out.println(r.readLine());
		}
	}
	
	public static void test() {
		throw new RuntimeException("123");
	}
}