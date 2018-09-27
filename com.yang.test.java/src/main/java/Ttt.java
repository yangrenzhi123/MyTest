import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Ttt {

	public static void main(String[] args) throws IOException {
		
		
		File f = new File(args[0]);
		byte[] b = new byte[new Integer(f.length()+"")];
		FileInputStream is = new FileInputStream(f);
		is.read(b);
		for(long i=0;i<f.length();i++) {
			System.out.print(b[new Integer(i+"")]);
		}
		is.close();
	}
}
