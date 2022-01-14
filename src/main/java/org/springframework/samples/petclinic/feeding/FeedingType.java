package org.springframework.samples.petclinic.feeding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.sym.Name;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="feedingtype")
public class FeedingType extends BaseEntity{
    @NotNull
    @Size(min=3,max=50)
    @Column(name="name",unique=true)
    String name;
    @NotNull
    String description;
    
    @ManyToOne
	@JoinColumn(name = "pet_type_id")
    @NotNull
    PetType petType;
}
