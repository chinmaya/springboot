package hello;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
	private RegistrationRepository repository;
	
	@GetMapping
	public ModelAndView getRegister(@RequestParam(required=false) String greeting) {
		if (greeting == null) {
			greeting = "Hello There, please register";
		}
		Map<String, String> model = new HashMap<>();
		model.put("greeting", greeting);
		return new ModelAndView("register", model);
	}
	
	@PostMapping
	public @ResponseBody RegistrationDetails register(@ModelAttribute RegistrationDetails details) {
		repository.save(new Registration(details.getName(), details.getEmail()));
		return details;
	}
	
	@GetMapping("all")
	public @ResponseBody List<RegistrationDetails> getAllRegistrations() {
		List<RegistrationDetails> list = new LinkedList<>();
		for (Registration reg : repository.findAll()) {
			list.add(new RegistrationDetails(reg.getName(), reg.getEmail()));
		}
		return list;
	}
}
