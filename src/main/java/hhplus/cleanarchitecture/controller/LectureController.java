package hhplus.cleanarchitecture.controller;

import hhplus.cleanarchitecture.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/lecture")
public class LectureController {

    /**
     * TODO - 특강 신청 완료 여부 조회
     */
    @GetMapping("{id}")
    public User lecture(@PathVariable long id) {
        log.info("point id={}", id);
        return null;
    }

    /**
     * TODO - 특강 신청
     */
    @PatchMapping("{id}/register")
    public User register(@PathVariable long id, @RequestBody long amount) {
        log.info("charge id={}, amount={}", id, amount);
        return null;
    }
}
