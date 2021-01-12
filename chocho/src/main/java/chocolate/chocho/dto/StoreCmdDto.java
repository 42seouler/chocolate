package chocolate.chocho.dto;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreCmdDto {

    private String      name;
    private Address     address;

    public StoreCmdDto(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
