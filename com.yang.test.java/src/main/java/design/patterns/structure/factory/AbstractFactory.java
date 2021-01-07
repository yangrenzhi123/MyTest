package design.patterns.structure.factory;

public class AbstractFactory {

	public static void main(String[] args) {
		Farm f = new SGfarm();
		Animal a = f.newAnimal();
		a.show();
	}
}

// 抽象产品：动物类
interface Animal {
	public void show();
}

// 具体产品：马类
class Horse implements Animal {
	public void show() {
		System.out.println("马");
	}
}

// 具体产品：牛类
class Cattle implements Animal {
	public void show() {
		System.out.println("牛");
	}
}

// 抽象产品：植物类
interface Plant {
	public void show();
}

// 具体产品：水果类
class Fruitage implements Plant {
	public void show() {
		System.out.println("水果");
	}
}

// 具体产品：蔬菜类
class Vegetables implements Plant {
	public void show() {
		System.out.println("蔬菜");
	}
}

// 抽象工厂：农场类
interface Farm {
	public Animal newAnimal();

	public Plant newPlant();
}

// 具体工厂：韶关农场类
class SGfarm implements Farm {
	public Animal newAnimal() {
		System.out.println("新牛出生！");
		return new Cattle();
	}

	public Plant newPlant() {
		System.out.println("蔬菜长成！");
		return new Vegetables();
	}
}

// 具体工厂：上饶农场类
class SRfarm implements Farm {
	public Animal newAnimal() {
		System.out.println("新马出生！");
		return new Horse();
	}

	public Plant newPlant() {
		System.out.println("水果长成！");
		return new Fruitage();
	}
}