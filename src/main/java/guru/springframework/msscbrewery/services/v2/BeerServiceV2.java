package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDtoV2 getById(UUID beerId);

    BeerDtoV2 save(BeerDtoV2 beerDtoV2);

    void update(UUID beerId, BeerDtoV2 beerDtoV2);

    void deleteById(UUID beerId);
}
