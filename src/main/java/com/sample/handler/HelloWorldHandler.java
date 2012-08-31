package com.sample.handler;

import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

public class HelloWorldHandler implements WorkItemHandler 
{

	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub
		System.out.println("Hello World Aborted!!!");
		
	}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub
		System.out.println("Hello World from the Handler");
		manager.completeWorkItem(workItem.getId(), null);
	}

}
