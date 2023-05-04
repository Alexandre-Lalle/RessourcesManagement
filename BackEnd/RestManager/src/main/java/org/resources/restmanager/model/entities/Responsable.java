package org.resources.restmanager.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.resources.restmanager.model.entities.auth.User;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("manager")
public class Responsable extends User implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String nom;
//    private String prenom;
//    private String mail;
//    private  String password;
    private String departement;
     @OneToMany(mappedBy = "responsable")
    private  List<Offre> offreList;


    public Responsable(String firstName, String lastName, String userName, String password, String department){
        super(firstName,lastName,userName,password);
        this.departement = department;
    }
}
