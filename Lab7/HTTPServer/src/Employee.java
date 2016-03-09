import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Employee {

	public String firstName;
	private String lastName;
	private String dept;
	private String phoneNumber;

	public Employee(String firstName, String lastName, String dept, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dept = dept;
		this.phoneNumber = phoneNumber;
	}

	public void setName(String name) {
		firstName = name;
	}

	public void setLastName(String lastName) {
		lastName = lastName;
	}

	public void setDepartment(String dept) {
		dept = dept;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + dept + " " + phoneNumber;
	}

	public static void main(String[] args) {
		Gson g = new Gson();
		ArrayList<Employee> p = new ArrayList<Employee>();
		p.add(new Employee("Alex", "Sarafraz", "IT", "1234567891"));
		p.add(new Employee("Allen", "Nash", "HR", "1567891234"));


		String out = g.toJson(p);
		System.out.println(out);
		ArrayList<Employee> ep = (g.fromJson(out, new TypeToken<Collection<Employee>>(){}.getType()));
		for (Employee pp: ep) {
			System.out.println(pp);
		}
	}

}
