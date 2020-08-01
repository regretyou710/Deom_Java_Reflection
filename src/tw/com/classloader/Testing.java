package tw.com.classloader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class Testing {

	@Test
	public void test1() {
		// 對於自定義類，使用系統類加載器進行加載
		ClassLoader classLoader = Testing.class.getClassLoader();
		System.out.println(classLoader);// AppClassLoader:系統類加載器

		ClassLoader classLoader1 = classLoader.getParent();
		System.out.println(classLoader1);// ExtClassLoader:擴展類加載器

		// 引導類加載器主要負責加載java的核心類庫
		ClassLoader classLoader2 = classLoader1.getParent();
		System.out.println(classLoader2);// 引導類加載器獲取不到，null

		ClassLoader classLoader3 = String.class.getClassLoader();
		System.out.println(classLoader3);// 引導類加載器獲取不到，null
	}

	@Test
	public void test2() throws Exception {
		// 資源文件載入
		//方式一:
		//默認文件路徑在Project底下
		Properties prop1 = new Properties();
		FileInputStream fis = new FileInputStream("jdbc1.properties");
		//FileInputStream fis = new FileInputStream("src/jdbc2.properties");
		prop1.load(fis);
		String user1 = prop1.getProperty("user");
		String pwd1 = prop1.getProperty("password");
		System.out.println("user:" + user1 + "\t pwd:" + pwd1);
		
		//方式二:使用ClassLoader，默認文件路徑在src(資源文件夾)底下
		Properties prop2 = new Properties();
		ClassLoader classLoader = Testing.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream("jdbc2.properties");		
		prop2.load(is);
		String user2 = prop2.getProperty("user");
		String pwd2 = prop2.getProperty("password");
		System.out.println("user:" + user2 + "\t pwd:" + pwd2);
		
	}
}
