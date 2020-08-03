package tw.com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class Testing {

	// 反射之前，對於Person的操作
	@Test
	public void test1() {
		Person p1 = new Person("Eason", 20);
		p1.setAge(22);
		System.out.println(p1.toString());
		p1.show();
		// 無法存取私有的屬性、方法、建構式
	}

	// 反射之後，對於Person的操作
	@Test
	public void test2() throws Exception {
		// getFields():獲取當前運行時類及其父類中聲明為public的欄位。
		// getDeclaredFields():獲取當前運行時類中聲明的所有的欄位(不包含父類中聲明的欄位)。

		// 透過反射，創建Person物件
		Class clazz = Person.class;
		Constructor cons = clazz.getConstructor(String.class, int.class);
		Object instance = cons.newInstance("Eason", 20);
		Person p1 = (Person) instance;
		System.out.println(instance);
		System.out.println(p1);

		// 透過反射，調用Person物件指定的屬性、方法
		Field age = clazz.getField("age");
		age.set(p1, 22);
		System.out.println("調用age屬性:" + p1.toString());

		Method show = clazz.getMethod("show");
		show.invoke(p1);

		System.out.println("---------------");

		// 透過反射，調用Person類私有的屬性、方法、建構式

		// 調用私有建構式
		Constructor cons1 = clazz.getDeclaredConstructor(String.class);
		cons1.setAccessible(true);
		Person p2 = (Person) cons1.newInstance("Tom");
		System.out.println(p2);

		// 調用私有屬性
		Field name = clazz.getDeclaredField("name");
		name.setAccessible(true);
		name.set(p2, "Lee");
		System.out.println(p2);

		// 調用私有方法
		Method showNation = clazz.getDeclaredMethod("showNation", String.class);
		showNation.setAccessible(true);
		// showNation.invoke(p2, "台灣");
		String str = (String) showNation.invoke(p2, "台灣");
		System.out.println(str);

		// 調用靜態方法
		Method info = clazz.getDeclaredMethod("info");
		info.setAccessible(true);
		info.invoke(Person.class);// info.invoke(clazz);靜態方法在類的加載過程就被設置並初始化，所以依附在運行時類的身上而不是運行時類的物件
	}

	// 獲取Class實例的方式
	@Test
	public void test3() {
		// 方式一:調用運行時，類的屬性 .class
		Class<Person> clazz1 = Person.class;
		System.out.println("clazz1: " + clazz1);

		// 方式二:透過運行時，"類的物件"調用getClass()
		Person p1 = new Person();
		Class clazz2 = p1.getClass();
		System.out.println("clazz2: " + clazz2);

		// 方式三:調用Class的靜態方法 forName(String classPath)
		Class clazz3 = null;
		try {
			clazz3 = Class.forName("tw.com.reflection.Person");
			System.out.println("clazz3: " + clazz3);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(clazz1 == clazz2);
		System.out.println(clazz1 == clazz3);

		// 方法四:使用類的加載器，ClassLoader()
		ClassLoader classLoader = Testing.class.getClassLoader();
		try {
			Class clazz4 = classLoader.loadClass("tw.com.reflection.Person");
			System.out.println("clazz4: " + clazz4);
			System.out.println(clazz1 == clazz4);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
