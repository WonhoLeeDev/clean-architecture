package hhplus.cleanarchitecture.controller;

import hhplus.cleanarchitecture.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final RegistrationService registrationService;
    /**
     * TODO - 특강 신청 완료 여부 조회
     */
    @GetMapping("{id}")
    public List<RegistrationDto> lecture(@PathVariable Long id) {
        return registrationService.findRegistrationsByUserId(id);
    }

    /**
     * TODO - 특강 신청
     */
    @PostMapping("{id}/register")
    public RegistrationDto register(@PathVariable Long id, @RequestBody Long lectureId) {
        return registrationService.register(id, lectureId);
    }
}
