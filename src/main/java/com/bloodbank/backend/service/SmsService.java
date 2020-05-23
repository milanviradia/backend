package com.bloodbank.backend.service;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Service
@ConfigurationProperties(prefix = "twillio.credentials")
@Configuration("employeeProperties")
public class SmsService {
	@Value("${twillio.credentials.sid}")
	private String ACCOUNT_SID;
	@Value("${twillio.credentials.token}")
	private String AUTH_TOKEN;
	
	public void sendSMS(String mobile_no,String address) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(mobile_no),
                new PhoneNumber("+12029336716"),
                "**Raktdaan Mahadaan**\n You can save a life by reaching at below location as soon as possible.\nAddress:"+address).create();
        System.out.println(message.getSid());
	}
}
