import java.util.Observable;

/** 响应式编程 */
public class Reactive {

	public static void main(String[] args) {
		// 可观察对象
		MyObservable observable = new MyObservable();
		// 添加观察者
		observable.addObserver((o, arg) -> {
			System.out.println("观察者1处理事件:" + arg.toString());
		});

		observable.addObserver((o, arg) -> {
			System.out.println("观察者2处理事件:" + arg.toString());
		});

		observable.addObserver((o, arg) -> {
			System.out.println("观察者3处理事件:" + arg.toString());
		});
		// 发布事件通知观察者
		observable.setChanged();
		observable.notifyObservers("事件@@");
	}

	static class MyObservable extends Observable {
		@Override
		public void setChanged() {
			super.setChanged();
		}
	}
}
