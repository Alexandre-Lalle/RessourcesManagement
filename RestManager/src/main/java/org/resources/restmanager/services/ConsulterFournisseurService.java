package org.resources.restmanager.services;

import jakarta.transaction.Transactional;
import org.resources.restmanager.model.entities.Provider;
import org.resources.restmanager.model.entities.auth.User;
import org.resources.restmanager.repositories.ProviderRepository;
import org.resources.restmanager.repositories.auth.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ConsulterFournisseurService {
    private final AppUserRepository userRepository;


    public ConsulterFournisseurService(AppUserRepository userRepository) {
        this.userRepository=userRepository;
    }
    public Provider addProvider(Provider fournisseur){
        return userRepository.save(fournisseur);
    }

//    public List<Provider> findAllProviders(){
//        return userRepository.findAllByRoles_Name("provider");
//    }
    public List<Provider> findAllProviders2(){
        List<User> users = userRepository.findAll();
        List<Provider> providers = new ArrayList<>();

        for(User user: users) {
            if(user instanceof Provider) {
                providers.add((Provider) user);
            }
        }

        return providers;
    }

    public Provider updateProvider(Provider fournisseur){
        return userRepository.save(fournisseur);
    }
    public void deleteProviderById(Long id){
        userRepository.deleteProviderById(id);
    }
    public Provider findProvider(Provider fournisseur) {
        List<Provider> result = this.findAllProviders2();
        Provider res = null;
        for (Provider fournisseur1 : result) {
            if (fournisseur.getUserName().equalsIgnoreCase(fournisseur1.getUserName())) {
                res = fournisseur1; // Set to true if a match is found

                break; // Break out of the loop when a match is found
            }
        }
        return res;
    }
}
