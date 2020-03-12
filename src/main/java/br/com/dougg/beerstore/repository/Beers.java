package br.com.dougg.beerstore.repository;

import br.com.dougg.beerstore.model.Beer;
import br.com.dougg.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Beers extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);

}
