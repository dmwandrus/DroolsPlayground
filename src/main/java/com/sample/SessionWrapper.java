package com.sample;

import java.util.List;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;

import com.sample.handler.AltHelloWorldHandler;
import com.sample.handler.HelloWorldHandler;

public class SessionWrapper {

	KnowledgeRuntimeLogger logger;
	KnowledgeBase kbase;
	StatefulKnowledgeSession ksession;
	
	public SessionWrapper() {

	}

	public void init() throws Exception {
		// load up the knowledge base
		kbase = readKnowledgeBase();
		ksession = kbase.newStatefulKnowledgeSession();
		
		ksession.getWorkItemManager().registerWorkItemHandler("SampleTask", new HelloWorldHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("AltSampleTask", new AltHelloWorldHandler());
		
		logger = KnowledgeRuntimeLoggerFactory
				.newFileLogger(ksession, "test");
		System.out.println("Started Knowledge Session");
	}
	
	public void startProcess(String processName)
	{
		
		int num = ksession.getProcessInstances().size();
		System.out.println("Running "+num+" processes.");
		ProcessInstance pi = ksession.startProcess(processName);
//		while(ksession.getProcessInstance(pi.getId()) != null)
//		{
//			System.out.println("running process: "+pi.getProcessId());
//		}System.out.println("Done running");
		
		num = ksession.getProcessInstances().size();
		System.out.println("Running "+num+" processes.");
		
	}
	
	public void startProcess(String processName, Map<String, Object> params)
	{
		ksession.startProcess(processName, params);
	}
	
	public void addAndExecute(Object o)
	{
		ksession.insert(o);
		ksession.fireAllRules();
	}
	
	public void addAndExecute(List<Object> items)
	{
		for(Object item:items)
		{
			ksession.insert(item);
		}
		ksession.fireAllRules();
	}
	
	public void destroy()
	{
		ksession.dispose();
		logger.close();
		System.out.println("Stopped Knowledge Session");
	}

	private KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("appRules.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newClassPathResource("messageRules.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newClassPathResource("sample.bpmn"), ResourceType.BPMN2);
		kbuilder.add(ResourceFactory.newClassPathResource("sample2.bpmn"), ResourceType.BPMN2);

		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}
