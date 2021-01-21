package chocolate.chocho.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreUpdateDto {

    private String city;
    private String street;
    private String zipcode;

    public StoreUpdateDto(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
