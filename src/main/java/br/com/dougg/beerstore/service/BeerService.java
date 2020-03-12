package br.com.dougg.beerstore.service;

import br.com.dougg.beerstore.model.Beer;
import br.com.dougg.beerstore.repository.Beers;
import br.com.dougg.beerstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

    private Beers beers;

    public BeerService(@Autowired Beers beers) {
        this.beers = beers;
    }

    public Beer save(final Beer beer) {
        Optional<Beer> beerByNameAndType = beers.findByNameAndType(beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent())
            throw new BeerAlreadyExistException();

        return beers.save(beer);
    }
}
