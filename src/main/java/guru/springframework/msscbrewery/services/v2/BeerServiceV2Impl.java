package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import guru.springframework.msscbrewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getById(UUID beerId) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.IPA)
                .build();
    }

    @Override
    public BeerDtoV2 save(BeerDtoV2 beerDtoV2) {
        return BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName(beerDtoV2.getBeerName())
                .beerStyle(beerDtoV2.getBeerStyle())
                .build();
    }

    @Override
    public void update(UUID beerId, BeerDtoV2 beerDtoV2) {
        // @Todo update the beer
    }

    @Override
    public void deleteById(UUID beerId) {
        // @Todo delete the beer
        log.debug("Deleting BeerV2 {}", beerId);
    }
}
