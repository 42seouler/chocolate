package chocolate.chocho.applicaiton.storeuser.command;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreUserCmdDto {

    private String  name;
    private Address address;
    private Store   store;

    public StoreUserCmdDto(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public StoreUserCmdDto(String name, Address address, Store store) {
        this.name = name;
        this.address = address;
        this.store = store;
    }
}


