package chocolate.chocho.controller;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.service.StoreCmdServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static chocolate.chocho.controller.StoreCmdController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreCmdController.class)
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
public class StoreCmdControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StoreCmdServiceImpl storeService;


    @Test
    public void registerStore() throws Exception {
        //given
        StoreCmdRequest storeCmdRequest = getStoreCmdRequest();
        //when, then
        given(storeService.registerStore(any(StoreCmdDto.class))).willReturn(1L);
        mockMvc.perform(post("/api/stores")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(convertToString(storeCmdRequest)))
                .andDo(print())
                .andExpect(jsonPath("id").value(1L));
    }

    @Test
    public void updateStore() throws Exception {
        //given
        UpdateStoreRequest updateRequest = new UpdateStoreRequest("samsung", "seoul", "songpa-dong", "zipcode");
        StoreCmdDto dto = new StoreCmdDto("samsung", "seoul", "songpa-dong", "zipcode");
        given(storeService.updateStore(any(Long.class), any(StoreCmdDto.class))).willReturn(dto);
        //when then
        mockMvc.perform(post("/api/stores/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(convertToString(updateRequest)))
                .andDo(print())
                .andExpect(jsonPath("name").value("samsung"))
                .andExpect(jsonPath("city").value("seoul"))
                .andExpect(jsonPath("street").value("songpa-dong"))
                .andExpect(jsonPath("zipcode").value("zipcode"));
    }

    private StoreCmdRequest getStoreCmdRequest() {
        return new StoreCmdRequest("starbucks",
                "city",
                "street",
                "zipcode");
    }


    private String convertToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}
