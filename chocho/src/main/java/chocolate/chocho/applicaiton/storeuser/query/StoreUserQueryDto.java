package chocolate.chocho.applicaiton.storeuser.query;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreUserQueryDto {

    private String  name;
    private Address address;
    private Store   store;

    public StoreUserQueryDto(String name, Address address, Store store) {
        this.name = name;
        this.address = address;
        this.store = store;
    }
}
