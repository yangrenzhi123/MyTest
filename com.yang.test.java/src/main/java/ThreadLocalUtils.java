import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThreadLocalUtils {

	public static final ThreadLocal<DateFormat> yyyyMMddHHmmss = new ThreadLocal<>();

	public static DateFormat getyyyyMMddHHmmss() {
		DateFormat df = yyyyMMddHHmmss.get();
		if (df == null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			yyyyMMddHHmmss.set(df);
		}
		return df;
	}
}