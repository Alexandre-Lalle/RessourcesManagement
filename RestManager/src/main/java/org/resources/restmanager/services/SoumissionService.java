package org.resources.restmanager.services;


import org.resources.restmanager.model.entities.Soumission;
import org.resources.restmanager.repositories.SoumissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoumissionService {
    @Autowired
    SoumissionRepo soumissionRepo;

    public boolean AcceptSoumission(Long id_s,Long id_o){
        List<Soumission> soumissionList ;
        try {
            soumissionList = soumissionRepo.findSoumissionInOffre(id_o);
            for( Soumission sm : soumissionList){
                if(sm.getId() == id_s){
                    sm.setEtat(1);
                }else{
                    sm.setEtat(-1);
                }
                soumissionRepo.save(sm);
            }
            return true ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
    public Soumission getSoumissionById(Long id){
        return soumissionRepo.getReferenceById(id);
    }
}
