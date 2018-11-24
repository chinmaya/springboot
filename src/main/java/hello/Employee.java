package hello;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String email;
	
	@ManyToOne(targetEntity=Department.class, optional=true)
	private Collection<Department> departments;

	public Employee() {
	}

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Collection<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Collection<Department> departments) {
		this.departments = departments;
	}

}
