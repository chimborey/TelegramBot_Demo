package com.example.TelegramBot_Application_Demo.exception;

import org.springframework.stereotype.Service;

@Service
public class CoculatorException {

    public int RuntimeExceptionHandler(){

        int res = 10 / 0;
        return res;
    }
}
