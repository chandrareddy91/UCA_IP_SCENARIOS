package com.hpe.uca.vp.IP_CEN_01.auto.automation;

import org.slf4j.LoggerFactory;

import com.hpe.uca.vp.IP_CEN_01.auto.core.MyAutomationDefault;
import com.hpe.uca.expert.vp.auto.interfaces.AutomationInterface;


/**
 * The Class Automation_Switch.
 */
public final class Automation_Switch extends MyAutomationDefault implements AutomationInterface {

	/**
	 * Instantiates a new Automation_Switch.
	 */
	public Automation_Switch() {
		super();
		setLog(LoggerFactory.getLogger(Automation_Switch.class));
	}

}