package com.hpe.uca.vp.IP_CEN_01.pd.custom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.uca.common.trace.LogHelper;
import com.hp.uca.expert.alarm.Alarm;
import com.hp.uca.expert.event.Event;
import com.hp.uca.expert.group.Group;
import com.hp.uca.expert.scenario.Scenario;
import com.hp.uca.expert.vp.common.actions.temip.TeMIPActionsFactory;
import com.hp.uca.expert.vp.common.interfaces.SupportedActions;
import com.hp.uca.expert.vp.pd.services.PD_Service_Action;
import com.hp.uca.expert.x733alarm.NetworkState;
import com.hp.uca.expert.x733alarm.OperatorState;
import com.hp.uca.mediation.action.client.Action;
import com.hp.uca.mediation.action.exception.UcaActionExecutionException;
import com.hp.uca.mediation.action.exception.UcaActionInitializationException;
import com.hp.uca.temip.mvp.aodirective.mapper.AODirective;
import com.hp.uca.temip.mvp.aodirective.mapper.AODirectiveKey;

public class Common_Util {
	
	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(Common_Util.class);
	public static final EventTimeComparator eventTimeComparator = new EventTimeComparator();

	public Common_Util() {
		
	} 
	
	/**
	 * Gets the passing filter parameter value from alarm/event.
	 * 
	 * @param event
	 * @param filterParam
	 * @param problem
	 * @return
	 */
	public static String getPassingFilterParamValue(Alarm alarm,String filterParam, String problem) {
		
		String result = null;
		if (alarm != null && problem != null && filterParam != null) {
			LOG.debug("getPassingFiltersParamValue(), {}",
					"alarm : " + alarm.getIdentifier() + ", problem : "
							+ problem + ", filterParam : " + filterParam);
			if (alarm.getPassingFiltersParams()!=null 
					&& alarm.getPassingFiltersParams().get(problem) != null
					&& alarm.getPassingFiltersParams().get(problem).get(filterParam) != null) {
				result = alarm.getPassingFiltersParams().get(problem).get(filterParam);
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("getPassingFilterParamFromAlarm: The " + filterParam
							+ " tag is not present in the passing filters of the alarm : {}",
							alarm.getIdentifier());
				}
			}
		}

		if (LOG.isTraceEnabled()) {
			LogHelper.exit(LOG, "getPassingFiltersParamValue()", "result : "
					+ result);
		}

