package com.bloodbank.backend.service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.util.Arrays;

public class SmsService {
	public static final String ACCOUNT_SID = "AC4a0300473f5e44e18196db05f3a4f72d";
	public static final String AUTH_TOKEN = "73b3adffcda2bb808cb2720f83b23833";
	public void sendSMS() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+918200100665"),
                new PhoneNumber("+12029336716"),
                "This is the ship that made the Kessel Run in fourteen parsecs?")
            .setMediaUrl(
                Arrays.asList(URI.create("https://c1.staticflickr.com/3/2899/14341091933_1e92e62d12_b.jpg")))
            .create();
        System.out.println(message.getSid());
	}
}
