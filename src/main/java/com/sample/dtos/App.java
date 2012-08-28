package com.sample.dtos;

import java.util.ArrayList;
import java.util.List;

public class App {
	String name;
	List<MessageTypes> myRegisteredTypes;
	List<Message> myReceivedMessages = new ArrayList<Message>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MessageTypes> getMyRegisteredTypes() {
		return myRegisteredTypes;
	}
	public void setMyRegisteredTypes(List<MessageTypes> myRegisteredTypes) {
		this.myRegisteredTypes = myRegisteredTypes;
	}
	
	public void addMessage(Message m)
	{
		myReceivedMessages.add(m);
	}
	
	public List<Message> getMyReceivedMessages() {
		return myReceivedMessages;
	}
	public void setMyReceivedMessages(List<Message> myReceivedMessages) {
		this.myReceivedMessages = myReceivedMessages;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((myRegisteredTypes == null) ? 0 : myRegisteredTypes
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		if (myRegisteredTypes == null) {
			if (other.myRegisteredTypes != null)
				return false;
		} else if (!myRegisteredTypes.equals(other.myRegisteredTypes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("App: name = ");
		sb.append(name);
		sb.append(", Registered Types = ").append(myRegisteredTypes);
		sb.append(", number received = ").append(myReceivedMessages.size());
		return sb.toString();
	}
	
	
}
