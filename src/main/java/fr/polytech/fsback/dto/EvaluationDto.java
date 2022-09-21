package fr.polytech.fsback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.fsback.entity.EvaluationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EvaluationDto {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("nom_user")
    @Size(max = 50)
    private String user;

    @NotBlank
    @JsonProperty("message")
    @Size(max = 255)
    private String message;

    @JsonProperty("note")
    private int note;


    public static EvaluationDto fromEntity(EvaluationEntity entity) {
        return EvaluationDto.builder().id(entity.getId()).user(entity.getUser()).message(entity.getMessage()).note(entity.getNote()).build();
    }

}
