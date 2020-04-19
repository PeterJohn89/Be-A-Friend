public class Friend {
	private String userName;
	private String bio;
	private int age;
	private String contactNumber;

	public Friend(String userName, String bio, int age, String contactNumber) {
		this.userName = userName;
		this.bio = bio;
		this.age = age;
		this.contactNumber = contactNumber;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getBio() {
		return this.bio;
	}

	public int getAge() {
		return this.age;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

}
