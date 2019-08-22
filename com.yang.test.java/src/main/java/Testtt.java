import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
//		long a = System.currentTimeMillis();
//		System.out.println(new Random().nextInt(20000000));
//		System.out.println(System.currentTimeMillis() - a);

		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		
//		List<Integer> l = new ArrayList<Integer>();
//		l.add(new Integer(0));
//		l.add(new Integer(1));
//		l.remove(1);
//		for (Integer a : l) {
//			System.out.println(a);
//		}
	}
}