package org.resources.restmanager.services;

import org.resources.restmanager.repositories.NotificationRepository;
import org.resources.restmanager.model.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAlNotifications(){
        return notificationRepository.findAll();
    }

    public List<Notification> getNosNotificationsByDirectorId(Long id){
        return notificationRepository.getNotificationsByDepartmentDirector_Id(id);
    }

    public boolean addNotification(Notification notification){
        try {
            notificationRepository.save(notification);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteNotification(Long id){
        try{
            notificationRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
