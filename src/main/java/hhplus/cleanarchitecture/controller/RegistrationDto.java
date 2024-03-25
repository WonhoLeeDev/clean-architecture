package hhplus.cleanarchitecture.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class RegistrationDto {
    private Long id;
    private UserDto userDto;
    private LectureDto lectureDto;
    private LocalDateTime registrationDate;
}
