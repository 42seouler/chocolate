package chocolate.chocho.controller;

import chocolate.chocho.dto.StoreCreateDto;
import chocolate.chocho.dto.StoreUpdateDto;
import chocolate.chocho.service.StoreCmdServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreCmdController {

    private final StoreCmdServiceImpl storeService;

    @PostMapping("/api/stores")
    public StoreCmdResponse registerStore(@RequestBody @Valid StoreCmdRequest request) {
        StoreCreateDto storeCreateDto = new StoreCreateDto(request.getName(),
                request.getCity(),
                request.getStreet(),
                request.getZipcode());
        return new StoreCmdResponse(storeService.registerStore(storeCreateDto));
    }

    @PostMapping("/api/stores/{id}")
    public UpdateStoreResponse updateStore(@PathVariable("id") Long id, @RequestBody @Valid UpdateStoreRequest request) {
        StoreUpdateDto updateStore = storeService.updateStore(id, createUpdateDto(request));
        return new UpdateStoreResponse(updateStore.getCity(), updateStore.getStreet(), updateStore.getZipcode());
    }

    @DeleteMapping("/api/stores/{id}")
    public void deleteStore(@PathVariable("id") Long storeId) {
        storeService.deleteStore(storeId);
    }

    private StoreUpdateDto createUpdateDto(UpdateStoreRequest request) {

        return new StoreUpdateDto(request.city, request.getStreet(), request.getZipcode());
    }

    @Data
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

        private String city;
        private String street;
        private String zipcode;
    }
}
