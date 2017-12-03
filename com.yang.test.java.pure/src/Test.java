public class Test {

	public static void main(String[] args) {
		T t = () -> {System.out.println("Hello Lambda!");};
		t.print();
		
		G g = new G();
		//print2	g.init((i) -> {System.out.println(1);}); // 注意这里，基础是java8
		
		
		System.out.println(g.id);
	}
}

class G {
	public Integer id;

	public G() {
		
	}
	
	public G(Runnable target) {
		target.run();
	}

	public void init(T target) {
		//target.print2(1);
	}
}

interface T {
	void print();
}