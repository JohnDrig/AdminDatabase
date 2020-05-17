package paket;

public class Person {

	private String firstName, secondName, gender;
	private int age;
	private int id;

	public Person(int id, String firstName, String secondName, int age, String gender) {
		setId(id);
		setFirstName(firstName);
		setSecondName(secondName);
		setAge(age);
		setGender(gender);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

}
