import java.math.BigDecimal;

public class Testtt {

	public static void main(String[] args) {
		System.out.println(new BigDecimal(4.9).divide(new BigDecimal(1000), 10, BigDecimal.ROUND_DOWN));
	}
}