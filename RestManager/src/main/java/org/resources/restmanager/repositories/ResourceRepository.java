package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Failure;
import org.resources.restmanager.model.entities.Resource;
import org.resources.restmanager.model.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource,Long> {
    @Query("SELECT rc FROM Resource  rc JOIN rc.teachers t WHERE t.department = :department")
    public List<Resource> findAllDemandsByDepartment(@Param("department") String department);
    @Query("SELECT r FROM Resource r,Teacher t, Notification n WHERE t.id=:idEns AND t MEMBER r.teachers AND r MEMBER n.resources AND n.id =:id")
    List<Resource> findByEnseignant_IdAndDemande_Id(@Param("idEns") long enseignant_id,@Param("id") long demande_id);

    @Query("SELECT rc.failures FROM Resource rc WHERE rc.id = :id")
    List<Failure> getFailuresByResourceId(@Param("id") Long id);

    @Query("SELECT r FROM Resource r WHERE r.offre IS NULL")
    List<Resource> findRessourcesWithoutOffre();

    @Query("SELECT rc FROM Resource rc, Teacher t WHERE t MEMBER rc.teachers AND t.id = :id")
    public List<Resource> findAllByTeachersContains(@Param("id") Long id);

}
