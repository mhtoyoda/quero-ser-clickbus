package br.com.clickbus.repository;


import br.com.clickbus.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaceRepository extends MongoRepository<Place, String> {

    List<Place> findPlaceByName(String name);
}
