package nagp.com.data.restfulbooker;

public class Employment {
	
	
	public Employment(String company, String position, int yearsatcompany, Salary salary) {
		this.Company=company;
		this.Position=position;
		this.yearsatcompany=yearsatcompany;
		this.salary= salary;
		
	}

	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public int getYearsatcompany() {
		return yearsatcompany;
	}
	public void setYearsatcompany(int yearsatcompany) {
		this.yearsatcompany = yearsatcompany;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	private String Company;
	private String Position;
	private int yearsatcompany;
	private Salary salary;
	
}
