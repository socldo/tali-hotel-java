//package com.vn.tali.hotel.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//@Service
//public class TwilioService {
//
//    @Value("${twilio.accountSid}")
//    private String accountSid;
//
//    @Value("${twilio.authToken}")
//    private String authToken;
//
//    @Value("${twilio.phoneNumber}")
//    private String twilioPhoneNumber;
//
//    public void sendSms(String toPhoneNumber, String message) {
//        Twilio.init(accountSid, authToken);
//
//        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(twilioPhoneNumber), message).create();
//    }
//}
