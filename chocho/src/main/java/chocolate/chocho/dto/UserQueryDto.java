package chocolate.chocho.dto;

import chocolate.chocho.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserQueryDto {

    private String  name;
    private Address address;

    public UserQueryDto(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}