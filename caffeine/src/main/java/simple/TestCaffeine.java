package simple;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

public class TestCaffeine {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		t0();
	}
	
	//手动
	public static void t0() {
		Cache<Integer, Object> a = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).maximumSize(100).build();
		
		a.put(1, "123");
		a.put(2, "456");

		System.out.println(a.getIfPresent(1));
		System.out.println(a.getIfPresent(2));
		a.invalidateAll();
		System.out.println(a.getIfPresent(1));
		System.out.println(a.getIfPresent(2));
	}

	//手动
	public static void t1() {
		Cache<Integer, Object> a = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).maximumSize(100).build();

		System.out.println(a.get(1, k -> "123"));
	}

	//同步加载
	public static void t2() {
		LoadingCache<Integer, Object> a = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).maximumSize(100).build(k -> "i got " + k);

		System.out.println(a.get(1));
		
		a.getAll(Arrays.asList(2, 3, 4));
	}

	//异步加载
	public static void t3() throws InterruptedException, ExecutionException {
		AsyncLoadingCache<Integer, Object> a = Caffeine.newBuilder().maximumSize(100).expireAfterWrite(1, TimeUnit.MINUTES).buildAsync(k -> "i got " + k);
		
		
		CompletableFuture<Void> b = a.get(1).thenAccept(o -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(o);
		});
		
		
		System.out.println(b.get());
	}
}