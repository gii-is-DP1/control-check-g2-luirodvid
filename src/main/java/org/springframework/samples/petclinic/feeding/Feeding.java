package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feeding")
public class Feeding extends BaseEntity {
	@Column(name = "start_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    LocalDate startDate;
    @Column(name="weeks_duration")
    @Min(1)
    @NotNull
    double weeksDuration;
    @ManyToOne
	@JoinColumn(name = "pet_id")
    @NotNull
    Pet pet;   
    @JoinColumn(name="feeding_type_id")
    @ManyToOne
    FeedingType feedingType;
}
