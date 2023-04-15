package org.resources.restmanager.model.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Computer extends Resource {
    @NonNull
    private String CPU;
    @NonNull
    private String disk;
    @NonNull
    private int RAM;
    @NonNull
    private String screen;


    @Override
    public String getResourceType(){
        return "Computer";
    }
}
