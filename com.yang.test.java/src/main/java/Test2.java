import java.util.Random;

public class Test2 {

	public static void main(String[] args) {
		Random r = new Random();
		while(true) {
			System.out.println(r.nextInt(20));
		}
	}
}

class A {

	static {
		System.out.print("1");
	}

	public A() {
		System.out.print("2");
	}
}

class B extends A {

	static {
		System.out.print("a");
	}

	public B() {
		System.out.print("b");
	}
}