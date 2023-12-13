package com.example.HOMEWORK.Controller;

import com.example.HOMEWORK.Dto.TimeMachineFormDto;
import com.example.HOMEWORK.Entity.TimeMachine;
import com.example.HOMEWORK.Service.TimeMachineService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Slf4j
@Transactional
@Controller
@RequiredArgsConstructor
public class TimeMachineController {

    private final TimeMachineService timeMachineService;

    @GetMapping("/timeMachine/new")
    public String newTimeMachine(Model model) {
        // 타임머신 등록 폼
        model.addAttribute("timeMachineFormDto", new TimeMachineFormDto());
        return "timeMachine/timeMachineForm";

    }


    @PostMapping("/timeMachine/new")
    public String newTimeMachine(@Valid TimeMachineFormDto timeMachineFormDto, BindingResult bindingResult,
                                 Model model) {
        // 타임머신 등록 로직
        if (bindingResult.hasErrors()) {
            log.info("fuck");
            return "timeMachine/timeMachineForm";
        }

        timeMachineFormDto.setId(UUID.randomUUID().toString());
        timeMachineService.saveTimeMachine(timeMachineFormDto.createTimeMachine());
        return "redirect:/timeMachine/all";

    }


    @GetMapping("/timeMachine/{timeMachineId}/delete")
    public String deleteTimeMachine(Model model, @PathVariable("timeMachineId") String timeMachineId) {
        // 타임머신 삭제 로직
        TimeMachine timeMachine = timeMachineService.findByIdTimeMachine(timeMachineId);
        timeMachineService.deleteTimeMachine(timeMachine);
        return "redirect:/timeMachine/all";
    }


    @GetMapping("/timeMachine/{timeMachineId}/update")
    public String updateTimeMachine(Model model, @PathVariable("timeMachineId") String timeMachineId) {
        // 타임머신 업데이트 폼
        TimeMachine timeMachine = timeMachineService.findByIdTimeMachine(timeMachineId);
        TimeMachineFormDto timeMachineFormDto = TimeMachineFormDto.createTimeMachineFormDto(timeMachine);
        model.addAttribute("timeMachineFormDto", timeMachineFormDto);
        return "timeMachine/timeMachineForm";
    }

    @PostMapping("/timeMachine/{timeMachineId}/update")
    public String updateTimeMachine(TimeMachineFormDto timeMachineFormDto, BindingResult bindingResult, Model model, @PathVariable("timeMachineId") String timeMachineId) {
        // 타임머신 업데이트 로직
        TimeMachine timeMachine = timeMachineService.findByIdTimeMachine(timeMachineId);
        timeMachine.updateTimeMachine(timeMachineFormDto);
        return "redirect:/timeMachine/all";

    }


    @GetMapping("/timeMachine/all")
    public String findAllTimeMachine( Model model) {

        //타임머신 현황 창 호출
        List<TimeMachine> timeMachineList = timeMachineService.findAllTimeMachine();
        model.addAttribute("timeMachineList", timeMachineList);

        return "timeMachine/timeMachineAllForm";
    }


}
