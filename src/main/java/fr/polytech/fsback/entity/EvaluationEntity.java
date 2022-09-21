package fr.polytech.fsback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Evaluation")
public class EvaluationEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "nom_user")
    private String user;

    @Column(name = "note")
    private int note;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private RestaurantEntity restaurant;

}
