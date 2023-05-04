package org.resources.restmanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("director")
public class DepartmentDirector extends Teacher {
    @JsonIgnore
    @OneToMany(mappedBy = "departmentDirector", fetch = FetchType.LAZY)
    private List<Notification> notifications;

    public DepartmentDirector(String firstName, String lastName, String userName, String password, String department){
        super(firstName,lastName,userName,password,department);
    }
}
