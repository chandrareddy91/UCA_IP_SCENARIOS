package com.hpe.uca.vp.IP_CEN_01.pd.core;

import org.slf4j.LoggerFactory;
import com.hp.uca.expert.vp.pd.core.GeneralBehaviorDefault;

import com.hp.uca.expert.alarm.JDBCAlarmForwarder;
/**
 * The Class MyGeneralBehavior.
 * 
 * This behavior is empty and ready to define methods to customize the
 * GeneralBehaviorDefault
 */
public class MyGeneralBehavior extends GeneralBehaviorDefault {

	protected static final String DB_FORWARDER_BEAN = "dbForwarder";

	// --------------------------- constructor

	public MyGeneralBehavior() {
		super();
		setLog(LoggerFactory.getLogger(MyGeneralBehavior.class));
	}

	// --------------------------- implementation

	@Override
	public void whatToDoWhenProblemDetectionIsInitialized() throws Exception {
		super.whatToDoWhenProblemDetectionIsInitialized();

		JDBCAlarmForwarder al = (JDBCAlarmForwarder) getScenario().getVPApplicationContext().getBean(DB_FORWARDER_BEAN);
		al.getStoreDaoInterface().clearAll();
	}

}
