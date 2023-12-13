package com.example.HOMEWORK.Controller;

import com.example.HOMEWORK.Dto.CustomerFormDto;
import com.example.HOMEWORK.Entity.Artifact;
import com.example.HOMEWORK.Entity.Customer;
import com.example.HOMEWORK.Entity.Transaction;
import com.example.HOMEWORK.Service.ArtifactService;
import com.example.HOMEWORK.Service.CustomerService;
import com.example.HOMEWORK.Service.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@Controller
@RequiredArgsConstructor
@Transactional
public class CustomerController {

    private final CustomerService customerService;
    private final ArtifactService artifactService;
    private final TransactionService transactionService;

    @GetMapping("/customer/{customerId}/home")
    public String home(@PathVariable("customerId")String custoemrId){
        //고객의 상세 홈페이지 호출

        return "customer/customerMain";


    }


    @GetMapping("customer/new")
    public String newCustomer(Model model){
        //고객 등록 폼 호출
        model.addAttribute("context","회원가입");
        model.addAttribute("customerFormDto", new CustomerFormDto());
        return "customer/customerForm";
    }






    @PostMapping("customer/new")
    public String newCustomer(@Valid CustomerFormDto customerFormDto, BindingResult bindingResult, Model model){
        //고객 등록 로직

        if (bindingResult.hasErrors()) {
            return "customer/customerForm";
        }

        customerService.saveCustomer(customerFormDto.createCustomer());
        return "redirect:/login";
    }

    // 밑에 2게 get , postMapping은  로그인이 안된 상태에서 로그인 후 구매버튼 클릭시   회원가입조차 안돼있을 경우 로그인 파라미터를 로그인창에 보내주기 위함이다.
    @GetMapping("customer/new/{artifactId}")
    public String newCustomer2(Model model,@PathVariable String artifactId){
        model.addAttribute("context","회원가입");
        model.addAttribute("customerFormDto", new CustomerFormDto());
        return "customer/customerForm";
    }

    @PostMapping("customer/new/{artifactId}")
    public String newCustomer2(@Valid CustomerFormDto customerFormDto, BindingResult bindingResult, Model model,String artifactId){

        if (bindingResult.hasErrors()) {
            return "customer/customerForm";
        }

        customerService.saveCustomer(customerFormDto.createCustomer());
        return "redirect:/login?artifactId={artifactId}";
    }










    @GetMapping("customer/{customerId}/delete")
    public String deleteCustomer(Model model, @PathVariable("customerId")String customerId) {
        //고객 삭제 로직
        Customer customer= customerService.findCustomerById(customerId);
        customerService.deleteCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("customer/{customerId}/update")
         //고객 업데이트 폼 호출
    public String updateCustomer(Model model, @PathVariable("customerId")String customerId) {
        model.addAttribute("context","정보수정");
        CustomerFormDto customerFormDto = CustomerFormDto.createCustomerFormDto(customerService.findCustomerById(customerId));
        model.addAttribute("customerFormDto", customerFormDto);
        return "customer/customerForm";
    }

    @PostMapping("customer/{customerId}/update")
    public String updateCustomer(@PathVariable("customerId")String customerId,CustomerFormDto customerFormDto) {

        Customer customer = customerService.findCustomerById(customerId);
        customer.updateCustomer(customerFormDto);

        return "redirect:/customer/{customerId}/home";
    }

    //원래 /artfact는 customer 와 상관없는 거지만 귀찬아서 여기서 처리한다.
    @GetMapping("artifact")
    public String artifactForCustomer(Model model) {
        // 유물 나열 페이지 리턴
        List<Artifact>artifactList=artifactService.findAllArtifact();
        model.addAttribute("artifactList", artifactList);

        return "artifact/artifactForMainAndCustomer";
    }

    @GetMapping("customer/{customerId}/artifact")
    public String artifactForCustomer(Model model,@PathVariable String customerId) {
        // 유물 나열 페이지 리턴
        List<Artifact>artifactList=artifactService.findAllArtifact();
        model.addAttribute("artifactList", artifactList);
        model.addAttribute("customerId", customerId);
        return "artifact/artifactForMainAndCustomer";
    }


    @GetMapping("artifact/{artifactId}")
    public String artifactSpecific(Model model, @PathVariable("artifactId")Long artifactId) {
        //유물 구체 정보 창 리턴 (로그인전)
        model.addAttribute("artifact", artifactService.findByIdArtifact(artifactId));
        model.addAttribute("artifactId", artifactId);

        return "artifact/artifactSpecific";
    }

    @GetMapping("customer/{customerId}/artifact/{artifactId}")
    public String artifactSpecific2(Model model,@PathVariable String customerId,@PathVariable Long artifactId) {List<Artifact>artifactList=artifactService.findAllArtifact();
        //유물 구체 정보 창 리턴(로그인후)
        model.addAttribute("artifact", artifactService.findByIdArtifact(artifactId));
        model.addAttribute("artifactId", artifactId);
        model.addAttribute("customerId", customerId);
        return "artifact/artifactSpecific";
    }

    @GetMapping("customer/{customerId}/artifact/{artifactId}/transaction/new")
    public String artifactTransaction(@PathVariable String customerId, @PathVariable Long artifactId) {
        //유물 구매 로직

        Artifact artifact = artifactService.findByIdArtifact(artifactId);
        Customer customer =customerService.findCustomerById(customerId);
        artifact.setCustomer(customer);
        artifact.setStatus("판매완료");

        Transaction transaction = new Transaction();
        transaction.setArtifact(artifact);
        transaction.setCustomer(customer);
        transaction.setPrice(artifact.getPrice());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setType("일반판매");


        transactionService.saveTransaction(transaction);

        return "redirect:/customer/{customerId}/artifact";
    }



}
