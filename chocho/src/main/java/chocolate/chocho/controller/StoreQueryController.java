package chocolate.chocho.controller;

import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.service.StoreQueryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreQueryController {

    private final StoreQueryServiceImpl storeQueryService;

    @GetMapping("/api/stores/{id}")
    public StoreQueryDto findById(@PathVariable("id") Long storeId) {
        return storeQueryService.findById(storeId);
    }
}
