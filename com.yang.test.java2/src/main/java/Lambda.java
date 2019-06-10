import java.util.Comparator;
import java.util.function.Consumer;

public class Lambda {

	public static void main(String[] args) {
		test5();
	}

	public static void test() {
		Runnable r = () -> {
			System.out.println("Hello Lambda!");
		};
		r.run();
	}

	public static void test2() {
		Consumer<String> consumer = (x) -> System.out.println(x);
		consumer.accept("345678");
	}

	public static void test3() {
		Consumer<String> consumer = x -> {
			System.out.println(x);
		};
		consumer.accept("123456");
	}

	public static void test4() {
		Comparator<Integer> comparable = (x, y) -> {
			System.out.println("234567");
			return Integer.compare(x, y);
		};

		System.out.println(comparable.compare(1, 2));
	}

	public static void test5() {
		T r = () -> {
			System.out.println("Hello Lambda!");
		};
		r.run();
	}

	public static void test6() {
		Comparator<Integer> comparable = (Integer x, Integer y) -> Integer.compare(x, y);
		System.out.println(comparable.compare(1, 2));
	}

	public static void test7() {
		Three comparable = (Integer x, Integer y, String z) -> {
			Integer.compare(x, y);
		};
		comparable.compare(1, 2, "3");
	}

	public static void test8() {
		for8((Integer x, Integer y, String z) -> {
			Integer.compare(x, y);
		});
	}
	
	public static void for8(Three t) {
		t.compare(1, 2, "3");
	}
}

interface T {
	void run();
}

@FunctionalInterface
interface Three {
	void compare(Integer x, Integer y, String z);
}