		LOG.debug("getPassingFiltersParamValue() result : "+ result);
		return result;
	}
	
	/**
	 * Update alarm additional text in TeMIP.
	 * @param problem
	 * @param alarm
	 * @param newValue
	 */
	public static void updateAlarmAdditionalText(Scenario scenario, Alarm alarm, String newValue) {
		if(alarm!=null){
			Action action = null;
		
			if (LOG.isTraceEnabled()) {
				LogHelper.enter(LOG, "updateAlarmAdditionalText", alarm.getIdentifier() + " "
						+ newValue);
			}
			
			if (LOG.isDebugEnabled()) {
				LOG.debug("updateAlarmAdditionalText: Updating Additional Text for the Alarm {}", 
						alarm.getIdentifier());
			}
			String oldValue = alarm.getAdditionalText();
			LOG.debug("updateAlarmAdditionalText:\n Old Value : "+oldValue +"\nNewValue :"+ newValue );
			
			if(newValue!=null){
				
				if(!newValue.equals(oldValue)){
					try {
						if (scenario!=null && !scenario.isTestOnly()) {
							SupportedActions supportedActions = PD_Service_Action.retrieveSupportedActions(scenario, alarm);
							
							if(supportedActions!=null){
								if (LOG.isDebugEnabled()) {
									LOG.debug("updateAlarmAdditionalText: supportedActions action factory : {}", supportedActions.getName());
								}
								if(supportedActions instanceof TeMIPActionsFactory){
									//action = new Action(supportedActions.getActionReference());
									action = new Action("TeMIP_AOAction");
									action.addCommand(AODirectiveKey.DIRECTIVE_NAME, AODirective.SET);
									action.addCommand(AODirectiveKey.ENTITY_NAME, alarm.getIdentifier());
									action.addCommand(AODirectiveKey.ADDITIONAL_TEXT, newValue);
									scenario.addAction(action);
									action.executeAsync("NewText");
									alarm.setAdditionalText(newValue);
									/*if (action.getActionStatus().equals(ActionStatus.Completed)) {
										if (LOG.isDebugEnabled()) {
											LOG.debug("updateAlarmAdditionalText: Action update is success for : {}", newValue);
										}
										alarm.setAdditionalText(newValue);
									} else {
										LOG.info(
												"updateAlarmAdditionalText: Exception occured: Unable to update the alarm additional text in TeMIP for the alarm : {}",
												alarm.getIdentifier());
									}*/
								}
							}
						} else {
							if (LOG.isDebugEnabled()) {
								LOG.debug("updateAlarmAdditionalText: use testOnly mode, Action update is success for : {}", newValue);
							}
							alarm.setAdditionalText(newValue);
						}	
					} catch (Exception e) {
						LOG.error("updateAlarmAdditionalText: Exception while initializing Action: updateAlarmAdditionalText for {}",
								alarm.getIdentifier(), e);
					}
				}else{
					LOG.debug("updateAlarmAdditionalText: new value is same as current AdditionalText value, ignore Alarm {} update", alarm.getIdentifier());
				}
			}else{
				LOG.debug("updateAlarmAdditionalText: new value cannot be null");
			}
			if (LOG.isTraceEnabled()) {
				LogHelper.exit(LOG, "updateAlarmAdditionalText");
			}
		}
	}
	
	
	/**
	 * Returns alarm summary table for RCA additional Text.
	 * @param alarmList
	 * @return summary
	 */
	public static String calculateAlarmSummary(ArrayList<Alarm> alarmList) {
		StringBuilder summary = new StringBuilder();
		
		summary.append(
				"ALARM DETAILS:\n\n");
		summary.append(
				"| ALARM_ID | EVENT_TIME | NODE_NAME | AL_SPECIFIC_PROBLEM | AFFECTED_OBJECT |");
		summary.append(
				"\n-----------------------------------------------------------------------------\n");
		for (Alarm alarm : alarmList ) {
			String alarmId = getShortId(alarm.getIdentifier());
			String eventTime = getFormattedTime(alarm.getTimeInMilliseconds());
			String nodeName  = alarm.getCustomFieldValue("nodeName");
			String AlSpecific=alarm.getCustomFieldValue("alSpecificProblem");
			String AffectedObject=alarm.getCustomFieldValue("affectedObject");
			String CreationTimestamp=alarm.getCustomFieldValue("creationTimestamp");
			
			/*
			 * String affectedCi = alarm.getCustomFieldValue("affectedObject"); String
			 * alarmName = alarm.getCustomFieldValue("alSpecificProblem");
			 */
			
			summary.append("|"+alarmId + "|" + eventTime + "|" + nodeName +"|" + AlSpecific +"|"+ AffectedObject + "|\n");
		}
		return summary.toString();
	}
	
	/**
	 * Returns shortened string for alarm identifier in format oc_id
	 * @param identifier
	 * @return shortID
	 */
	public static String getShortId(String identifier) {
		String shortID = "" ;
		String ocName = "";
		String id = "";
		//sample : operation_context chandra_oc alarm_object 232
		ocName = identifier.substring(identifier.indexOf("operation_context")+17, identifier.indexOf("alarm_object")).trim();
		id = identifier.substring(identifier.indexOf("alarm_object")+13,identifier.length());
		
		LOG.debug("ocName : "+ocName+" id :"+id);
		if (ocName.indexOf('.') != -1) {
			shortID = ocName.substring(ocName.lastIndexOf(':') + 1)+ "_" + id;
			
		} else {
			shortID = ocName + "_" + id;
			
		}
		
		LOG.debug("Final getShortId: "+shortID);
		
		return shortID;
	}
	
	
	/**
	 * Returns formatted Date string for given time in milliseconds
	 * @param timeInMillis
	 * @return eventtime
	 */
	public static String getFormattedTime(long timeInMillis) {
		String eventtime = "";
		Date alarmDate = new Date(timeInMillis);
		SimpleDateFormat temipDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		eventtime = temipDF.format(alarmDate);
		return eventtime;
	}
	
	@SuppressWarnings("deprecation")
	public static String getFormattedTime(String customFieldValue) {
		String eventtime = "";
		Date alarmDate = new Date(customFieldValue);
		SimpleDateFormat temipDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		eventtime = temipDF.format(alarmDate);
		return eventtime;
	}
	public static class EventTimeComparator implements Comparator<Event>{
		@Override
		public int compare(Event event1, Event event2) {
			if(event1!=null && event2!=null){
				long l = event1.getTimeInMilliseconds() - event2.getTimeInMilliseconds();
				if(l<0){
					return 1;
				}else if(l==0){
					return 0;
				}else{
					return -1;
				}
			}
			return 0;
		}
	}
	
	public static List<Alarm> getActiveSubAlarmList(Group group){
		List<Alarm> alarms = new ArrayList<Alarm>();
		if(group.getAlarmList()!=null && !group.getAlarmList().isEmpty()){
			synchronized (group.getAlarmsMap()) {
				for(Alarm alarm : group.getAlarmList()) {
					if(alarm!=group.getProblemAlarm() && alarm.getOperatorState()!=OperatorState.TERMINATED && alarm.getNetworkState()!=NetworkState.CLEARED) {
						alarms.add(alarm);
					}
				}
			}
			//alarms.addAll(group.getAlarmList());
			Collections.sort(alarms, eventTimeComparator);
		}	
		//LOG.debug("getSubAlarmList :"+alarms.toString());
		return alarms;
	} 
	
	public static HashSet<String> getUniqueSubAlarmKeys(Group group){
		HashSet<String> uniqueKeys = new HashSet<String>();
		if(group.getAlarmList()!=null && !group.getAlarmList().isEmpty()){
			synchronized (group.getAlarmsMap()) {
				for(Alarm alarm : group.getAlarmList()) {
					if(alarm!=group.getProblemAlarm() && alarm.getOperatorState()!=OperatorState.TERMINATED && alarm.getNetworkState()!=NetworkState.CLEARED) {
						uniqueKeys.add(alarm.getCustomFieldValue("primaryKeyUca"));
					}
				}
			}
		}	
		//LOG.debug("getSubAlarmList :"+alarms.toString());
		return uniqueKeys;
	} 
	
	public static void updateAlarm(Scenario theScenario, Alarm alarm, String attribute, String value) throws Exception{
		
		Action action = new Action ("TeMIP_AOAction");
		action.addCommand(AODirectiveKey.DIRECTIVE_NAME, AODirective.SET); 
		action.addCommand(AODirectiveKey.ENTITY_NAME, alarm.getIdentifier()); 
		action.addCommand(attribute, value );
		theScenario.addAction(action);
		action.executeAsync(Action.NO_SYNCHRONIZATION_KEY);
		LOG.debug("[updateAlarm] SET Action Request sent for "+alarm.getIdentifier());
		LOG.trace("[updateAlarm] SET Action Request sent \n "+action.toFormattedString());
	
	}
	
	public static void clearAlarm(String alarmtoBeCleared, Scenario scenario){

		LOG.debug("Alarm to be cleared : "+ alarmtoBeCleared );
		
		Action action;
		try {
			action = new Action("TeMIP_AOAction");

			action.addCommand(AODirectiveKey.DIRECTIVE_NAME, AODirective.CLEARALARM);
			action.addCommand(AODirectiveKey.ENTITY_NAME, alarmtoBeCleared);
			action.addCommand(AODirectiveKey.USERID, "uca:parent-not-eligible");
			scenario.addAction(action);
			action.executeAsync(AODirectiveKey.ENTITY_NAME);;
		} catch (UcaActionInitializationException e) {
			// TODO Auto-generated catch block
			LOG.error("clearAlarm: Exception on Initializing clear action" +e);
		}catch ( UcaActionExecutionException e) {
			// TODO Auto-generated catch block
			LOG.error("clearAlarm: Exception in executing clear" +e);
		}
	}
	
	public static void terminateAlarm(String alarmtoBeTerminated, Scenario scenario){

		LOG.debug("terminateAlarm : "+ alarmtoBeTerminated );
		Action action;
		try {
			action = new Action("TeMIP_AOAction");
			
			action.addCommand(AODirectiveKey.DIRECTIVE_NAME, AODirective.TERMINATE);
			action.addCommand(AODirectiveKey.ENTITY_NAME, alarmtoBeTerminated);
			action.addCommand(AODirectiveKey.USERID, "uca:auto-terminate");
			scenario.addAction(action);
			action.executeAsync(AODirectiveKey.ENTITY_NAME);
		} catch (UcaActionInitializationException e) {
			// TODO Auto-generated catch block
			LOG.error("terminateAlarm: Exception on Initializing clear action" +e);
		}catch ( UcaActionExecutionException e) {
			// TODO Auto-generated catch block
			LOG.error("terminateAlarm: Exception in executing clear" +e);
		}
	}

	
	
	
}