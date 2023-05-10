package org.resources.restmanager.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String subject;
    @NonNull
    private String content;
    @CreationTimestamp
    private Date date;
    @ManyToMany
    private List<Resource> resources;
    @ManyToOne
    private DepartmentDirector departmentDirector;
}
