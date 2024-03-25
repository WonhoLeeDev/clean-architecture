package hhplus.cleanarchitecture.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Lecture {

    @Id
    @Column(name = "lecture_id")
    private Long id;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    private String name;

    public Lecture(Long id, List<Registration> registrations, String name) {
        this.id = id;
        this.registrations = registrations;
        this.name = name;
    }
}
