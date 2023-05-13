package org.resources.restmanager.model.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.model.entities.Teacher;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Affectation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dateAffectation;

	@ManyToMany
	@JoinColumn(name = "enseignant_id")
	private List<Teacher> teacherList;

	@ManyToOne
	@JoinColumn(name = "ressource_id")
	private Resource resource;

}