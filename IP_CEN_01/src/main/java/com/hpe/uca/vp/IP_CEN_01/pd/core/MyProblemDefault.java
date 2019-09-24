/**
 * 
 */
package com.hpe.uca.vp.IP_CEN_01.pd.core;

import org.slf4j.LoggerFactory;

import com.hp.uca.common.trace.LogHelper;
import com.hp.uca.expert.alarm.AlarmUpdater;
import com.hp.uca.expert.group.Group;
import com.hp.uca.expert.vp.pd.core.ProblemDefault;

/**
 * The Class MyProblemDefault extends ProblemDefault and overrides <li>
 * {@link #whatToDoWhenProblemAlarmIsNoMoreEligible(Group)}
 */
public class MyProblemDefault extends ProblemDefault {

	public MyProblemDefault() {
		super();
		setLog(LoggerFactory.getLogger(MyProblemDefault.class));
	}

	// --------------------------- default override

	@Override
	public void whatToDoWhenProblemAlarmIsNoMoreEligible(Group group) throws Exception {
		if (getLog().isTraceEnabled()) {
			LogHelper.enter(getLog(), "whatToDoWhenProblemAlarmIsNoMoreEligible()", group.getName());
		}

		getLog().info("Sending ProblemAlarm deletion to orchestration because it's no more eligible : "
				+ group.getProblemAlarm().getIdentifier());

		// XXX note that if alarm become eligible again, it's definitely deleted
		getScenario().applyOrchestration(AlarmUpdater.generateDeletionFromAlarm(group.getProblemAlarm()));

		super.whatToDoWhenProblemAlarmIsNoMoreEligible(group);

		if (getLog().isTraceEnabled()) {
			LogHelper.exit(getLog(), "whatToDoWhenProblemAlarmIsNoMoreEligible()");
		}
	}

}
