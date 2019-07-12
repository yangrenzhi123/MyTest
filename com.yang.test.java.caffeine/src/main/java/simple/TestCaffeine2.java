package simple;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class TestCaffeine2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		t0();
	}

	// 手动
	public static void t0() {
		Cache<Object, Object> a = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).maximumSize(100).build();

		Test t = new Test();
		t.setId(1);
		a.put(t, "123");
		System.out.println(a.getIfPresent(t));

		t = new Test();
		t.setId(1);

		System.out.println(a.getIfPresent(t));
	}
}

class Test {
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}