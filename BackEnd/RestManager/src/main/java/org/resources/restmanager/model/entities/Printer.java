package org.resources.restmanager.model.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Printer extends Resource {
    @NonNull
    private String resolution;
    @NonNull
    private int printSpeed;

    public Printer(String name, String resolution, int printSpeed){
        super(name);
        this.resolution = resolution;
        this.printSpeed = printSpeed;
    }

    @Override
    public String getResourceType(){
        return "Printer";
    }
}
