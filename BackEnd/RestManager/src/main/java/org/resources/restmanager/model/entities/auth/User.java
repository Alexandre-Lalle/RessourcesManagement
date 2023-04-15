package org.resources.restmanager.model.entities.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type" ,discriminatorType = DiscriminatorType.STRING,length = 10)
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public User(Long id){
        this.id = id;
    }
}
