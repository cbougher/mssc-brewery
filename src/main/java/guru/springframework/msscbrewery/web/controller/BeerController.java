package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(value = {"/{beerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public BeerDto get(@PathVariable UUID beerId) {

        return beerService.getById(beerId);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
        beerService.update(beerId, beerDto);
    }

    @PostMapping
    public ResponseEntity<BeerDto> newBeer(@RequestBody BeerDto beerDto) {
        BeerDto savedBeerDto = beerService.save(beerDto);

        String url = "/api/v1/beer/{beerId}";
        Map<String, UUID> params = new HashMap<String, UUID>();
        params.put("beerId", savedBeerDto.getId());
        URI location = UriComponentsBuilder.fromUriString(url)
                .buildAndExpand(params)
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<BeerDto>(savedBeerDto, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
    }
}
