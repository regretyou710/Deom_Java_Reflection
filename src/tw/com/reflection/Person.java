package tw.com.reflection;

import javax.sound.midi.MidiDevice.Info;

public class Person {
	private String name;
	public int age;

	public Person() {

	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	private Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void show() {
		System.out.println("您好 我是一個普通人");
	}

	private String showNation(String nation) {
		System.out.println("我的國籍是:" + nation);
		return nation;
	}

	public static void info(){
		System.out.println("一個沒事做的普通人");
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
