package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.EmployeeRole;
import com.example.HOMEWORK.Entity.Customer;
import com.example.HOMEWORK.Entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;




@Slf4j
@RequiredArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {

    private final EmployeeService employeeService;
    private final CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 음 password를 반드시 엔코딩을 해야 동작하고 여기서 username은 그냥 id 이다.

        List<GrantedAuthority> authorities;

        try {

            Employee employee = employeeService.findEmployeeById(username);



            if (employee != null) {

                authorities = new ArrayList<>();


                switch (employeeService.findEmployeeById(username).getDepartment().getName()) {
                    case "reserve":
                        authorities.add(new SimpleGrantedAuthority(EmployeeRole.reserve.toString()));
                        break;
                    case "travel":
                        authorities.add(new SimpleGrantedAuthority(EmployeeRole.travel.toString()));
                        break;
                    case "sales":
                        authorities.add(new SimpleGrantedAuthority(EmployeeRole.sales.toString()));
                        break;
                    case "appraisal":
                        authorities.add(new SimpleGrantedAuthority(EmployeeRole.appraisal.toString()));
                        break;

                }
                log.info("{}", employee.getId());
                log.info("{}", employee.getPassword());
                return new User(employee.getId(), employee.getPassword(), authorities);
//                스프링 시큐리티는 loadUserByUsername 메서드에 의해 리턴된 User 객체의 비밀번호가 화면으로부터 입력 받은 비밀번호와 일치하는지를 검사하는 로직을 내부적으로 가지고 있다.


            }


        } catch (Exception e) {

        }

        try {
            Customer customer = customerService.findCustomerById(username);

            if (customer != null) {

                authorities = new ArrayList<>();

                //enum클래스 만들기 귀찮아서 그냥 customer 를 employeeRole에 넣음
                authorities.add(new SimpleGrantedAuthority(EmployeeRole.customer.toString()));


                log.info("{}", customer.getId());
                log.info("{}", customer.getPassword());
                return new User(customer.getId(), customer.getPassword(), authorities);
                // 스프링 시큐리티는 loadUserByUsername 메서드에 의해 리턴된 User 객체의 비밀번호가 화면으로부터 입력 받은 비밀번호와 일치하는지를 검사하는 로직을 내부적으로 가지고 있다.
            }


        } catch (Exception e) {

        }




        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);

    }








}
