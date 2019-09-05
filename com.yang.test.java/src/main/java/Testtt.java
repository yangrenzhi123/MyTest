import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
//		long a = System.currentTimeMillis();
//		System.out.println(new Random().nextInt(20000000));
//		System.out.println(System.currentTimeMillis() - a);

//		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(1567584746930L)));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(1567588346930L)));
		
//		List<Integer> l = new ArrayList<Integer>();
//		l.add(new Integer(0));
//		l.add(new Integer(1));
//		l.remove(1);
//		for (Integer a : l) {
//			System.out.println(a);
//		}
	}
}