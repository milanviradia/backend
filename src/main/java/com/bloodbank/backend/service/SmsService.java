package com.bloodbank.backend.service;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	public static final String ACCOUNT= "AC572a0b8b224fcc72367caa475808fd37";
	public static final String AUTH = "5b0c5ebbd6da253921bdf1b6e285e0e8";
	public void sendSMS(String mobile_no,String address) {
		Twilio.init(ACCOUNT, AUTH);
		mobile_no = "+91"+mobile_no;
		Message message = Message.creator(
                new PhoneNumber(mobile_no),
                new PhoneNumber("+12077422945"),
                "**Raktdaan Mahadaan**\n You can save a life by reaching at below location as soon as possible.\nAddress:"+address).create();
        System.out.println(message.getSid());
	}
}
