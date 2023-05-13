package org.resources.restmanager.model.DTO.mouhsine;



import lombok.*;
import org.resources.restmanager.model.entities.auth.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class UserDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;
    private String department;

    private String password;

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .build();
    }
}
