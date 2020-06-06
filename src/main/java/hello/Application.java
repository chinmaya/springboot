package hello;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private SessionFactory hibernateFactory;

	@Autowired
	public Application(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.hibernateFactory = factory.unwrap(SessionFactory.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			/*
			Thread.sleep(10*1000);
			Try scrollable results.
			tryout();
			*/
		};
	}
	
	Set<Integer> ids = new HashSet<>();

	@Transactional
	public void tryout() {
		int count = 0;
		int fetchSize = 10000;

		Session session = hibernateFactory.openSession();
		Criteria cr = session.createCriteria(Employees.class);
		while (count % fetchSize == 0) {
			count = fetch(cr, count, fetchSize);
			System.out.println("Total" + count);
		}
		System.out.println("Ids size " + ids.size());
	}
	
	public int fetch(Criteria cr, int count, int fetchSize) {
		cr.setFirstResult(count);
		cr.setMaxResults(fetchSize);
		ScrollableResults result = cr.scroll(ScrollMode.FORWARD_ONLY);
		while (result.next()) {
			Employees emp = (Employees) result.get(0);
			System.out.println("Emp. no." + emp.getEmp_no() + ", " + emp.getFirst_name());
			count += 1;
			ids.add(emp.getEmp_no());
		}
		return count;
	}

}
