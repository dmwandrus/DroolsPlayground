package com.sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.sample.dtos.App;
import com.sample.dtos.Message;
import com.sample.dtos.MessageTypes;

public class Runner {
	
	public static Random random = new Random();
	
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Starting up....");
		SessionWrapper wrapper = new SessionWrapper();
		wrapper.init();
		
		List<Object> objectsToAdd = new ArrayList<Object>();
		for(int i=0; i<20; i++)
		{
			objectsToAdd.add(createMessage(i));
		}
		
		List<MessageTypes> registeredTypes1 = new ArrayList<MessageTypes>();
		registeredTypes1.add(MessageTypes.blue);
		registeredTypes1.add(MessageTypes.red);
		registeredTypes1.add(MessageTypes.green);
		App app1 = new App();
		app1.setName("App1");
		app1.setMyRegisteredTypes(registeredTypes1);
		
		
		List<MessageTypes> registeredTypes2 = new ArrayList<MessageTypes>();
		registeredTypes2.add(MessageTypes.orange);
		registeredTypes2.add(MessageTypes.purple);
		App app2 = new App();
		app2.setName("App2");
		app2.setMyRegisteredTypes(registeredTypes2);
		
		
		List<MessageTypes> registeredTypes3 = new ArrayList<MessageTypes>();
		registeredTypes3.add(MessageTypes.blue);
		registeredTypes3.add(MessageTypes.yellow);
		App app3 = new App();
		app3.setName("App3");
		app3.setMyRegisteredTypes(registeredTypes3);
		
		List<MessageTypes> registeredTypes4 = new ArrayList<MessageTypes>();
		registeredTypes4.add(MessageTypes.red);
		App app4 = new App();
		app4.setName("App4");
		app4.setMyRegisteredTypes(registeredTypes4);
		
		
		List<MessageTypes> registeredTypes5 = new ArrayList<MessageTypes>();
		registeredTypes5.add(MessageTypes.blue);
		registeredTypes5.add(MessageTypes.green);
		registeredTypes5.add(MessageTypes.orange);
		registeredTypes5.add(MessageTypes.red);
		registeredTypes5.add(MessageTypes.purple);
		registeredTypes5.add(MessageTypes.yellow);
		App app5 = new App();
		app5.setName("App5");
		app5.setMyRegisteredTypes(registeredTypes5);
		
		objectsToAdd.add(app1);
		objectsToAdd.add(app2);
		objectsToAdd.add(app3);
		objectsToAdd.add(app5);
		
		wrapper.addAndExecute(objectsToAdd);
		
		
		List<Object> objectsToAdd2 = new ArrayList<Object>();
		for(int i=20; i<40; i++)
		{
			objectsToAdd2.add(createMessage(i));
		}
		wrapper.addAndExecute(objectsToAdd2);
		
		for(int i=40; i<60; i++)
		{
			wrapper.addAndExecute(createMessage(i));
		}
		
		wrapper.destroy();
		System.out.println("Shutting down....");
	}
	
	
	public static Message createMessage(int i)
	{
		Message message = new Message();
		message.setId(i);
		message.setDate(new Date());
		message.setMessageType(getRandomType());
		message.setContent("Message Number "+i);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	private static MessageTypes getRandomType()
	{
		int maxSize = MessageTypes.values().length;
		int rnum = random.nextInt(maxSize-1);
		return MessageTypes.values()[rnum];
	}
	
}
