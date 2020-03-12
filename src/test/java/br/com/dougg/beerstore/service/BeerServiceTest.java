package br.com.dougg.beerstore.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import br.com.dougg.beerstore.model.Beer;
import br.com.dougg.beerstore.model.BeerType;
import br.com.dougg.beerstore.repository.Beers;
import br.com.dougg.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private Beers beersMocked;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beersMocked);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void shouldDenyCreationOfBeerThatExists() {
        Beer beerInDataBase = new Beer();
        beerInDataBase.setId(10L);
        beerInDataBase.setName("Heineken");
        beerInDataBase.setVolume(new BigDecimal("355"));
        beerInDataBase.setType(BeerType.PILSEN);

        when(beersMocked.findByNameAndType("Heineken", BeerType.PILSEN)).thenReturn(Optional.of(beerInDataBase));

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.PILSEN);
        newBeer.setVolume(new BigDecimal("355"));

        beerService.save(newBeer);
    }

    @Test
    public void shouldCreateNewBeer() {
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.PILSEN);
        newBeer.setVolume(new BigDecimal("355"));

        Beer newBeerInDatabase = new Beer();
        newBeerInDatabase.setId(10L);
        newBeerInDatabase.setName("Heineken");
        newBeerInDatabase.setType(BeerType.PILSEN);

        when(beersMocked.save(newBeer)).thenReturn(newBeerInDatabase);
        Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.PILSEN));
    }
}
