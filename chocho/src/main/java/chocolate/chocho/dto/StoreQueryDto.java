package chocolate.chocho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class StoreQueryDto {

    private String name;
    private String city;
    private String street;
    private String zipcode;
}
