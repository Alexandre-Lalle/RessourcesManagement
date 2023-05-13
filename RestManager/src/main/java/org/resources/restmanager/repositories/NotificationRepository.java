package org.resources.restmanager.repositories;

import org.resources.restmanager.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public List<Notification> getNotificationsByDepartmentDirector_Id(Long id);
    @Query("SELECT n FROM Notification  n, DepartmentDirector d WHERE n.departmentDirector = d AND d.department=:department ORDER BY n.id DESC")
    List<Notification> findDemandesByDepartementd_IdOrderByIdDesc(@Param("department") String department);
}
