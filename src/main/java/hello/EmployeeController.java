package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;

	public EmployeeRepository getRepository() {
		return repository;
	}
	
	@GetMapping
	public String showEmployeeDetails() {
		return "employee.html";
	}
	
	@PostMapping
	public void createEmployeeDetails(@ModelAttribute EmployeeDetails details) {
		Employee entity = new Employee(details.getName(), details.getEmail());
		repository.save(entity);
	}
}
