package org.resources.restmanager.model.DTO.zouine;

import lombok.Data;
import org.resources.restmanager.model.entities.auth.User;

@Data
public class AuthResponseDTO {
    private User user;
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(User user, String accessToken){
        this.user = user;
        this.accessToken = accessToken;
    }
}
