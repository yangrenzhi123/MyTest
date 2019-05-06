
import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class Testtt {

	
	
	public static void main(String[] args) {
		LocalDateTime of = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()), LocalTime.of(0, 0, 0));
		Duration between = Duration.between(LocalDateTime.now(), of);
		System.out.println(between.getSeconds());
		int second = (int) between.getSeconds();
		System.out.println(second);
	}
}