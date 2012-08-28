package com.sample.dtos;

public class MessageRegistration {
	App app;
	MessageTypes type;
	
	public MessageRegistration(App app, MessageTypes type)
	{
		this.app = app;
		this.type = type;
	}
	
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public MessageTypes getType() {
		return type;
	}
	public void setType(MessageTypes type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MessageRegistration other = (MessageRegistration) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
