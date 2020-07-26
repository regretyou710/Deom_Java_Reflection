package tw.com.newinstance;

import java.util.Random;

import org.junit.Test;

import tw.com.reflection.Person;

public class Testing {

	/**
	 * 透過反射創建應對的運行時類的物件
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void test1() throws InstantiationException, IllegalAccessException {
		Class clazz = Person.class;
		/*
		 * Person.class->運行時類 newInstance():調用此方法，創建對應的運行時類的物件。內部調用了運行時類的空參建構式
		 * 若要此方法正常運行，要求
		 * 1.運行時類須提供空參建構式 
		 * 2.空參建構式的存取修飾詞須public
		 * 
		 * 在javabean中要求提供一個空參public建構式，原因: 
		 * 1.便於透過反射，創建運行時類的物件
		 * 2.便於子類繼承此運行類時，默認調用super()，保證父類有此建構式
		 */
		Person p1 = (Person) clazz.newInstance();
		System.out.println(p1);
	}

	@Test
	public void test2() {
		/*
		 * 透過隨機數體現反射機制的動態性 
		 * 反射機制動態性:在編譯時無法確定要創造哪個對象，運行時才知道要創造哪一對象
		 */
		for (int i = 0; i < 10; i++) {
			int option = new Random().nextInt(3);// 0~2
			String classPath = "";
			switch (option) {
			case 0:
				classPath = "java.util.Date";
				break;
			case 1:
				classPath = "java.lang.Object";
				break;
			case 2:
				classPath = "tw.com.reflection.Person";
				break;
			}
			try {
				Object obj = getInstance(classPath);
				System.out.println(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Object getInstance(String classPath) throws Exception {
		/*
		 * 創建一個指定類的物件 
		 * classPath:指定類的全類名
		 */
		Class clazz = Class.forName(classPath);
		return clazz.newInstance();
	}
}
