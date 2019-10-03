package br.com.clickbus.business;

import br.com.clickbus.exception.PlaceNotFoundException;
import br.com.clickbus.model.Place;
import br.com.clickbus.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PlaceBusiness {

    @Autowired
    private PlaceRepository placeRepository;

    public Place savePlace(Place place){
        place.setCreatedAt(LocalDateTime.now());
        Place saved = placeRepository.save(place);
        return saved;
    }

    public Place updatePlace(String id, Place place) throws PlaceNotFoundException {
        Optional<Place> placeOptional = placeRepository.findById(id);
        if(placeOptional.isPresent()){
            Place placeFinded = placeOptional.get();
            placeFinded.setName(place.getName());
            placeFinded.setSlug(place.getSlug());
            placeFinded.setState(place.getState());
            placeFinded.setCity(place.getCity());
            placeFinded.setState(place.getState());
            placeFinded.setUpdatedAt(LocalDateTime.now());

            return placeRepository.save(placeFinded);
        }else{
            throw new PlaceNotFoundException("Place not found");
        }
    }

    public Place findById(String id) throws PlaceNotFoundException {
        Optional<Place> placeOptional = placeRepository.findById(id);
        if(placeOptional.isPresent()){
            return placeOptional.get();
        }else{
            throw new PlaceNotFoundException("Place not found");
        }
    }

    public List<Place> findPlaceByName(String name) throws PlaceNotFoundException {
        Optional<List<Place>> list = Optional.ofNullable(placeRepository.findPlaceByName(name));
        if(list.isPresent()){
            return list.get();
        }else{
            throw new PlaceNotFoundException("Places not found");
        }
    }

}
