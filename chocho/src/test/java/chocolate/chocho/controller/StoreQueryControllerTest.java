package chocolate.chocho.controller;

import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.service.StoreQueryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.JsonPath;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreQueryController.class)
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
public class StoreQueryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StoreQueryServiceImpl storeQueryService;

    @Test
    public void findById() throws Exception {
        //given
        StoreQueryDto resultDto = new StoreQueryDto("starbucks", "city", "street", "zipcode");
        given(storeQueryService.findById(any(Long.class))).willReturn(resultDto);
        //when //then
        mockMvc.perform(get("/api/stores/{id}", 1))
                .andExpect(jsonPath("name").value("starbucks"));
        verify(storeQueryService, times(1)).findById(any(Long.class));
    }

//    @Test
//    public void findAll() throws Exception {
//        //given
//        List<StoreQueryDto> dto = new ArrayList<>();
//        dto.add(createStoreQueryDto("starbucks", "seoul", "songpa", "24"));
//        dto.add(createStoreQueryDto("coffeebean", "busan", "gaepo", "42"));
//        //when
//        mockMvc.perform(get("/api/stores"))
//                .andDo(print())
//                .andExpect()
//        //then
//    }

    private StoreQueryDto createStoreQueryDto(String name, String city, String street, String zipcode) {
       return new StoreQueryDto(name, city, street, zipcode); 
    }
        
}
