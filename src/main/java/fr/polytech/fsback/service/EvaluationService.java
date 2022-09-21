package fr.polytech.fsback.service;

import fr.polytech.fsback.entity.EvaluationEntity;
import fr.polytech.fsback.entity.RestaurantEntity;
import fr.polytech.fsback.exception.ResourceDoesntExistException;
import fr.polytech.fsback.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    private final RestaurantService restaurantService;

    public EvaluationEntity getEvaluationById(int id) {
        return this.evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesntExistException("evaluation with id " + id + " doesn't exists"));
    }
    public EvaluationEntity addEvaluation(int restaurantId, String messageEvaluation, String nomEvaluation, int noteEvaluation) {
        final RestaurantEntity restaurant = restaurantService.getRestaurantById(restaurantId);
        final EvaluationEntity nouvelleEvaluation = EvaluationEntity.builder().message(messageEvaluation).user(nomEvaluation).note(noteEvaluation).restaurant(restaurant).build();

        return this.evaluationRepository.save(nouvelleEvaluation);
    }
    public boolean delEvaluation(int restaurantId, int evaluationId) {
        final RestaurantEntity restaurant = restaurantService.getRestaurantById(restaurantId);
        final EvaluationEntity evaluation = getEvaluationById(evaluationId);
        final EvaluationEntity deleteEvaluation = EvaluationEntity.builder().id(evaluationId).restaurant(restaurant).build();

        this.evaluationRepository.delete(deleteEvaluation);
        return true;
    }
}
