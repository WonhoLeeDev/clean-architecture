package hhplus.cleanarchitecture.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class LectureDto {
    public final static int MAX_REGISTRATION_NUM = 30;
    private Long id;
    private String name;
}
