package com.sample;

import java.util.List;

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
		logger = KnowledgeRuntimeLoggerFactory
				.newFileLogger(ksession, "test");
		System.out.println("Started Knowledge Session");
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
