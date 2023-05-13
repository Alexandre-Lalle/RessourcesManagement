package org.resources.restmanager;

import org.resources.restmanager.model.entities.auth.Role;
import org.resources.restmanager.repositories.*;
import org.resources.restmanager.model.entities.*;
import org.resources.restmanager.repositories.auth.AppRoleRepository;
import org.resources.restmanager.repositories.auth.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class RestManagerApplication {
    private FailureRepository failureRepository;
    private AppUserRepository userRepository;
    private ResourceRepository resourceRepository;
    private ReportRepository reportRepository;
    private NotificationRepository notificationRepository;
    private AppRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public RestManagerApplication(FailureRepository failureRepository, AppUserRepository userRepository, ResourceRepository resourceRepository, ReportRepository reportRepository, NotificationRepository notificationRepository, AppRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.failureRepository = failureRepository;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.reportRepository = reportRepository;
        this.notificationRepository = notificationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(){
        return args -> {
//            Date d1 = new Date();
//            Printer p1 = new Printer("1024x1024",2000);
//            DepartmentDirector dr1 = new DepartmentDirector("zouine","ayoub","ayoub@gmail.com","1234","data_science");
//            Report r1 = new Report("some sheet","frequent","hardware");
//            Failure f1 = new Failure((long)1,new Date(),false,p1,dr1,r1);
//            f1.setProcessed(true);
//            Failure f2 = new Failure(null,new Date(),false,p1,dr1,null);
//            r1.setFailure(f1);
//            Provider pr1 = new Provider("ayoub","Lenovo","samaka@gmail.com","1235");
//            p1.setProvider(pr1);
//            Notification n1 = new Notification("test of the api","salam");
//
//            n1.setDepartmentDirector(dr1);
//            List<Teacher> teachers = new ArrayList<>();
//            teachers.add(dr1);
//            p1.setTeachers(teachers);
//            userRepository.save(dr1);
//            userRepository.save(pr1);
//            resourceRepository.save(p1);
//            notificationRepository.save(n1);
//            Report r11 = reportRepository.save(r1);
//            failureRepository.save(f1);
//            failureRepository.save(f2);
//
            initUserANDRoles();
        };
    }


    private void initUserANDRoles() {

        Teacher teacher = new Teacher("zouine", "ayoub", "ayoub22", "1234", "data_science");
        Teacher teacher2 = new Teacher("zouine", "yassir", "yassir22", "1234", "data_science");
        DepartmentDirector departmentDirector = new DepartmentDirector("zouine", "ayman", "ayman22", "1234", "data_science");
        Technician technician = new Technician("zouine", "omar", "omar22", "1234");
        Responsable manager = new Responsable("zouine","amin","amin22","1234","data_science");
        Provider provider = new Provider("hamza","hp","hamza22","1234");

        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        departmentDirector.setPassword(passwordEncoder.encode(departmentDirector.getPassword()));
        technician.setPassword(passwordEncoder.encode(technician.getPassword()));
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));

        List<Role> teacherRoles = new ArrayList<>();
        List<Role> teacherRoles2 = new ArrayList<>();
        List<Role> directorRoles = new ArrayList<>();
        List<Role> technicianRoles = new ArrayList<>();
        List<Role> managerRoles = new ArrayList<>();
        List<Role> providerRoles = new ArrayList<>();


        teacherRoles.add(new Role("TEACHER"));
        teacherRoles2.add(new Role("TEACHER"));
        teacher.setRoles(teacherRoles);
        teacher2.setRoles(teacherRoles2);

        directorRoles.add(new Role("DIRECTOR"));
        departmentDirector.setRoles(directorRoles);

        technicianRoles.add(new Role("TECHNICIAN"));
        technician.setRoles(technicianRoles);

        managerRoles.add(new Role("MANAGER"));
        manager.setRoles(managerRoles);

        providerRoles.add(new Role("PROVIDER"));
        provider.setRoles(providerRoles);


        userRepository.save(teacher);
//        userRepository.save(teacher2);
        userRepository.save(departmentDirector);
        userRepository.save(technician);
        userRepository.save(manager);
        userRepository.save(provider);
    }
}
