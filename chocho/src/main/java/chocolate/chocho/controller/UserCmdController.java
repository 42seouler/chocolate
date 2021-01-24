package chocolate.chocho.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCmdController {

    @Data
    @AllArgsConstructor
    static class CreateUserRequest {

        private String name;
        private String city;
        private String street;
        private String zipcode;
    }
}
