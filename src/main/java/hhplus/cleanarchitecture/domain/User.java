package hhplus.cleanarchitecture.domain;

import hhplus.cleanarchitecture.controller.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    public User(Long id, String name, List<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.registrations = registrations;
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName()
        );
    }
}
