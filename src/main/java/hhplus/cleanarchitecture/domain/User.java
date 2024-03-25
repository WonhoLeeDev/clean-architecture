package hhplus.cleanarchitecture.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
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
}
