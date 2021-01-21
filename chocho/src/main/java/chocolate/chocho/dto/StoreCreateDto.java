package chocolate.chocho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreCreateDto {

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public StoreCreateDto(String name, String city, String street, String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
