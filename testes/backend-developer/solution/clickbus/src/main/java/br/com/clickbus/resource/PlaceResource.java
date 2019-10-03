package br.com.clickbus.resource;

import br.com.clickbus.business.PlaceBusiness;
import br.com.clickbus.exception.PlaceNotFoundException;
import br.com.clickbus.model.Place;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Place")
@RestController
@RequestMapping("/place")
@Slf4j
public class PlaceResource {

    @Autowired
    private PlaceBusiness placeBusiness;

    @ApiOperation(value = "Create new place", response = String.class, notes = "This operation saves a new place")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return saved place", response = String.class),
            @ApiResponse(code = 500, message = "Return error", response = String.class)
    })
    @PostMapping
    public ResponseEntity createPlace(@RequestBody Place place){

        try{
            Place placeSaved = placeBusiness.savePlace(place);
            return ResponseEntity.ok(placeSaved);
        }catch (Exception e){
            log.error("Error while save place - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Update place", response = String.class, notes = "This operation update place")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return updated place", response = String.class),
            @ApiResponse(code = 404, message = "Returns error not found place", response = String.class),
            @ApiResponse(code = 500, message = "Return error", response = String.class)
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity updatePlace(@PathVariable("id") String id, @RequestBody Place place){

        try{
            Place placeSaved = placeBusiness.updatePlace(id, place);
            return ResponseEntity.ok(placeSaved);
        }catch (PlaceNotFoundException e){
            log.error("Error while update place - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            log.error("Error while update place - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Get place by id", response = String.class, notes = "This operation return place by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return place", response = String.class),
            @ApiResponse(code = 404, message = "Returns error not found place", response = String.class),
            @ApiResponse(code = 500, message = "Return error", response = String.class)
    })
    @GetMapping(value = "/id/{id}")
    public ResponseEntity findPlaceById(@PathVariable("id") String id){
        try{
            Place place = placeBusiness.findById(id);
            return ResponseEntity.ok(place);
        }catch (PlaceNotFoundException e){
            log.error("Error while find place by id: {} - {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            log.error("Error while find place by id: {} - {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Get list place by name", response = String.class, notes = "This operation return list place by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return list place", response = String.class),
            @ApiResponse(code = 404, message = "Returns error not found place", response = String.class),
            @ApiResponse(code = 500, message = "Return error", response = String.class)
    })
    @GetMapping(value = "/name/{name}")
    public ResponseEntity findPlaceListByName(@PathVariable("name") String name){
        try{
            List<Place> place = placeBusiness.findPlaceByName(name);
            return ResponseEntity.ok(place);
        }catch (PlaceNotFoundException e){
            log.error("Error while find place by name: {} - {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            log.error("Error while find place by name: {} - {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
