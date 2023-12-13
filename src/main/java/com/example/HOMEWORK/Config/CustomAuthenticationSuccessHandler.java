package com.example.HOMEWORK.Config;

import com.example.HOMEWORK.EmployeeRole;
import com.example.HOMEWORK.Service.CustomerService;
import com.example.HOMEWORK.Service.EmployeeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //loadUserByUsername 메서드 가 리턴한 권한을 비교하여  리다이렉트한다.


        log.info(request.getParameter("username"));
        log.info(request.getParameter("password"));

        log.info(request.getParameter("artifactId"));

        //request로 로그인 폼에 담았던 input들을 모두 받을 수 있다.

        //꼼수쓴거 ... 로그인할 떄  로그인 폼에 값을 숨겨서 넣은후  request로 처리하도록
        if ( request.getParameter("artifactId")!=null) {

            String username=request.getParameter("username");

            String artifactId=request.getParameter("artifactId");

            response.sendRedirect("/customer/"+username+ "/artifact/"+artifactId);
            return;
        }



        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // UserDetails 객체로부터 권한에 접근
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // 비교할 권한을 생성
        SimpleGrantedAuthority reserveAuthority=new SimpleGrantedAuthority(EmployeeRole.reserve.toString());
        SimpleGrantedAuthority travelAuthority=new SimpleGrantedAuthority(EmployeeRole.travel.toString());
        SimpleGrantedAuthority appraisalAuthority=new SimpleGrantedAuthority(EmployeeRole.appraisal.toString());
        SimpleGrantedAuthority salesAuthority=new SimpleGrantedAuthority(EmployeeRole.sales.toString());
        SimpleGrantedAuthority customerAuthority=new SimpleGrantedAuthority(EmployeeRole.customer.toString());


        String id = request.getParameter("username");


        // 권한 비교
        if (authorities.contains(reserveAuthority)) {
            // reserve권한이 있는 경우의 로직 수행
            // 예: 리다이렉트 또는 특정 처리
            response.sendRedirect("/employee/reserve/"+id+"/home");

            return;

        }
        else if (authorities.contains(travelAuthority)) {
            // travel 권한이 있는 경우의 로직 수행
            // 예: 리다이렉트 또는 특정 처리
            response.sendRedirect("/employee/travel/"+id+"/home");

            return;

        }
        else if (authorities.contains(appraisalAuthority)) {
            // appraisal 권한이 있는 경우의 로직 수행
            // 예: 리다이렉트 또는 특정 처리
            response.sendRedirect("/employee/appraisal/"+id+"/home");

            return;

        }
        else if (authorities.contains(salesAuthority)) {
            // sales 권한이 있는 경우의 로직 수행
            // 예: 리다이렉트 또는 특정 처리
            response.sendRedirect("/employee/sales/"+id+"/home");

            return;

        }
        else if (authorities.contains(customerAuthority)) {
            // customer 권한이 있는 경우의 로직 수행
            // 예: 리다이렉트 또는 특정 처리
            response.sendRedirect("/customer/"+id+"/home");

            return;

        }



        }

    }

