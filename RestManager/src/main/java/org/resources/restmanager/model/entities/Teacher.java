package org.resources.restmanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.resources.restmanager.model.entities.auth.User;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("teacher")
public class Teacher extends User {
    @NonNull
    private String department;
    @JsonIgnore
    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    private List<Resource> resources;
    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Failure> failures;

    public Teacher(Long id){
        super(id);
    }

    public Teacher(String firstName, String lastName, String userName, String password, String department){
        super(firstName,lastName,userName,password);
        this.department = department;
    }
}
