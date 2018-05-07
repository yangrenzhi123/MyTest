import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

public class Test implements WrapperListener {

	public static void main(String[] args) throws InterruptedException {

		WrapperManager.start(new Test(), args);
		
		int i=0;
		while(true){
			System.out.println(i++);
			Thread.sleep(1000);
		}
	}

	@Override
	public void controlEvent(int arg0) {
	}

	@Override
	public Integer start(String[] arg0) {
		System.out.println("start");
		return null;
	}

	@Override
	public int stop(int arg0) {
		System.out.println("stop");
		return 0;
	}
}