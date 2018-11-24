package hello;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {
 
    @Autowired
    private UserRepository repository;
    
    @Test
    public void testCreate() {
    	User user = new User();
    	user.setName("John");
    	user.setEmail("Email");
    	
    	User entity = repository.save(user);
    	
    	Optional<User> response = repository.findById(entity.getId());
    	Assert.assertTrue(response.isPresent());
    	
    	Assert.assertEquals("The name muse be John", "John", response.get().getName());
    }
}
