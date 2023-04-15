package org.resources.restmanager.services;

import org.resources.restmanager.repositories.DepartmentDirectorRepository;
import org.resources.restmanager.model.entities.DepartmentDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceOld {
    DepartmentDirectorRepository departmentDirectorRepository;



    @Autowired
    public DepartmentServiceOld(DepartmentDirectorRepository departmentDirectorRepository){
        this.departmentDirectorRepository = departmentDirectorRepository;
    }

    public List<DepartmentDirector> getDepartmentDirectors(){
        return departmentDirectorRepository.findAll();
    }
    public Optional<DepartmentDirector> getDepartmentDirector(Long id){return departmentDirectorRepository.findById(id);}
    public boolean addDepartmentDirector(DepartmentDirector departmentDirector){
        try{
            departmentDirectorRepository.save(departmentDirector);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteDepartmentDirector(Long id){
        try{
            departmentDirectorRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
