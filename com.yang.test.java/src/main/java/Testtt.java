import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		List<Integer> from = new ArrayList<>();
		from.add(1);
		from.add(2);
		from.add(3);
		from.add(4);
		from.add(5);
		from.add(6);
		List<Integer> to = new ArrayList<>();
		//并行
		from.parallelStream();
		//串行
		//from.stream();
		System.out.println(to);
		
//		System.out.println(Long.MAX_VALUE);
		
//		System.out.println(0%5);
//		System.out.println(1%5);
//		System.out.println(2%5);
//		System.out.println(3%5);
//		System.out.println(4%5);
//		System.out.println(5%5);
//		System.out.println(6%5);
//		System.out.println(7%5);
//		System.out.println(8%5);
//		System.out.println(9%5);
//		System.out.println(10%5);
//		System.out.println(11%5);
//		System.out.println(12%5);
//		System.out.println(13%5);
//		System.out.println(14%5);
//		System.out.println(15%5);
//		System.out.println(16%5);
		
		
//		throw new RuntimeException(new NullPointerException());
		
//		long a = System.currentTimeMillis();
//		System.out.println(new Random().nextInt(20000000));
//		System.out.println(System.currentTimeMillis() - a);

//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString());
		
//		Calendar a = Calendar.getInstance();
//		System.out.println(a.get(Calendar.DAY_OF_MONTH));
	
		/*Calendar a = Calendar.getInstance();
		//a.set(Calendar.DAY_OF_YEAR, a.get(Calendar.DAY_OF_YEAR) - 3);
		a.set(Calendar.HOUR_OF_DAY, 0);
		a.set(Calendar.MINUTE, 0);
		a.set(Calendar.SECOND, 0);
		a.set(Calendar.MILLISECOND, 0);
		//System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(a.getTime()));
		System.out.println(a.getTime().getTime());*/
		
//		System.out.println(168895696601088L >> 22);
		
//		System.out.println(1571846400000L + 40267872);
		

//		Calendar a = Calendar.getInstance();
//		System.out.println(a.get(Calendar.DAY_OF_WEEK));
		
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(1288834974000L)));
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(1567588346930L)));
		
//		System.out.println(URLDecoder.decode("%7B%22equipNo%22%3A%22000000000331%22%2C%22type%22%3A%222%22%2C%22phone%22%3A%22LYZH151211144001%22%7D", "utf8"));
		
//		List<Integer> l = new ArrayList<Integer>();
//		l.add(new Integer(0));
//		l.add(new Integer(1));
//		l.remove(1);
//		for (Integer a : l) {
//			System.out.println(a);
//		}
	}
}