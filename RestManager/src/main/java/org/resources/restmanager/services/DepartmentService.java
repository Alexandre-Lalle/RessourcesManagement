package org.resources.restmanager.services;


import org.resources.restmanager.model.entities.Notification;
import org.resources.restmanager.model.entities.Report;
import org.resources.restmanager.model.entities.Teacher;
import org.resources.restmanager.repositories.NotificationRepository;
import org.resources.restmanager.repositories.ReportRepository;
import org.resources.restmanager.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private TeacherRepository teacherRepository;
    private NotificationRepository notificationRepository;
    private ReportRepository reportRepository;

    @Autowired
    public DepartmentService(TeacherRepository teacherRepository,
                             NotificationRepository notificationRepository,
                             ReportRepository reportRepository){
        this.teacherRepository = teacherRepository;
        this.notificationRepository = notificationRepository;
        this.reportRepository = reportRepository;
    }

    public List<Teacher> getTeachers(String department){
        return teacherRepository.findAllByDepartment(department);
    }
    public List<String> getTeacherMails(String department){
        return teacherRepository.findAllMailsByDepartment(department);
    }

    public Optional<Notification> getNotification(Long id){
        return notificationRepository.findById(id);
    }

    public List<Notification> getNotificationByDirectorID(Long id){
        try{
            return notificationRepository.getNotificationsByDepartmentDirector_Id(id);
        }catch (Exception e){
            System.out.println("Error while fetching notifications for director : \n");
            return null;
        }
    }

    public boolean addNotification(Notification notification){
        try{
            notificationRepository.save(notification);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateNotification(Notification notification){
        try{
            notificationRepository.save(notification);
            return true;
        }catch (Exception e){
            System.out.println("Error while updating the noification("+notification.getId()+") : \n"+e.getMessage());
            return false;
        }
    }

    public boolean deleteNotification(Long id){
        try{
            notificationRepository.deleteById(id);
            return true;
        }catch(Exception e ){
            System.out.println("Error while deleting the notification(id="+id+") : \n"+e.getMessage());
            return false;
        }
    }


    public Report getReportFromFailure(Long id){
        try{
            return reportRepository.findByFailure_Id(id);
        }catch (Exception e){
            System.out.println("Error while fetching the report : \n"+e.getMessage());
            return null;
        }
    }

    public List<String> getAllDepartements(){
        return teacherRepository.findAllDepartments();
    }



    ///////////////////////////////////////////////////////////////////////////////////


}
