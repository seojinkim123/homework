package com.example.HOMEWORK.Controller;

import com.example.HOMEWORK.Dto.EmployeeFormDto;
import com.example.HOMEWORK.Entity.Employee;
import com.example.HOMEWORK.Service.DepartmentService;
import com.example.HOMEWORK.Service.EmployeeService;
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
@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public String Work(String dept) {
       String Dept=null;
        switch (dept) {
            case"travel":
                Dept="여행관리";
                break;
            case"reserve":
                Dept="타임머신 예약 / 관리";
                break;
            case"sales":
                Dept= "판매관리";
                break;
            case"appraisal":
                Dept= "감정관리";
                break;

        }
        return Dept;
    }




    @GetMapping("employee/{dept}/{employeeId}/home")
    public String home(@PathVariable("dept")String dept ,@PathVariable("employeeId")String employeeId, Model model){
        // 직원 상세 홈페이지 호출
        model.addAttribute("dept", dept);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("work",Work(dept));

        return "employee/employeeMain";


    }







    @GetMapping("employee/{dept}/new" )
    public String newEmployee(Model model,@PathVariable("dept")String dept){
        // 신규직원 등록 폼
        model.addAttribute("employeeFormDto", new EmployeeFormDto());
        model.addAttribute("context","신규직원등록");
        model.addAttribute("dept", dept);
        model.addAttribute("work",Work(dept));
        return "employee/employeeForm";
    }

    @PostMapping("employee/{dept}/new")
    public String newEmployee(@Valid EmployeeFormDto employeeFormDto , BindingResult bindingResult,Model model,@PathVariable("dept")String dept){
        //신규 직원 등록 로직
        model.addAttribute("dept", dept);
        model.addAttribute("work",Work(dept));
        if (bindingResult.hasErrors()) {
            return "employee/employeeForm";
        }

        Employee employee = employeeFormDto.createEmployee();
        employee.setDepartment(departmentService.findByIdDepartment(dept));
        employeeService.saveEmployee(employee);
        return "redirect:/login";
    }


    @GetMapping("employee/{dept}/{employeeId}/update")
    public String updateEmployee(@PathVariable("employeeId")String employeeId, @PathVariable("dept")String dept, Model model){
        //직원 업데이트 폼 호출
        Employee updateEmployee=employeeService.findEmployeeById(employeeId);

        model.addAttribute("employeeFormDto", EmployeeFormDto.createEmployeeFormDto(updateEmployee));
        model.addAttribute("dept", dept);
        model.addAttribute("employeeId", employeeId);

        model.addAttribute("work",Work(dept));

        return "employee/employeeForm";
    }

    @PostMapping("employee/{dept}/{employeeId}/update")
    public String updateEmployee(@Valid EmployeeFormDto employeeFormDto , BindingResult bindingResult, @PathVariable("employeeId")String employeeId, @PathVariable("dept")String dept ,Model model){
        //직원 업데이트 로직
//        Employee updateEmployee = employeeService.findById(employeeFormDto.getId());
        model.addAttribute("dept", dept);
        model.addAttribute("work",Work(dept));
        model.addAttribute("employeeId", employeeId);
        if (bindingResult.hasErrors()) {
            return "employee/employeeForm";
        }



        Employee updateEmployee = employeeService.findEmployeeById(employeeId);

        updateEmployee.updateEmployee(employeeFormDto);
        log.info("info log = {}", updateEmployee.getName());

        //@Transaction 없으니까 변경감지를 못한다.
        //@Transactional이 있는 경우에는 해당 메소드가 트랜잭션으로 묶여있어 메소드가 끝나는 지점에 트랜잭션 커밋이 발생하게 되고 flush가 자동으로 작동하게 됩니다.

        return "redirect:/employee/{dept}/{employeeId}/all";
    }

    @GetMapping("employee/{dept}/{employeeId}/delete")
    public String  deleteEmployee(@PathVariable("employeeId")String employeeId,Model model,@PathVariable("dept")String dept){
        //직원 삭제 로직
        Employee forDeleteEmployee=employeeService.findEmployeeById(employeeId);

        employeeService.deleteEmployee(forDeleteEmployee);

        model.addAttribute("dept", dept);
        model.addAttribute("work",Work(dept));

        return "redirect:/employee/{dept}/{employeeId}/all";





    }

    @GetMapping("employee/{dept}/{employeeId}/all")
    public String findAllEmployee(Model model,@PathVariable("dept")String dept,@PathVariable("employeeId")String employeeId) {
        //직원 현황 창 호출
        List<Employee> employeeList =employeeService.findEmployees();

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("context", "직원현황");
        model.addAttribute("dept", dept);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("work",Work(dept));

        return "employee/employeeAllForm";
    }














}
