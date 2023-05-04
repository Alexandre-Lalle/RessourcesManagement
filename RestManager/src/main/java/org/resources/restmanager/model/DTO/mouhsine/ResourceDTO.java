package org.resources.restmanager.model.DTO.mouhsine;



import org.resources.restmanager.model.entities.Computer;
import org.resources.restmanager.model.entities.Printer;
import org.resources.restmanager.model.entities.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceDTO {

    public String getResourceType(){return "Resource" ; }

    public static List<Resource> toEntitieList(List<ResourceDTO> resourceDTOList) {
        System.out.println("toEntitieList => "+resourceDTOList);
        List<Resource> resourceList = new ArrayList<>();
        Resource resource = null;
        for (ResourceDTO resourceDTO : resourceDTOList) {
            if (resourceDTO.getResourceType() == "Computer") {
                resource = Computer.toEntity((OrdinateurDTO) resourceDTO);
                resourceList.add(resource);
            } else if (resourceDTO.getResourceType() == "Printer") {
                  resource = Printer.toEntity((ImprimanteDTO) resourceDTO);
                  resourceList.add(resource);
            }
        }
        return resourceList;
    }
}
