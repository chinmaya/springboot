package hello;

import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGet() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.get("/register")
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Register")));
    }
    
    @Test
    public void testPost() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.post("/register")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			.param("name", "test")
    			.param("email", "email@email"))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("email@email")));
    }
}
