package com.bloodbank.backend.service;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	public void sendSMS(String mobile_no,String address) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		mobile_no = "+91"+mobile_no;
		Message message = Message.creator(
                new PhoneNumber(mobile_no),
                new PhoneNumber("+12029336716"),
                "**Raktdaan Mahadaan**\n You can save a life by reaching at below location as soon as possible.\nAddress:"+address).create();
        System.out.println(message.getSid());
	}
}
