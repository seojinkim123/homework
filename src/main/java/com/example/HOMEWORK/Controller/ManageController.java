package com.example.HOMEWORK.Controller;

import com.example.HOMEWORK.Dto.ArtifactFormDto;
import com.example.HOMEWORK.Dto.ReservationFormDto;
import com.example.HOMEWORK.Entity.*;
import com.example.HOMEWORK.Service.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Controller
public class ManageController {

    private final TimeMachineService timeMachineService;
    private final ReservationService reservationService;
    private final ArtifactService artifactService;
    private final EmployeeService employeeService;
    private final ContinentService continentService;
    private final TravelService travelService;
    private final AppraisalService appraisalService;



    @GetMapping("employee/reserve/{employeeId}/manage")
    public String ManageReserve(Model model,@PathVariable("employeeId")String employeeId) {

        //타임머신 예약 관리 부서의 관리 창 호출
        model.addAttribute("dept", "reserve");
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("work","예약관리");

        List<Reservation> reservationList = reservationService.findAllReservation();
        model.addAttribute("reservationList", reservationList);

        return "manage/reserveManage";

    }

    @GetMapping("employee/reserve/{employeeId}/manage/{reservationId}")
    public String ManageReserveApprove(Model model,@PathVariable("employeeId")String employeeId,@PathVariable Long reservationId) {
        //타임머신 예약 관리 부서의 예약 로직
        Reservation reservation = reservationService.findByIdReservation(reservationId);
        reservation.setEmployee2(employeeService.findEmployeeById(employeeId));
        reservation.setStatus("승인됨");
        return "redirect:/employee/reserve/{employeeId}/manage";

    }

    @GetMapping("employee/reserve/{employeeId}/manage/{reservationId}/delete")
    public String ManageReserveDelete(Model model,@PathVariable("employeeId")String employeeId,@PathVariable Long reservationId) {
        //타임머신 예약 관리 부서의 예약 취소 로직
        reservationService.deleteReservation(reservationService.findByIdReservation(reservationId));
        return "redirect:/employee/reserve/{employeeId}/manage";

    }

    @GetMapping("employee/reserve/{employeeId}/manage/{reservationId}/reject")
    public String ManageReserveReject(Model model,@PathVariable("employeeId")String employeeId,@PathVariable Long reservationId) {
        //타임머신 예약 관리 부서의 예약 거절 로직
        Reservation reservation = reservationService.findByIdReservation(reservationId);
        reservation.setEmployee2(employeeService.findEmployeeById(employeeId));
        reservation.setStatus("거절됨");
        return "redirect:/employee/reserve/{employeeId}/manage";

    }




    @GetMapping("employee/travel/{employeeId}/manage")
    public String ManageTravel(Model model,@PathVariable("employeeId")String employeeId) {

        //타임머신 여행 부서의 관리 창 호출

        model.addAttribute("dept", "travel");
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("work","여행관리");

        List<Reservation> reservationList = reservationService.findAllReservation();
        List<TimeMachine> timeMachineList = timeMachineService.findAllTimeMachine();

        model.addAttribute("reservationList", reservationList);
        model.addAttribute("timeMachineList", timeMachineList);

        return "manage/travelManage";

    }

    @GetMapping("employee/travel/{employeeId}/manage/{timeMachineId}")
    public String ManageTravelTimMachine(@PathVariable("timeMachineId")String timeMachineId,@PathVariable("employeeId")String employeeId,Model model) {
        //타임머신 여행 부서의 예약 폼 호출
        TimeMachine timeMachine = timeMachineService.findByIdTimeMachine(timeMachineId);
        model.addAttribute("timeMachine", timeMachine);
        model.addAttribute("date",LocalDate.now());
        model.addAttribute("employeeId",employeeId);
        model.addAttribute("dept","travel");
        model.addAttribute("work","여행관리");

        return "ReservationForm";
    }

