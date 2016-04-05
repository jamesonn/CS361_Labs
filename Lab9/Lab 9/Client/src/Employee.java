
public class Employee implements Comparable<Object>{

	private String title;
	private String firstName;
	private String lastName;
	private String department;
	private String phoneNumber;
	private String gender;


	public Employee(String title, String firstName, String lastName, String department, String phoneNum, String gender) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.phoneNumber = phoneNum;
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " in " + department + " has phone number of " + phoneNumber;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Employee) {
			Employee other = (Employee) o;
			return lastName.compareTo(other.lastName);
		}
		return 0;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDepartment() {
		return department;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public String getTitle() {
		return title;
	}

	public boolean isEmpty() {
		return firstName.isEmpty() && lastName.isEmpty() && phoneNumber.isEmpty() && department.isEmpty();
	}
}
