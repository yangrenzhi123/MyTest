import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Java9FlowTest {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch(2);

		// 订阅者
		MySubscriber<Integer> s2 = new MySubscriber<>();
		s2.setLatch(cdl);
		MySubscriber<Integer> s1 = new MySubscriber<>();
		s1.setLatch(cdl);

		// 发布者
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		publisher.subscribe(s1);
		publisher.subscribe(s2);

		// 发布信息
		publisher.submit(0);
		publisher.close();

		cdl.await();
		System.out.println("已结束");
	}

	@SuppressWarnings("hiding")
	public static class MySubscriber<T> implements Flow.Subscriber<T> {
		private Flow.Subscription subscription;
		private CountDownLatch latch;

		@Override
		public void onSubscribe(Flow.Subscription subscription) {
			this.subscription = subscription;
			subscription.request(1); // 这里要使用Long.MAX_VALUE就会被认为获取无穷的数据。
		}

		@Override
		public void onNext(T item) {
			if ((int) item == 1)
				throw new RuntimeException();
			System.out.println("Got : " + item);
			subscription.request(1);
		}

		@Override
		public void onError(Throwable t) {
			t.printStackTrace();
			latch.countDown();
		}

		@Override
		public void onComplete() {
			System.out.println("Done");
			latch.countDown();
		}

		public CountDownLatch getLatch() {
			return latch;
		}

		public void setLatch(CountDownLatch latch) {
			this.latch = latch;
		}
	}
}