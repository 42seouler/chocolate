package chocolate.chocho.controller;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.repository.StoreRepository;
import chocolate.chocho.service.StoreCmdServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreCmdController {

    private final StoreCmdServiceImpl storeService;

    @PostMapping("/api/stores")
    public StoreCmdResponse registerStore(@RequestBody @Valid StoreCmdRequest request) {
        StoreCmdDto storeCmdDto = new StoreCmdDto(request.getName(),
                request.getCity(),
                request.getStreet(),
                request.getZipcode());
        return new StoreCmdResponse(storeService.registerStore(storeCmdDto));
    }

    @PostMapping("/api/stores/{id}")
    public UpdateStoreResponse updateStore(@PathVariable("id") Long id, @RequestBody @Valid UpdateStoreRequest request) {
        StoreCmdDto Dto = new StoreCmdDto(request.getName(), request.getCity(), request.street, request.zipcode);
        StoreCmdDto result = storeService.updateStore(id, Dto);
        return new UpdateStoreResponse(result.getName(), result.getCity(), result.getStreet(), result.getZipcode());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class StoreCmdRequest {

        private String name;
        private String city;
        private String street;
        private String zipcode;
    }

    @Data
    @NoArgsConstructor
    static class StoreCmdResponse {

        private Long id;

        public StoreCmdResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UpdateStoreRequest {

        private String name;
        private String city;
        private String street;
        private String zipcode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UpdateStoreResponse {

        private String name;
        private String city;
        private String street;
        private String zipcode;
    }
}
