package hhplus.cleanarchitecture.controller;

import hhplus.cleanarchitecture.controller.dto.RegistrationDto;
import hhplus.cleanarchitecture.domain.RegistrationStatus;
import hhplus.cleanarchitecture.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    /**
     * TODO - 특강 신청 목록 조회
     */
    @GetMapping("{id}")
    public List<RegistrationDto> registration(@PathVariable Long id) {
        return registrationService.findRegistrationsByUserId(id);
    }

    /**
     * TODO - 특정 특강 신청 여부 조회
     */
    @GetMapping("{id}/lecture/{lectureId}")
    public RegistrationStatus lecture(@PathVariable Long id, @PathVariable Long lectureId) {
        return registrationService.getRegistrationStatus(id, lectureId);
    }

    /**
     * TODO - 특강 신청
     */
    @PostMapping("{id}/lecture")
    public RegistrationDto register(@PathVariable Long id, @RequestBody Long lectureId) {
        return registrationService.register(id, lectureId);
    }
}