    @PostMapping("employee/travel/{employeeId}/manage/{timeMachineId}")
    public String ManageTravelTimMachine(@PathVariable("timeMachineId")String timeMachineId ,@PathVariable("employeeId")String employeeId,ReservationFormDto reservationFormDto) {
        // 타임머신 여행 부서의 예약 로직

        Reservation reservation=new Reservation();

        reservation.setPurpose(reservationFormDto.getPurpose());
        reservation.setStatus("신청");
        reservation.setContinent(continentService.findByIdcontinent(reservationFormDto.getDestination()));

        reservation.setTimeMachine(timeMachineService.findByIdTimeMachine(timeMachineId));

        reservation.setStartTime(reservationFormDto.getStartDate());
        reservation.setEndTime(null);

        reservation.setCreateTime(LocalDateTime.now());
        reservation.setDestinationTime(LocalDateTime.now().plusDays(reservationFormDto.getDuration()));

        reservation.setEmployee(employeeService.findEmployeeById(employeeId));
        reservation.setEmployee2(null);

        reservationService.saveReservation(reservation);

        return "redirect:/employee/travel/{employeeId}/manage";

    }




    @GetMapping("employee/travel/{employeeId}/manage/artifact/{id}/update")
    public String updateArtifact(Model model,@PathVariable("id")Long id,@PathVariable("employeeId")String employeeId) {

        // 타임머신 여행 부서의 유물 업데이트 폼 호출
        model.addAttribute("artifactFormDto",ArtifactFormDto.createArtifactDto(artifactService.findByIdArtifact(id)));
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("dept", "travel");
        return "artifact/artifactForm";
    }

    @PostMapping("employee/travel/{employeeId}/manage/artifact/{id}/update")
    public String updateArtifact(@Valid ArtifactFormDto artifactFormDto, BindingResult bindingResult, Model model ,@PathVariable("id")Long id,@PathVariable("employeeId")String employeeId) {
        // 타임머신 여행 부서의 유물 업데이트 로직
        Artifact artifact = artifactService.findByIdArtifact(id);
        artifact.updateArtifact(artifactFormDto);

        return "redirect:/employee/travel/{employeeId}/manage/artifact/all";

    }

    @GetMapping("employee/travel/{employeeId}/manage/artifact/{id}/delete")
    public String deleteArtifact(@PathVariable("id")Long id) {
        // 타임머신 여행 부서의 유물 삭제 로직
        Artifact artifact = artifactService.findByIdArtifact(id);
        artifactService.deleteArtifact(artifact);

        return "redirect:/employee/travel/{employeeId}/manage/artifact/all";
    }


    @GetMapping("employee/travel/{employeeId}/manage/artifact/all")
    public String allArtifact(Model model,@PathVariable("employeeId")String employeeId) {
        // 타임머신 여행 부서의 유물 현황 창 호출
        List<Artifact>artifactList =artifactService.findAllArtifact();
        model.addAttribute("artifactList",artifactList);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("dept", "travel");
        return "artifact/artifactAllForm";
    }


    @GetMapping("employee/travel/{employeeId}/manage/reserve")
    public String travelReserve(Model model,@PathVariable("employeeId")String employeeId) {
        // 타임머신 여행 부서의 예약현황 창 호출
        List<Reservation> reservationList = reservationService.findAllReservation();
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("dept", "travel");

        return "travelReserve";
    }
    @GetMapping("employee/travel/{employeeId}/manage/reserve/{reserveId}/start")
    public String travelStart(Model model,@PathVariable("employeeId")String employeeId,@PathVariable("reserveId")String reserveId) {
        // 타임머신 여행 부서의 여행 중 & 유불 수집 폼 호출
        model.addAttribute("artifactFormDto", new ArtifactFormDto());
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("reserveId", reserveId);
        model.addAttribute("dept", "travel");

        return "artifact/artifactForm";
    }


