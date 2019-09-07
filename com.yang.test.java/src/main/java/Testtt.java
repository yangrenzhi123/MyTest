import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
//		long a = System.currentTimeMillis();
//		System.out.println(new Random().nextInt(20000000));
//		System.out.println(System.currentTimeMillis() - a);

//		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(1567584746930L)));
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(1567588346930L)));
		
		System.out.println(URLDecoder.decode("%7B%22equipNo%22%3A%22000000000331%22%2C%22type%22%3A%222%22%2C%22phone%22%3A%22LYZH151211144001%22%7D", "utf8"));
		
//		List<Integer> l = new ArrayList<Integer>();
//		l.add(new Integer(0));
//		l.add(new Integer(1));
//		l.remove(1);
//		for (Integer a : l) {
//			System.out.println(a);
//		}
	}
}