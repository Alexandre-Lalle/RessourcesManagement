package org.resources.restmanager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.resources.restmanager.model.entities.auth.User;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("provider")
@SecondaryTable(name = "provider")
public class Provider extends User {
    @NonNull
    @Column(table = "provider")
    private String manager;
    @NonNull
    @Column(table = "provider")
    private String name;
    private String place;
    @JsonIgnore
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<Resource> resources;


    public Provider(Long id){
        super(id);
    }

    public Provider(String manager, String name, String userName, String password){
        super("","",userName,password);
        this.manager = manager;
        this.name = name;
    }
}