    @PostMapping("employee/travel/{employeeId}/manage/reserve/{reserveId}/start")
    public String saveArtifact(@Valid ArtifactFormDto artifactFormDto, BindingResult bindingResult, Model model,@PathVariable("employeeId")String employeeId,@PathVariable("reserveId") Long reserveId) {
        // 유물 저장 로직
        if (bindingResult.hasErrors()) {
            return "artifact/artifactForm";
        }
        Artifact artifact = artifactFormDto.createArtifact();
        artifact.setStatus("감정중");

        model.addAttribute("employeeId", employeeId);



        Travel travel =new Travel();
        travel.setArrivalTime(LocalDateTime.now());
        travel.setDepartureTime(LocalDateTime.now()); //수정바람
        travel.setReservation(reservationService.findByIdReservation(reserveId));

        travelService.saveTravel(travel);

        artifact.setTravel(travel);
        artifactService.saveArtifact(artifact);

        Reservation reservation = reservationService.findByIdReservation(reserveId);
        reservation.setStatus("도착완료");



        return "redirect:/employee/travel/{employeeId}/manage/reserve";
    }










//    @GetMapping("employee/sales/{employeeId}/manage")
//    public String ManageSales(Model model, @PathVariable("employeeId")String employeeId) {
//
//        model.addAttribute("employeeId", employeeId);
//        model.addAttribute("dept", "sales");
//        model.addAttribute("work","판매관리");
//
//        return "manage/salesManage";
//
//    }

    @GetMapping("employee/appraisal/{employeeId}/manage")
    public String ManageAppraisal(Model model,@PathVariable("employeeId")String employeeId) {
        //감정관리부서의 관리 창 호출
        List<Artifact> artifactList = artifactService.findAllArtifact();

        model.addAttribute("dept", "appraisal");
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("work","감정관리");
        model.addAttribute("artifactList", artifactList);

        return "manage/appraisalManage";

    }


    @GetMapping("employee/appraisal/{employeeId}/manage/{artifactId}")
    public String ManageAppraisalArtifact(Model model,@PathVariable("employeeId")String employeeId,@PathVariable Long artifactId) {
        //감정관리부서의 유물감정 폼 호출 & 상태 변경
        model.addAttribute("work","감정관리");
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("artifactId", artifactId);
        model.addAttribute("artifact", artifactService.findByIdArtifact(artifactId));

        model.addAttribute("dept","appraisal");

        Artifact artifact=artifactService.findByIdArtifact(artifactId);
        artifact.setStatus("판매중");


        Appraisal appraisal = new Appraisal();
        appraisal.setArtifact(artifact);

        appraisalService.saveAppraisal(appraisal);



        return "appraisalForm";
    }


    @GetMapping("employee/appraisal/{employeeId}/manage/{artifactId}/update")
    public String ManageAppraisalArtifactUpdate(Model model,@PathVariable("employeeId")String employeeId,@PathVariable Long artifactId) {
        // 감정부서의 유물 감정 정보 업데이트 창 호출
        model.addAttribute("work","감정관리");
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("artifactId", artifactId);
        model.addAttribute("dept","appraisal");
        model.addAttribute("artifact", artifactService.findByIdArtifact(artifactId));

        return "appraisalForm";

    }

    @PostMapping("employee/appraisal/{employeeId}/manage/{artifactId}")
    public String ManageAppraisalArtifact(Model model,@PathVariable("employeeId")String employeeId,@PathVariable Long artifactId,Long price) {
        // 감정부서의 가격감정  로직
        Artifact artifact = artifactService.findByIdArtifact(artifactId);
        artifact.setPrice(price);
        return "redirect:/employee/appraisal/{employeeId}/manage";
    }


    @PostMapping("employee/appraisal/{employeeId}/manage/{artifactId}/update")
    public String ManageAppraisalArtifactUpdate(Model model,@PathVariable("employeeId")String employeeId,@PathVariable Long artifactId,Long price) {
        // 감정부서의 가격감정 수정 로직
        Artifact artifact = artifactService.findByIdArtifact(artifactId);
        artifact.setPrice(price);
        return "redirect:/employee/appraisal/{employeeId}/manage";

    }




}
