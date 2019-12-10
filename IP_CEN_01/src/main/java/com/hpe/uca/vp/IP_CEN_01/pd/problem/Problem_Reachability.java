/**
 * 
 */
package com.hpe.uca.vp.IP_CEN_01.pd.problem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.store.SleepingLockWrapper;
import org.slf4j.LoggerFactory;

import com.hp.uca.common.trace.LogHelper;
import com.hp.uca.expert.alarm.Alarm;
import com.hp.uca.expert.event.Event;
import com.hp.uca.expert.group.Group;
import com.hp.uca.expert.instancemapper.MapperUtils;
import com.hp.uca.expert.scenario.Scenario;
import com.hp.uca.expert.scenario.ScenarioThreadLocal;
import com.hp.uca.expert.topology.CypherQuery;
import com.hp.uca.expert.vp.pd.core.ProblemDefault;
import com.hp.uca.expert.vp.pd.interfaces.ProblemInterface;
import com.hp.uca.expert.x733alarm.ProblemState;
import com.hp.uca.mediation.action.client.Action;
import com.hpe.uca.vp.IP_CEN_01.pd.custom.Common_Util;

/**
 * @author reddydc
 *
 */
public class Problem_Reachability extends ProblemDefault implements ProblemInterface {

	public Problem_Reachability() {
		super();
		setLog(LoggerFactory.getLogger(Problem_Reachability.class));
	}

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Problem_Reachability.class);

	@Override
	public boolean isAllCriteriaForProblemAlarmCreation(Group group) throws Exception {
		// TODO Auto-generated method stub
		boolean ret = super.isAllCriteriaForProblemAlarmCreation(group);
		LOG.debug(" Returned value from isAllCriteriaForProblemAlarmCreation : Enter " + ret);
		
		if(ret)
		{
			Alarm Trigger = group.getTriggerAlarm();
			String reteriveGlobalClassName = MapperUtils.doMapping(Trigger,"reteriveGlobalClassName");
			String retrievePrimaryKeyUca = MapperUtils.doMapping(Trigger,"retrievePrimaryKeyUca");
			String caliculateMo = reteriveGlobalClassName+" \""+retrievePrimaryKeyUca+"\"";
			group.getVar().put("caliculateMo",caliculateMo);
		}
		/*
		 * if(ret) { group.getVar().put("Problem_Class",getClass().getSimpleName());
		 * //Calculate group variables for RCA Enrichment Map<String, Object> params =
		 * new HashMap<String, Object>(); List<Map<String, String>> result; Alarm
		 * Trigger = group.getTriggerAlarm();
		 * 
		 * String queryName = Common_Util.getPassingFilterParamValue(Trigger,
		 * "ProblemAlarm.Enrichment.Query", getClass().getSimpleName());
		 * if(queryName!=null && !queryName.isEmpty()){
		 * 
		 * String nodeInfoQuery = getScenario().getMappers().getCypherQuery(queryName);
		 * 
		 * String retrieveNodeFromPrimaryKeyUca = MapperUtils.doMapping(Trigger, group,
		 * "retrieveNodeFromPrimaryKeyUca"); LOG.
		 * debug("isAllCriteriaForProblemAlarmCreation : retrieveNodeFromPrimaryKeyUca: "
		 * +retrieveNodeFromPrimaryKeyUca);
		 * 
		 * params.put("neid", retrieveNodeFromPrimaryKeyUca.toLowerCase());
		 * 
		 * //params.put("nodeUniqueId",
		 * group.getName().split("<e>")[1].split("</e>")[0].trim());
		 * LOG.debug("isAllCriteriaForProblemAlarmCreation : nodeInfoQuery: "
		 * +nodeInfoQuery + " Params : "+params.toString());
		 * 
		 * 
		 * 
		 * result = CypherQuery.executeAndReturnResultAsStrings(nodeInfoQuery,params);
		 * 
		 * if (result != null && !result.isEmpty() && result.size()>=1){
		 * LOG.debug("isAllCriteriaForProblemAlarmCreation : nodeInfoQuery output "
		 * +result.toString());
		 * 
		 * //To get Node Type details String linkType =
		 * result.get(0).get("link.LinkRelation"); String[] linkSep =
		 * linkType.split("_"); String AnodeType = linkSep[0]; String ZnodeType =
		 * linkSep[1];
		 * 
		 * group.getVar().put("Link_Type_A",AnodeType);
		 * group.getVar().put("Link_Type_B",ZnodeType);
		 * group.getVar().put("Pop_Type",(result.get(0).get("link.PopRelation")));
		 * group.getVar().put("nodeEnrich","RECORDS FOUND");
		 * 
		 * }else { group.getVar().put("Link_Type_A","DataNa");
		 * group.getVar().put("Link_Type_B","DataNa");
		 * group.getVar().put("Pop_Type","DataNa");
		 * group.getVar().put("nodeEnrich","NO Result");
		 * 
		 * String delay = Common_Util.getPassingFilterParamValue(Trigger,
		 * "ProblemAlarm.Creation.Delay", getClass().getSimpleName()); int timeToSleep =
		 * Integer.parseInt(delay);
		 * LOG.debug("isAllCriteriaForProblemAlarmCreation: going to sleep:"+timeToSleep
		 * ); Thread.sleep(timeToSleep); LOG.
		 * debug("isAllCriteriaForProblemAlarmCreation: Sleep time completed: calling update function on  "
		 * +Trigger); Scenario theScenario = ScenarioThreadLocal.getScenario();
		 * Common_Util.updateAlarm(theScenario, Trigger, "Additional Text", "Test");
		 * LOG.debug("isAllCriteriaForProblemAlarmCreation :  Result not found "+result.
		 * toString()+" ret :"+ret); LOG.
		 * debug("isAllCriteriaForProblemAlarmCreation: PR will not be created - call to update usertext :"
		 * +Trigger.getIdentifier()+"ret : "+ret+"timeToSleep :"+timeToSleep);
		 * 
		 * ret=false;
		 * 
		 * } }else {
		 * 
		 * ret=false;
		 * LOG.debug("isAllCriteriaForProblemAlarmCreation : no cypher query found "+
		 * queryName+"ret : "+ret); group.getVar().put("nodeEnrich","NO QUERY");
		 * group.getVar().put("Link_Type_A","DataNa");
		 * group.getVar().put("Link_Type_B","DataNa");
		 * group.getVar().put("Pop_Type","DataNa");
		 * 
		 * }
		 * 
		 * LOG.debug("isAllCriteriaForProblemAlarmCreation: PR will be created for :"
		 * +Trigger.getIdentifier()+"ret : "+ret); }
		 */
		LOG.debug(" Returned value from isAllCriteriaForProblemAlarmCreation : Exit " + ret);
		return ret;
	}

	@Override
	public String calculateProblemAlarmAdditionalText(Group group) throws Exception {
		// TODO Auto-generated method stub
		// String additionalText = super.calculateProblemAlarmAdditionalText(group);
		String additionalText = calculateCustomAdditionalText(group);
		return additionalText;
	}

	public String calculateCustomAdditionalText(Group group) throws Exception {

		String additionalText = "";
		Alarm Trigger = group.getTriggerAlarm();
		Alarm problemAlarm = group.getProblemAlarm();
		String mapperText = "";
		String nodeInfo = "";
		String adtextBlockSeperator = "\n############################################\n";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, String>> result;

		if (problemAlarm == null) {
			String mapper = Common_Util.getPassingFilterParamValue(Trigger, "ProblemAlarm.AdditionalText.CustomMapper",
					getClass().getSimpleName());
			if (mapper != null && !mapper.isEmpty()) {
				mapperText = MapperUtils.doMapping(Trigger, group, mapper);
			}
			group.getVar().put("Rca_Header", mapperText);
			LOG.debug("calculateCustomAdditionalText : RCA_AdditionalText_Mapper value : " + mapperText);

			additionalText = mapperText;

			/*
			 * if(!(group.getVar().containsKey("nodeEnrich"))) {
			 * 
			 * 
			 * String queryName = Common_Util.getPassingFilterParamValue(Trigger,
			 * "ProblemAlarm.Enrichment.Query", getClass().getSimpleName());
			 * if(queryName!=null && !queryName.isEmpty()){
			 * 
			 * String nodeInfoQuery = getScenario().getMappers().getCypherQuery(queryName);
			 * 
			 * String reteriveNodeName = MapperUtils.doMapping(Trigger, group,
			 * "reteriveNodeName");
			 * LOG.debug("isAllCriteriaForProblemAlarmCreation : reteriveNodeName: "
			 * +reteriveNodeName);
			 * 
			 * String managedObject = MapperUtils.doMapping(Trigger, group,
			 * "calculateNodeManagedObjectFromNode");
			 * LOG.debug("isAllCriteriaForProblemAlarmCreation : managedObject: "
			 * +managedObject);
			 * 
			 * String[] splits = managedObject.split("\\|");
			 * LOG.debug("isAllCriteriaForProblemAlarmCreation : Splits : "+splits.length);
			 * 
			 * LOG.debug("isAllCriteriaForProblemAlarmCreation : Splits[0] : "+splits[0]
			 * +"s Splits[1] : "+splits[1]); String nodeId =
			 * splits[0].split("\"")[1]+"|"+splits[1];
			 * 
			 * //group.getVar().put("nodeName",nodeId);
			 * 
			 * params.put("neId", reteriveNodeName.toLowerCase());
			 * 
			 * //params.put("nodeUniqueId",
			 * group.getName().split("<e>")[1].split("</e>")[0].trim());
			 * LOG.debug("isAllCriteriaForProblemAlarmCreation : nodeInfoQuery: "
			 * +nodeInfoQuery + " Params : "+params.toString());
			 * 
			 * result = CypherQuery.executeAndReturnResultAsStrings(nodeInfoQuery,params);
			 * 
			 * if (result != null && !result.isEmpty() && result.size()>=1){
			 * LOG.debug("calculateCustomAdditionalText : nodeInfoQuery output "+result.
			 * toString()); //To get Node Type details String linkType =
			 * result.get(0).get("link.LinkRelation"); String[] linkSep =
			 * linkType.split("_"); String AnodeType = linkSep[0]; String ZnodeType =
			 * linkSep[1];
			 * 
			 * group.getVar().put("Link_Type_A",AnodeType);
			 * group.getVar().put("Link_Type_B",ZnodeType);
			 * group.getVar().put("Pop_Type",(result.get(0).get("link.PopRelation")));
			 * group.getVar().put("nodeEnrich","RECORDS FOUND");
			 * 
			 * }else {
			 * 
			 * LOG.debug("calculateCustomAdditionalText : invalid nodeInfoQuery output ");
			 * group.getVar().put("Link_Type_A","DataNa");
			 * group.getVar().put("Link_Type_B","DataNa");
			 * group.getVar().put("Pop_Type","DataNa");
			 * group.getVar().put("nodeEnrich","NO Result");
			 * 
			 * String delay = Common_Util.getPassingFilterParamValue(Trigger,
			 * "ProblemAlarm.Creation.Delay", getClass().getSimpleName()); int timeToSleep =
			 * Integer.parseInt(delay);
			 * LOG.debug("calculateCustomAdditionalText: going to sleep:"+timeToSleep);
			 * Thread.sleep(timeToSleep); LOG.
			 * debug("calculateCustomAdditionalText: Sleep time completed: calling update function on  "
			 * +Trigger); Scenario theScenario = ScenarioThreadLocal.getScenario();
			 * Common_Util.updateAlarm(theScenario, Trigger, "Additional Text", "Test");
			 * LOG.debug("calculateCustomAdditionalText :  Result not found "+result.
			 * toString()); LOG.
			 * debug("calculateCustomAdditionalText: PR will not be created - call to update usertext :"
			 * +Trigger.getIdentifier()+"ret : ");
			 * 
			 * } }
			 * 
			 * }
			 */
			String AendModel = Trigger.getCustomFieldValue("nodeModel");
			String AendNodeName = Trigger.getCustomFieldValue("nodeName");
			String AendNodeType = Trigger.getCustomFieldValue("nodeType");
			/*
			 * String AlSpecific=Trigger.getCustomFieldValue("alSpecificProblem"); String
			 * AffectedObject=Trigger.getCustomFieldValue("affectedObject");
			 */

			nodeInfo = "<Start>" + "\nModel=" + AendModel + "\nNodeName=" + AendNodeName + "\nNodeType=" + AendNodeType
					+
					/*
					 * "\nAlSpecificProblem="+AlSpecific+ "\nalSpecificProblem="+AffectedObject+
					 */
					/*
					 * "\nCircle="+AendCircle+ "\nClass="+AendClass+ "\nNetwork=SNOC_TX"+
					 * "\nAffected_Object="+AendAffectedObject+ "\nSMSecAssign="+AendSecAssign+
					 * "\n"+AendAddInfo+
					 */
					"\n<End>";

			group.getVar().put("nodeInfo", nodeInfo);
			additionalText = additionalText + adtextBlockSeperator + nodeInfo + adtextBlockSeperator;

		} else {

			String prText = problemAlarm.getAdditionalText();
			additionalText = prText;
		}

		ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
		Alarm firstAlarm = group.getTriggerAlarm();
		alarmList.add(firstAlarm);
		List<Alarm> activegroupList = Common_Util.getActiveSubAlarmList(group);

		if (!activegroupList.isEmpty()) {
			Alarm lastAlarm = activegroupList.get(0);

			if (!lastAlarm.equals(firstAlarm)) {
				alarmList.add(lastAlarm);
			} else if (activegroupList.size() >= 2) {
				alarmList.add(activegroupList.get(1));
			}
		}

		LOG.debug(" alarmList from getActiveSubAlarmList  : " + alarmList);

		String alarmSummary = Common_Util.calculateAlarmSummary(alarmList);

		if (additionalText.contains("ALARM DETAILS")) {
			additionalText = additionalText.replaceAll("(?s)ALARM DETAILS.*", alarmSummary);
		} else {
			additionalText = additionalText + alarmSummary;
			// additionalText = additionalText + adtextBlockSeperator + alarmSummary;
		}

		LOG.debug("calculateCustomAdditionalText : " + additionalText);
		return additionalText;
	}

	@Override
	public void whatToDoWhenSubAlarmIsAttachedToGroup(Alarm alarm, Group group) throws Exception {
		// TODO Auto-generated method stub
		super.whatToDoWhenSubAlarmIsAttachedToGroup(alarm, group);
		LOG.debug("whatToDoWhenSubAlarmIsAttachedToGroup : " + alarm.getIdentifier());
		Alarm problemAlarm = group.getProblemAlarm();
		if (problemAlarm != null && alarm != problemAlarm) {
			String additonalText = calculateCustomAdditionalText(group);
			// LOG.trace("whatToDoWhenSubAlarmIsAttachedToGroup : recalculated text
			// :"+additonalText);
			Common_Util.updateAlarmAdditionalText(getScenario(), problemAlarm, additonalText);
		}
	}
	
	/*
	 * @Override public void whatToDoWhenProblemAlarmIsNoMoreEligible(Group group)
	 * throws Exception { if (getLog().isTraceEnabled()) { LogHelper.enter(getLog(),
	 * "whatToDoWhenProblemAlarmIsNoMoreEligible()", group.getName()); }
	 * 
	 * getLog().
	 * info("Sending ProblemAlarm deletion to orchestration because it's no more eligible : "
	 * + group.getProblemAlarm().getIdentifier());
	 * 
	 * Alarm PA = group.getProblemAlarm();
	 * 
	 * 
	 * 
	 * if ((null != PA) && (ProblemState.HANDLED != PA.getProblemState())) {
	 * 
	 * String strCreationTime = PA.getCustomFieldValue("creationTimestamp"); String
	 * strClearanceTime = PA.getCustomFieldValue("clearanceTimeStamp"); String
	 * strTerminationTime = PA.getCustomFieldValue("terminationTimeStamp");
	 * LOG.debug("Alarms in Group Count :"+group.getNbAlarmsSinceCreation());
	 * LOG.debug("whatToDoWhenProblemAlarmIsNoMoreEligible : creationTimestamp = "
	 * +strCreationTime+" , clearanceTimeStamp = "
	 * +strClearanceTime+" , terminationTimeStamp = "+strTerminationTime);
	 * 
	 * if(strCreationTime!=null && !strCreationTime.isEmpty() &&
	 * strClearanceTime!=null && !strClearanceTime.isEmpty() &&
	 * strTerminationTime!=null && !strTerminationTime.isEmpty()) { Date
	 * CreationTime=new Date(); Date ClearanceTime=new Date(); Date
	 * TerminationTime=new Date();
	 * 
	 * //2019-04-02T07:47:08.730Z SimpleDateFormat temipDF = new
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	 * 
	 * 
	 * CreationTime = temipDF.parse(strCreationTime); ClearanceTime =
	 * temipDF.parse(strClearanceTime); TerminationTime =
	 * temipDF.parse(strTerminationTime); //long diffinMills =
	 * (ClearanceTime.getTime() - CreationTime.getTime()); long diffinMills =
	 * (TerminationTime.getTime() - CreationTime.getTime());
	 * LOG.debug("After Changing the format  : creationTimestamp = "
	 * +CreationTime+" , clearanceTimeStamp = "
	 * +ClearanceTime+" , terminationTimeStamp = "+TerminationTime); LOG.
	 * debug("whatToDoWhenProblemAlarmIsNoMoreEligible : Difference between creation and clearance time : "
	 * + diffinMills);
	 * 
	 * Alarm Trigger = group.getTriggerAlarm();
	 * 
	 * long mapperTime = 0L; String strfluctuationWindow =
	 * Common_Util.getPassingFilterParamValue(Trigger,
	 * "ProblemAlarm.FluctuationWindow", getClass().getSimpleName()); LOG.
	 * debug("whatToDoWhenProblemAlarmIsNoMoreEligible : ProblemAlarm.FluctuationWindow : "
	 * + strfluctuationWindow); if(strfluctuationWindow!=null &&
	 * !strfluctuationWindow.isEmpty()){ if(strfluctuationWindow.contains("m")) {
	 * strfluctuationWindow = strfluctuationWindow.replaceAll("m", "");
	 * LOG.debug("strfluctuationWindow if m :"+strfluctuationWindow); mapperTime =
	 * Integer.parseInt(strfluctuationWindow)*60*1000; }else
	 * if(strfluctuationWindow.contains("s")) { strfluctuationWindow =
	 * strfluctuationWindow.replaceAll("s", "");
	 * System.out.println(strfluctuationWindow); mapperTime =
	 * Integer.parseInt(strfluctuationWindow)*1000; } }
	 * 
	 * LOG.debug("whatToDoWhenProblemAlarmIsNoMoreEligible : Fluctuation window : "+
	 * mapperTime);
	 * 
	 * if(diffinMills<=mapperTime) { LOG.
	 * debug("whatToDoWhenProblemAlarmIsNoMoreEligible : Fluctuation detected! RCA text will be updated for "
	 * + PA.getIdentifier()); String at = PA.getAdditionalText(); at =
	 * at.replaceFirst("RCA\\s*:", "RCA FLUCTUATION :"); PA.setAdditionalText(at);
	 * 
	 * Action a = new Action("TeMIP_AOAction");
	 * 
	 * a.addCommand("directiveName", "SET"); a.addCommand("entityName",
	 * PA.getIdentifier()); a.addCommand("additionalText", PA.getAdditionalText());
	 * 
	 * getScenario().addAction(a);
	 * 
	 * a.executeAsync("Fluctuation"); } } } // XXX note that if alarm become
	 * eligible again, it's definitely deleted
	 * //getScenario().applyOrchestration(AlarmUpdater.generateDeletionFromAlarm(
	 * group.getProblemAlarm()));
	 * 
	 * super.whatToDoWhenProblemAlarmIsNoMoreEligible(group);
	 * 
	 * if (getLog().isTraceEnabled()) { LogHelper.exit(getLog(),
	 * "whatToDoWhenProblemAlarmIsNoMoreEligible()"); } }
	 */

	/*
	 * @Override public void whatToDoWhenSubAlarmIsCleared(Alarm alarm, Group group)
	 * throws Exception { // TODO Auto-generated method stub
	 * super.whatToDoWhenSubAlarmIsCleared(alarm, group);
	 * LOG.debug("whatToDoWhenSubAlarmIsCleared : "+alarm.getIdentifier()); Alarm
	 * problemAlarm = group.getProblemAlarm(); if(problemAlarm!=null) { String
	 * additonalText = calculateCustomAdditionalText(group);
	 * //LOG.trace("whatToDoWhenSubAlarmIsCleared : recalculated text :"
	 * +additonalText); Common_Util.updateAlarmAdditionalText(getScenario(),
	 * problemAlarm, additonalText); }
	 * 
	 * }
	 * 
	 * @Override public void whatToDoWhenSubAlarmIsNoMoreEligible(Alarm alarm, Group
	 * group) throws Exception { // TODO Auto-generated method stub
	 * LOG.debug("whatToDoWhenSubAlarmIsNoMoreEligible : "+alarm.getIdentifier());
	 * Alarm problemAlarm = group.getProblemAlarm(); if(problemAlarm!=null) { String
	 * additonalText = calculateCustomAdditionalText(group);
	 * //LOG.trace("whatToDoWhenSubAlarmIsCleared : recalculated text :"
	 * +additonalText); Common_Util.updateAlarmAdditionalText(getScenario(),
	 * problemAlarm, additonalText); }
	 * 
	 * super.whatToDoWhenSubAlarmIsNoMoreEligible(alarm, group);
	 * 
	 * }
	 */

	@Override
	public void whatToDoWhenProblemAlarmIsAttachedToGroup(Group group) throws Exception {
		// TODO Auto-generated method stub
		LOG.debug("whatToDoWhenProblemAlarmIsAttachedToGroup enter: ");
		super.whatToDoWhenProblemAlarmIsAttachedToGroup(group);
		LOG.debug("whatToDoWhenProblemAlarmIsAttachedToGroup exit: ");
	}

	@Override
	public void whatToDoWhenProblemAlarmIsHandled(Group group) throws Exception {
		// TODO Auto-generated method stub
		if (LOG.isTraceEnabled()) {
			LogHelper.enter(LOG, "whatToDoWhenProblemAlarmIsHandled()", group.getName());
		}
		super.whatToDoWhenProblemAlarmIsHandled(group);
		LOG.info("whatToDoWhenProblemAlarmIsHandled : Alarm ID: " + group.getProblemAlarm().getIdentifier()
				+ " Ticket ID : " + group.getTroubleTicketIdentifier() + " Ticket Delay : "
				+ group.getTroubleTicketComputedDelay() + " Ticket Creation Time : "
				+ group.getTroubleTicketCreationRequestTime());

		LOG.info("whatToDoWhenProblemAlarmIsHandled : Group : " + group.toFormattedString());

		if (LOG.isTraceEnabled()) {
			LogHelper.exit(getLog(), "whatToDoWhenProblemAlarmIsHandled()");
		}
	}

	@Override
	public Long computeDelayForTroubleTicketCreation(Event event, Group group) {
		// TODO Auto-generated method stub
		if (LOG.isTraceEnabled()) {
			LogHelper.enter(getLog(), "computeDelayForTroubleTicketCreation()", group.getName());
		}

		Long problemAlarmDelay = super.computeDelayForTroubleTicketCreation(event, group);

		Alarm alarm = (Alarm) event;

		LOG.info("computeDelayForTroubleTicketCreation : Alarm ID : " + alarm.getIdentifier()
				+ " Alarm Additional Text : " + alarm.getAdditionalText() + " Group : " + group.getProblemAlarm()
				+ " TT Delay : " + problemAlarmDelay);

		if (LOG.isTraceEnabled()) {
			LogHelper.exit(getLog(), "computeDelayForTroubleTicketCreation()");
		}
		return problemAlarmDelay;
	}

	@Override
	public boolean isAllCriteriaForTroubleTicketCreation(Group group) throws Exception {
		// TODO Auto-generated method stub
		if (LOG.isTraceEnabled()) {
			LogHelper.enter(getLog(), "isAllCriteriaForTroubleTicketCreation()", group.getName());
		}
		boolean res = super.isAllCriteriaForTroubleTicketCreation(group);
		if (LOG.isTraceEnabled()) {
			LogHelper.exit(getLog(), "Super isAllCriteriaForTroubleTicketCreation()");
		}
		if (res) {
			LOG.info(
					"isAllCriteriaForTroubleTicketCreation in : Java Class " + group.getProblemAlarm().getIdentifier());
		}
		LOG.info("isAllCriteriaForTroubleTicketCreation : Result: " + res);
		return res;
	}

}
