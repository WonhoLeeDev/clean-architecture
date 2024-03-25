package hhplus.cleanarchitecture.domain;

import hhplus.cleanarchitecture.controller.RegistrationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@ToString
public class Registration {

    @Id
    @GeneratedValue
    @Column(name = "registration_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    private LocalDateTime registrationDate;

    protected Registration() {}

    public static Registration createRegistration(User user, Lecture lecture) {
        Registration registration = new Registration();
        registration.setUser(user);
        registration.setLecture(lecture);
        registration.setRegistrationDate(LocalDateTime.now());
        return registration;
    }

    public static RegistrationDto toDto(Registration registration) {
        return new RegistrationDto(
                registration.getId(),
                User.toDto(registration.getUser()),
                Lecture.toDto(registration.getLecture()),
                registration.getRegistrationDate()
        );
    }
}
