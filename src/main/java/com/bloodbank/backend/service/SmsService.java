package com.bloodbank.backend.service;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.util.Arrays;

@Service
public class SmsService {
	public static final String ACCOUNT_SID = "AC4a0300473f5e44e18196db05f3a4f72d";
	public static final String AUTH_TOKEN = "73b3adffcda2bb808cb2720f83b23833";
	public void sendSMS(String mobile_no,String address) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(mobile_no),
                new PhoneNumber("+12029336716"),
                "**Raktdaan Mahadaan**\n You can save a life by reaching at below location as soon as possible.\nAddress:"+address).create();
        System.out.println(message.getSid());
	}
}
