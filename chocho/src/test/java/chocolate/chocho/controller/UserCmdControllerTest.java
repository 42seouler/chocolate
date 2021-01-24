package chocolate.chocho.controller;

import chocolate.chocho.entity.User;
import chocolate.chocho.service.UserCmdServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static chocolate.chocho.controller.UserCmdController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserCmdController.class)
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
public class UserCmdControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserCmdServiceImpl userCmdService;

    @Test
    public void createUser() throws Exception {
        //given
        CreateUserRequest userRequest = getCreateUserRequest();
        //when //then
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRequest)))
                .andExpect(jsonPath("name").value("seouler"))
                .andExpect(jsonPath("city").value("city"))
                .andExpect(jsonPath("street").value("street"))
                .andExpect(jsonPath("zipcode").value("zipcode"));
    }

    private CreateUserRequest getCreateUserRequest() {
        return new CreateUserRequest("seouler", "city", "street", "zipcode");
    }

    private String asJsonString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

}
