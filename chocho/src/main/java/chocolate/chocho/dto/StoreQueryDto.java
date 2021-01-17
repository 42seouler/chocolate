package chocolate.chocho.dto;

import chocolate.chocho.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreQueryDto {

    private String      name;
    private Address address;
    private Employer employer;

    public StoreQueryDto(String name, Address address, Employer employer) {
        this.name = name;
        this.address = address;
        this.employer = employer;
    }
}
