package chocolate.chocho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreCmdDto {

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public StoreCmdDto(String name, String city, String street, String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
