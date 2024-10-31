package com.users.account.controller;

import com.users.account.constant.Message_StatusCode;
import com.users.account.entity.Account;
import com.users.account.service.AccountService;
import com.users.account.validator.EmailValidator;
import com.users.account.validator.PhoneValidator;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService service;

    public String index() {
        return "ACCOUNT SERVICE STARTED...";
    }

    @GetMapping("/users")
    public Object users() {

        logger.info("---------------GET ACCOUNTS----------------");

        List<Account> list = service.accounts();
        if (list.isEmpty()) {
            return Message_StatusCode.status(404);
        }
        return list;
    }

    @GetMapping("/signin")
    public Object signin(@RequestParam("user") String user,
                         @RequestParam("pass") String pass) {

        logger.info("--------------GET SIGN IN ACCOUNT-------------------");
        logger.info("user {}", user);
        logger.info("pass {}", pass);

        if (!EmailValidator.isValidEmail(user) && !PhoneValidator.isValidIndianPhoneNumber(user)) {
            return Message_StatusCode.status(400);
        }
        Optional<Account> op = service.signin(user, pass);
        if (op.isPresent()) {
            Map<String,Object> data = new LinkedHashMap<>();
            data.put("data",service.updateStatusCode(1, op.get().getId()));
            data.put("message",Message_StatusCode.status(200));
            return data;
        }
        return Message_StatusCode.status(404);
    }

    @GetMapping("/already")
    public Object alreadyLogin(@RequestParam("email") String user) {

        logger.info("-------------GET ALREADY LOGIN----------------");
        logger.info("email {}", user);

        if (!EmailValidator.isValidEmail(user)) {
            return Message_StatusCode.status(400);
        }

        Optional<Account> op = service.signin(user);

        if (op.isPresent()) {
            if (op.get().getStatus() == 0) {
                return Message_StatusCode.status(440);
            } else if (op.get().getStatus() == 1) {
                return Message_StatusCode.status(200);
            }
        }
        return Message_StatusCode.status(404);
    }

    @PostMapping("/signup")
    public Object signup(@RequestBody Map<String, Object> payload) {

        String name = (String) payload.get("name");
        String lName = (String) payload.get("lname");
        String email = (String) payload.get("email");
        String address = (String) payload.get("address");
        String phone = (String) payload.get("phone");
        String country = (String) payload.get("country");
        String state = (String) payload.get("state");
        String logo = (String) payload.get("logo");
        int crNumber = (Integer) payload.get("cr_number");
        String password = (String) payload.get("password");

        logger.info("--------------CREATE SIGN UP ACCOUNT------------------");
        logger.info("name {}", name);
        logger.info("last_name {}", lName);
        logger.info("email {}", email);
        logger.info("address {}", address);
        logger.info("phone {}", phone);
        logger.info("country {}", country);
        logger.info("state {}", state);
        logger.info("logo {}", logo);
        logger.info("cr_number {}", crNumber);
        logger.info("password {}", password);


        if (!EmailValidator.isValidEmail(email) && !PhoneValidator.isValidIndianPhoneNumber(phone)) {
            return Message_StatusCode.status(400);
        }

        Optional<Account> op = service.signup(email, phone, crNumber);

        if (op.isPresent()) {
            return Message_StatusCode.status(208);
        }

        Map<String,Object> data = new LinkedHashMap<>();
        data.put("data",service.save(new Account(name, lName, email, address, phone, country, state, logo, crNumber, password)));
        data.put("message",Message_StatusCode.status(200));
        return data;
    }

    @GetMapping("/logout")
    public Object logout(@RequestParam("email") String user) {
        logger.info("------------GET LOGOUT--------------");
        logger.info("email {}",user);
        Optional<Account> op = service.signin(user);
        if (op.isPresent()) {
            if(op.get().getStatus() == 1) {
                return service.updateStatusCode(0, op.get().getId());
            }else {
                return Message_StatusCode.status(440);
            }
        }
        return Message_StatusCode.status(404);
    }
}