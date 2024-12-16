package nagp.com.data.restfulbooker;

import java.util.List;

public class Details {

	
	public Details(String name, int age, String email, Address address, List<PhoneNumbers> phoneNumbers, Employment employment) {
		this.name= name;
		this.age= age;
		this.email= email;
		this.address= address;
		this.phoneNumbers= phoneNumbers;
		this.employment= employment;
		
	}
	private Employment employment;
		
	public Employment getEmployment() {
		return employment;
	}
	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	public List<PhoneNumbers> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumbers> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	private List<PhoneNumbers> phoneNumbers;
		private String name;
		private String email;
	private int age;
	private Address address;
}
