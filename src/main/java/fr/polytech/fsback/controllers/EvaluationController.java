package fr.polytech.fsback.controllers;

import fr.polytech.fsback.dto.EvaluationDto;
import fr.polytech.fsback.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("restaurants/{restaurantId}/evaluations")
    public @ResponseBody EvaluationDto addEvaluation(@Valid @RequestBody EvaluationDto dto, @PathVariable int restaurantId) {
        return EvaluationDto.fromEntity(this.evaluationService.addEvaluation(restaurantId, dto.getMessage(), dto.getUser(), dto.getNote()));
    }

    @DeleteMapping("restaurants/{restaurantId}/evaluations/{evaluationId}")
    public @ResponseBody boolean delEvaluation(@Valid @RequestBody @PathVariable int restaurantId, int evaluationId) {
        return this.evaluationService.delEvaluation(restaurantId, evaluationId);
    }

}