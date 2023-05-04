package org.resources.restmanager.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.resources.restmanager.model.entities.auth.User;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("technician")
public class Technician extends User {

    public Technician(String firstName, String lastName, String userName, String password){
        super(firstName,lastName,userName,password);
    }
}
