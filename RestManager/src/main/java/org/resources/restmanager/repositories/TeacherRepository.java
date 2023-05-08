package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    public List<Teacher> findAllByDepartment(String department);
    @Query("SELECT t.email FROM Teacher t WHERE t.department=:department")
    public List<String> findAllMailsByDepartment(@Param("department") String department);

    @Query(value = "select d from DepartmentDirector d where d.department=:x ")
    Teacher findByDepartmentAndChef(@Param("x") String department );
    List<Teacher> findByDepartment(String department);

    @Query("SELECT DISTINCT t.department FROM Teacher t")
    public List<String> findAllDepartments();
}
