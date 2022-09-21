package fr.polytech.fsback.service;

import fr.polytech.fsback.entity.RestaurantEntity;
import fr.polytech.fsback.exception.InvalidValueException;
import fr.polytech.fsback.exception.ResourceDoesntExistException;
import fr.polytech.fsback.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantEntity getRestaurantById(int id) {
        return this.restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesntExistException("restaurant with id " + id + " doesn't exists"));
    }

    public List<RestaurantEntity> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public RestaurantEntity addRestaurant(final String Nom, String Adresse) {
        final RestaurantEntity nouveauRestaurant = RestaurantEntity.builder().nom(Nom).adresse(Adresse).evaluations(new ArrayList<>()).build();
        return this.restaurantRepository.save(nouveauRestaurant);
    }

    public RestaurantEntity updateRestaurant(int id, String nouveauNom, String nouvelleAdresse) {
        if (nouveauNom == null) {
            throw new InvalidValueException("le nouveau nom ne doit pas être null");
        }
        if (nouvelleAdresse == null) {
            throw new InvalidValueException("la nouvelle adresse ne doit pas être null");
        }
        final RestaurantEntity restaurantToUpdate = this.restaurantRepository.findById(id).orElseThrow(() -> new ResourceDoesntExistException("le restaurant d'id " + id + " n'existe pas"));
        restaurantToUpdate.setNom(nouveauNom);
        restaurantToUpdate.setAdresse(nouvelleAdresse);
        restaurantRepository.save(restaurantToUpdate);
        return restaurantToUpdate;
    }


}
