package com.message.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.message.pojo.Account;
import com.message.pojo.Msg;
import com.message.pojo.SmsRequest;
import com.message.pojo.TextMessage;

@Path(value = "/sms")
public class SmsService {

	@POST
	@Path(value="/sendSmsService/{mobile}/{text}")
	public Response sendSms(@PathParam("mobile") String mobile, @PathParam("text") String text) {
		
		//Request smsgateway hub to deliver message to mobile number.
		StringBuilder url = new StringBuilder("https://www.smsgatewayhub.com/api/mt/SendSMS");
		SmsRequest smsReq = prepareRequest(mobile, text);
		
		Gson gson = new Gson();
		String smsReqJson = gson.toJson(smsReq);
		System.out.println("Reques in json format : " + smsReqJson);
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(smsReqJson,headers);
		try {
			ResponseEntity<String> result = rt.exchange(url.toString(), HttpMethod.POST, entity, String.class);
			
			String responseBody = result.getBody();
			System.out.println("Response from smsgatewayhub : " + result.getBody());
			TextMessage textMessage = gson.fromJson(responseBody, TextMessage.class);
			System.out.println("Message status : " + textMessage.getErrorMessage());
			
			/*
			 * int a = 10; if(a==10) throw new
			 * ArithmeticException("GateHub to send sms is down!! Please try after sometime!!"
			 * );
			 */
			
			return Response.status(200).entity(textMessage.getErrorMessage()).build();			
		} catch(Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
	}
	
	public SmsRequest prepareRequest(String mobile, String text) {
		SmsRequest smsReq = new SmsRequest();
		
		Account acc = new Account();
		acc.setUser("venugopal");
		acc.setPassword("606173");
		acc.setChannel("1");
		acc.setDCS("0");
		acc.setSenderId("SMSTST");
		
		List<Msg> msgList = new ArrayList<Msg>();
		Msg msg = new Msg();
		msg.setNumber(mobile);
		msg.setText(text);
		msgList.add(msg);
		
		smsReq.setAccount(acc);
		smsReq.setMessages(msgList);
		
		return smsReq;
	}
}
