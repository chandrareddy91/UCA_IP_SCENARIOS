package com.hp.uca.vp.IP_CEN_01.pd.lifecycle;

import java.util.Calendar;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.uca.expert.alarm.Alarm;
import com.hp.uca.expert.alarm.AlarmCommon;
import com.hp.uca.expert.scenario.Scenario;
import com.hp.uca.expert.vp.pd.im.lifecycle.InferenceMachineLifeCycleExtended;
import com.hpe.uca.vp.IP_CEN_01.pd.problem.*;

public class CustomInferenceMachineLifeCycleExtended extends InferenceMachineLifeCycleExtended {
	
	static final String CLEARANCE_TIMESTAMP_ATTRIBUTE="clearanceTimeStamp";
	static final String TERMINATION_TIMESTAMP_ATTRIBUTE="terminationTimeStamp";
	static final String ACKNOWLEDGE_TIMESTAMP_ATTRIBUTE="acknowledgementTimeStamp";
	static final String HANDLE_TIMESTAMP_ATTRIBUTE="handleTimeStamp";
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomInferenceMachineLifeCycleExtended.class);
	
	public CustomInferenceMachineLifeCycleExtended(Scenario scenario) {
		super(scenario);
	}
	
	@Override 
	public AlarmCommon onAlarmCreationProcess(Alarm alarm) 
	{ 		
		//logger.debug("onAlarmCreationProcess:Inside :"+alarm.toFormattedString());
		//logger.debug("onAlarmCreationProcess:Inside :"+alarm.getIdentifier());
		Alarm retAlarm = (Alarm) super.onAlarmCreationProcess(alarm);
		
		Calendar alarmTime = Calendar.getInstance();
		alarmTime.setTimeInMillis(retAlarm.getTimeInMilliseconds());

		Calendar deadLine = Calendar.getInstance();
		//deadLine.add(Calendar.HOUR, -2); // consider only 2 hrs history
		
		Set<String> passingTags = null;
		String problem = "";
		LOG.trace("onAlarmCreationProcess alarm ID " +retAlarm.getIdentifier()+ " PassingFiltersTags "+
				retAlarm.getPassingFiltersTags().toString());
		if(retAlarm.getPassingFiltersTags().containsKey(Problem_Interface_Down.class.getSimpleName())){
			problem = Problem_Interface_Down.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Link_Down.class.getSimpleName())){
			problem = Problem_Link_Down.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Protocol_Down.class.getSimpleName())){
			problem = Problem_Protocol_Down.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Infra_Issue.class.getSimpleName())){
			problem = Problem_Infra_Issue.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Device_Resource_Alarm.class.getSimpleName())){
			problem = Problem_Device_Resource_Alarm.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_NNM_Issue.class.getSimpleName())){
			problem = Problem_NNM_Issue.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Hardware_Issue.class.getSimpleName())){
			problem = Problem_Hardware_Issue.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Node_Down.class.getSimpleName())){
			problem = Problem_Node_Down.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Node_Isolation.class.getSimpleName())){
			problem = Problem_Node_Isolation.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Pop_Down.class.getSimpleName())){
		    problem = Problem_Pop_Down.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Pop_Isolation.class.getSimpleName())){
			problem = Problem_Pop_Isolation.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}else if(retAlarm.getPassingFiltersTags().containsKey(Problem_Reachability.class.getSimpleName())){
			problem = Problem_Reachability.class.getSimpleName();
			passingTags=retAlarm.getPassingFiltersTags().get(problem);
		}
		
		if(passingTags!=null) {
			LOG.debug("onAlarmCreationProcess alarm ID " +retAlarm.getIdentifier()+ " problem : "+problem+
					"\nPassingFiltersTags: "+retAlarm.getPassingFiltersTags().get(problem).toString()
					+ "\ngetPassingFiltersParams: "+retAlarm.getPassingFiltersParams().get(problem).toString());
			
			String strRejectTime = alarm.getPassingFiltersParams().get(problem).get("Alarm.RejectTime");
			int rejectTime ;
			if(strRejectTime!=null) {
				if(strRejectTime.contains("h")) {
				rejectTime = Integer.parseInt(strRejectTime.replaceAll("h", ""));
				LOG.debug("onAlarmCreationProcess alarm ID " +retAlarm.getIdentifier()+ " Reject Time :"+strRejectTime);
				
				deadLine.add(Calendar.HOUR, -1*rejectTime); 
				
				}else if(strRejectTime.contains("m")) {
					rejectTime = Integer.parseInt(strRejectTime.replaceAll("m", ""));
					LOG.debug("onAlarmCreationProcess alarm ID " +retAlarm.getIdentifier()+ " Reject Time :"+strRejectTime);
					
					deadLine.add(Calendar.MINUTE, -1*rejectTime); 
					
				}
				
				LOG.debug("onAlarmCreationProcess: deadline :"+deadLine.getTime()+ ". Alarm time : "+alarmTime.getTime());
				
				if (alarmTime.before(deadLine) && !(alarm.getCustomFieldValue("parents")!= null || 
													alarm.getParents()!= null || 
													alarm.getChildren()!= null)
													
				)
			    {
			    	LOG.info("onAlarmCreationProcess:Alarm is un-processed and older than "+strRejectTime+", id:"+alarm.getIdentifier() + " Discarding!!!");
			    	return null;
			    }else{
			    	LOG.info("onAlarmCreationProcess: Considering Alarm :"+alarm.getIdentifier() + "...");
			    }
			}else {
				LOG.info("onAlarmCreationProcess: Reject Time is not configured. Considering Alarm :"+alarm.getIdentifier() + "...");
			}
		}else {
			
			LOG.info("onAlarmCreationProcess: Invalid alarm for this scenario id:"+alarm.getIdentifier() + " Discarding!!!");
	    	return null;
		}
	
		return retAlarm;
	}
	
	/*@Override
	public boolean onUpdateSpecificFieldsFromStateChange(AlarmStateChange alarmStateChange,
			Event alarmInWorkingMemory) {

		String clearanceTimeStamp = alarmStateChange.getCustomFieldValue(CLEARANCE_TIMESTAMP_ATTRIBUTE);
		String terminationTimeStamp = alarmStateChange.getCustomFieldValue(TERMINATION_TIMESTAMP_ATTRIBUTE);
		String acknowledgeTimeStamp = alarmStateChange.getCustomFieldValue(ACKNOWLEDGE_TIMESTAMP_ATTRIBUTE);
		String handleTimeStamp = alarmStateChange.getCustomFieldValue(HANDLE_TIMESTAMP_ATTRIBUTE);
		
		LOG.debug("onUpdateSpecificFieldsFromStateChange : clearanceTimeStamp = "+clearanceTimeStamp+" , terminationTimeStamp = "+terminationTimeStamp+
        		" , acknowledgeTimeStamp = "+acknowledgeTimeStamp);
		
		if (clearanceTimeStamp != null && alarmInWorkingMemory instanceof Alarm) {
			if(LOG.isDebugEnabled()) {
				LOG.debug("Setting clearanceTimeStamp in the alarm " + alarmInWorkingMemory.getIdentifier());
			}
				((Alarm) alarmInWorkingMemory).setCustomFieldValue(CLEARANCE_TIMESTAMP_ATTRIBUTE, clearanceTimeStamp);
		}
		
		if (terminationTimeStamp != null && alarmInWorkingMemory instanceof Alarm) {
			if(LOG.isDebugEnabled()) {
				LOG.debug("Setting termiantionTimeStamp in the alarm " + alarmInWorkingMemory.getIdentifier());
			}				
				((Alarm) alarmInWorkingMemory).setCustomFieldValue(TERMINATION_TIMESTAMP_ATTRIBUTE, terminationTimeStamp);
		}
		
		if (acknowledgeTimeStamp != null && alarmInWorkingMemory instanceof Alarm) {
			if(LOG.isDebugEnabled()) {
				LOG.debug("Setting acknowledgeTimeStamp in the alarm " + alarmInWorkingMemory.getIdentifier());
			}
				((Alarm) alarmInWorkingMemory).setCustomFieldValue(ACKNOWLEDGE_TIMESTAMP_ATTRIBUTE, acknowledgeTimeStamp);
		}
		
		if (handleTimeStamp != null && alarmInWorkingMemory instanceof Alarm) {
			if(LOG.isDebugEnabled()) {
				LOG.debug("Setting acknowledgeTimeStamp in the alarm " + alarmInWorkingMemory.getIdentifier());
			}
				((Alarm) alarmInWorkingMemory).setCustomFieldValue(HANDLE_TIMESTAMP_ATTRIBUTE, handleTimeStamp);
		}
		
		return false;

	}*/

}