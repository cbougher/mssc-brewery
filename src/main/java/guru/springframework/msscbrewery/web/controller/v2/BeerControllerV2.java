package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping(value = {"/{beerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public BeerDtoV2 get(@PathVariable UUID beerId) {

        return beerService.getById(beerId);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID beerId, @RequestBody BeerDtoV2 beerDto) {
        beerService.update(beerId, beerDto);
    }

    @PostMapping
    public ResponseEntity<BeerDtoV2> newBeer(@RequestBody BeerDtoV2 beerDto) {
        BeerDtoV2 savedBeerDto = beerService.save(beerDto);

        String url = "/api/v2/beer/{beerId}";
        Map<String, UUID> params = new HashMap<>();
        params.put("beerId", savedBeerDto.getId());
        URI location = UriComponentsBuilder.fromUriString(url)
                .buildAndExpand(params)
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<>(savedBeerDto, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
    }
}
