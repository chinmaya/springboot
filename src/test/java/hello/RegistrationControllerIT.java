package hello;

import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate testTemplate;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/register");
    }

    @Test
    public void registerForm() throws Exception {
    	ResponseEntity<String> response = testTemplate.getForEntity(base.toString(), String.class);
    	Assert.assertTrue("Find string register", response.getBody().contains("register"));
    }
    
    @Test
    public void postRegisteration() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    	map.add("name", "some name");
    	map.add("email", "someemail@example.org");

    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

    	ResponseEntity<String> response = testTemplate.postForEntity(base.toString(), request, String.class);
		Assert.assertTrue("Find email value", response.getBody().contains("someemail@example.org"));
    }
}
