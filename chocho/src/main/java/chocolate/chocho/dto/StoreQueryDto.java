package chocolate.chocho.dto;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreQueryDto {

    private String      name;
    private Address address;
    private User user;

    public StoreQueryDto(String name, Address address, User user) {
        this.name = name;
        this.address = address;
        this.user = user;
    }
}
