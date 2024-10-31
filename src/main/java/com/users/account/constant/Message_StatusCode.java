package com.users.account.constant;

import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class Message_StatusCode {
    public final static Map<String, Object> status(int status) {
        Map<String, Object> msg = new LinkedHashMap<>();

        switch (status) {
            case 200:
                msg.put("message", "Data Success");
                msg.put("status", status);
                break;
            case 404:
                msg.put("message", "Data Not Found");
                msg.put("status", status);
                break;
            case 208:
                msg.put("message", "Already Reported");
                msg.put("status", status);
                break;

            case 440:
                msg.put("message","Already Logged Out");
                msg.put("status",status);
                break;

            case 400:
                msg.put("message","Bad Request");
                msg.put("status",status);
                break;
        }
        return msg;
    }
}
