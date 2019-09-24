package ft.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hp.uca.expert.config.UcaSettings;
import com.hp.uca.expert.server.topologyserver.TopoServerProxy;
import com.hp.uca.expert.vp.common.testmaterial.InferenceMachineJUnitIntegrationTest;
import com.hpe.uca.expert.topology.loader.CsvLoader;

/**
 * 
 * Sample test
 * 
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleTest extends InferenceMachineJUnitIntegrationTest {

    @SuppressWarnings("unused")
    private static Logger LOG = LoggerFactory.getLogger(SampleTest.class);

    @Before
    @Override
    public void setUp() throws Exception {

        // Initialize scenarios and flows & enable engine internal logs
        retrieveScenariosAndStartDBFlows();
        initTest(pdScenario, BMK_PATH);

        super.setUp();

        // Load the topology for the test
        UcaSettings.TOPOLOGY_MODE.setValue(TopoServerProxy.TOPOLOGY_IMPERMANENT);
        CsvLoader.load("src/test/resources/neo4j/import/csv");
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        TopoServerProxy.shutdown();
    }

    // --------------------------- test cases

    @Test
    @DirtiesContext
    public final void sampleTest() throws Exception {
        // String ALARM_FILE = "src/test/resources/ft/sample/Alarms.xml";
        //
        // // Register listeners
        // AlarmListener alarmSwitchDownListener = createAndAssignAlarmListener(
        // "Smarts::UCA-MSB-SAMa::NOTIFICATION-Switch_NB_SWITCH_D_Down", pdScenario);
        //
        // GroupListener nb_switch_d_GrListener = createAndAssignGroupListener(
        // "<p>Problem_SwitchDown</p><e>nb_switch_d</e>", pdScenario);
        // GroupListener nb_switch_g_GrListener = createAndAssignGroupListener(
        // "<p>Problem_SwitchDown</p><e>nb_switch_g</e>", pdScenario);
        // GroupListener g_switch3_GrListener =
        // createAndAssignGroupListener("<p>Problem_SwitchDown</p><e>g_switch3</e>",
        // pdScenario);
        //
        // AlarmListener alarmFirstSAListener = createAndAssignAlarmListener("UCA-123456", tpScenario);
        // AlarmListener alarmLastSAListener = createAndAssignAlarmListener("UCA-123458", tpScenario);
        // StateListener stateSwitch1 = createAndAssignStateListener("StateBase#g_switch1", tpScenario);
        // StateListener stateWM1 = createAndAssignStateListener("StateBase#g_vm1", tpScenario);
        // StateListener statePoolA3 = createAndAssignStateListener("StateBase#g_poola3", tpScenario);
        //
        // // Send alarms
        // LOG.info("========SENDING SWITCH DOWN===========================================================");
        // getProducer().sendAlarms(ALARM_FILE, 10);
        //
        // waitingForAlarmInsertion(alarmSwitchDownListener, 1 * SECOND, 10 * SECOND);
        // waitingForAlarmInsertion(alarmFirstSAListener, 1 * SECOND, 20 * SECOND);
        //
        // // Checking alarm updates
        // waitingForAlarmUpdate(alarmLastSAListener, 1 * SECOND, 10 * SECOND);
        // waitingForAlarmUpdate(alarmFirstSAListener, 1 * SECOND, 10 * SECOND);
        // waitingForAlarmUpdate(alarmSwitchDownListener, 1 * SECOND, 10 * SECOND);
        //
        // // Checking state insertions
        // waitingForStateInsertion(stateSwitch1, 1 * SECOND, 20 * SECOND);
        // waitingForStateInsertion(stateWM1, 1 * SECOND, 20 * SECOND);
        //
        // // Checking state updates
        // waitingForStateUpdate(stateWM1, 1 * SECOND, 20 * SECOND);
        //
        // // Checking last state insertion
        // waitingForStateInsertion(statePoolA3, 1 * SECOND, 20 * SECOND);
        //
        // waitingForAlarmInsertion(alarmLastSAListener, 1 * SECOND, 20 * SECOND);
        //
        // displayIntermediateSteps("before WM check");
        //
        // if (LOG.isDebugEnabled()) {
        // LOG.debug("PROPAGATION GROUPS START");
        // LOG.debug(tpScenario.getPropagationGroups().toString());
        // LOG.debug("PROPAGATION GROUPS END");
        // LOG.debug("==============================================================================================");
        // }
        //
        // // Checking the number of Groups
        // Groups groupsCollection = pdScenario.getGroups();
        // Collection<Group> groups = groupsCollection.getAllGroups();
        // assertEquals(3, groups.size());
        //
        // // Checking the number of Propagation Groups
        // PropagationGroups propGroupsCollection = tpScenario.getPropagationGroups();
        // Collection<PropagationGroup> propaGroups = propGroupsCollection.getAllGroups();
        // assertEquals(81, propaGroups.size());
        //
        // // Checking the number of Alarms in PD scenario
        // Collection<Alarm> alarms = getAlarmsFromWorkingMemory();
        // assertEquals(9, alarms.size());
        //
        // // ///////////////////////////////////////////////
        // // Checking PD alarms, relationship, groups
        // // ///////////////////////////////////////////////
        // Group nb_switch_dGr = nb_switch_d_GrListener.getGroup();
        // Group nb_switch_gGr = nb_switch_g_GrListener.getGroup();
        // Group g_switch_3Gr = g_switch3_GrListener.getGroup();
        //
        // // Checking alarms in PD
        //
        // // part of group Problem_SwitchDown nb_switch_d
        // Alarm rcNBSWD = getAlarm("Smarts::UCA-MSB-SAMa::NOTIFICATION-Switch_NB_SWITCH_D_Down");
        // Alarm pbNBswD = getAlarm("UCA-123456");
        //
        // assertNotNull(pbNBswD);
        //
        // assertTrue(nb_switch_dGr.getAlarmList().contains(rcNBSWD));
        // assertTrue(nb_switch_dGr.getProblemAlarm().equals(pbNBswD));
        // assertTrue(groupsCollection.getSetAs(nb_switch_dGr, pbNBswD).equals(Qualifier.ProblemAlarm));
        //
        // // part of group Problem_SwitchDown nb_switch_g
        // Alarm rcNBSWG = getAlarm("Smarts::UCA-MSB-SAMa::NOTIFICATION-Switch_NB_SWITCH_G_Down");
        // Alarm rcPhone9 = getAlarm("Smarts::UCA-MSB-SAMa::NOTIFICATION-PHONE_145.063.075.079_Unregistered");
        // Alarm rcPhone8 = getAlarm("Smarts::UCA-MSB-SAMa::NOTIFICATION-PHONE_145.063.075.078_Unregistered");
        // Alarm pbNbSWG = getAlarm("UCA-123457");
        // assertNotNull(pbNbSWG);
        //
        // assertTrue(nb_switch_gGr.getAlarmList().contains(rcNBSWG));
        // assertTrue(groupsCollection.getSetAs(nb_switch_gGr, pbNbSWG).equals(Qualifier.ProblemAlarm));
        //
        // // part of group Problem_SwitchDown g_switch_3
        // Alarm rcSW3 = getAlarm("Smarts::UCA-MSB-SAMa::NOTIFICATION-Switch_G_SWITCH3_Down");
        // Alarm pbGSW3 = getAlarm("UCA-123458");
        //
        // assertNotNull(pbGSW3);
        // assertTrue(g_switch_3Gr.getAlarmList().contains(rcSW3));
        // assertTrue(groupsCollection.getSetAs(g_switch_3Gr, pbGSW3).equals(Qualifier.ProblemAlarm));
        //
        // // Checking associations for triggers with their PB alarms
        // assertEquals(1, rcSW3.getParentsNumber());
        // assertEquals(1, rcNBSWG.getParentsNumber());
        // assertEquals(1, rcNBSWD.getParentsNumber());
        //
        // // //////////////////////////////////////////////////
        // // Checking TSP alarms, relationships with alarms from PD, states,
        // // groups
        // // ///////////////////////////////////////////////
        // Iterator<PropagationGroup> it = propaGroups.iterator();
        //
        // PropagationGroup customerGrNatBank = null;
        // PropagationGroup customerGrGardens = null;
        // PropagationGroup nbphoneGr = null;
        // PropagationGroup gphoneGr = null;
        //
        // while (it.hasNext()) {
        // PropagationGroup group = it.next();
        // if (group.getPropagationContext().getName().equals("Propagation_PhoneService")) {
        // if (group.getPropagationEntity().equals("nb_phone")) {
        // if (LOG.isDebugEnabled()) {
        // LOG.debug("CHECKING " + group.getName());
        // }
        // nbphoneGr = group;
        // } else {
        // if (group.getPropagationEntity().equals("g_phone")) {
        // LOG.info("CHECKING " + group.getName());
        // gphoneGr = group;
        // }
        // }
        // } else {
        //
        // if (group.getPropagationContext().getName().equals("Propagation_Customer")) {
        // if (group.getPropagationEntity().equals("gardens")) {
        // if (LOG.isDebugEnabled()) {
        // LOG.debug("CHECKING " + group.getName());
        // }
        // customerGrGardens = group;
        // } else {
        // if (group.getPropagationContext().getName().equals("Propagation_Customer")
        // && group.getPropagationEntity().equals("nationalbank")) {
        // if (LOG.isDebugEnabled()) {
        // LOG.debug("CHECKING " + group.getName());
        // }
        // customerGrNatBank = group;
        //
        // }
        // }
        // }
        //
        // }
        //
        // }
        //
        // // //////// CUSTOMER GARDENS
        //
        // // whole sub tree containing sub rc (pb alarms)
        // Set<Alarm> wst = customerGrGardens.getWholeSubTreeRootCauses();
        // assertNotNull(wst);
        //
        // Iterator<Alarm> itwst = wst.iterator();
        // ArrayList<String> keys = new ArrayList<>(2);
        // // constructing keys
        // while (itwst.hasNext()) {
        // Alarm rc = itwst.next();
        // keys.add(rc.getIdentifier());
        // }
        //
        // assertFalse(keys.contains(pbNBswD.getIdentifier()));
        // assertFalse(keys.contains(pbNbSWG.getIdentifier()));
        // assertTrue(keys.contains(pbGSW3.getIdentifier()));
        //
        // // assert SA not null
        // assertNotNull(customerGrGardens.getServiceAlarm());
        // assertNotNull(gphoneGr.getServiceAlarm());
        //
        // assertTrue(customerGrGardens.getServiceAlarm().getNetworkState().equals(NetworkState.NOT_CLEARED));
        //
        // assertTrue(customerGrGardens.getFirstLevelOfSubServiceAlarms().contains(gphoneGr.getServiceAlarm()));
        //
        // assertTrue(propGroupsCollection.getSetAs(customerGrGardens, customerGrGardens.getServiceAlarm())
        // .equals(PropagationQualifier.ServiceAlarm));
        //
        // assertTrue(tpScenario.getPropagationGroups().getSetAs(customerGrGardens, gphoneGr.getServiceAlarm())
        // .equals(PropagationQualifier.SubServiceAlarm));
        //
        // // //////// CUSTOMER NATIONAL BANK
        // // whole sub tree containing sub rc (pb alarms)
        // Set<Alarm> wst2 = customerGrNatBank.getWholeSubTreeRootCauses();
        // assertNotNull(wst2);
        //
        // Iterator<Alarm> itwst2 = wst2.iterator();
        // ArrayList<String> keys2 = new ArrayList<>(2);
        // // constructing keys
        // while (itwst2.hasNext()) {
        // Alarm rc = itwst2.next();
        // keys2.add(rc.getIdentifier());
        // }
        //
        // assertTrue(keys2.contains(pbNBswD.getIdentifier()));
        // assertFalse(keys2.contains(pbGSW3.getIdentifier()));
        // assertTrue(keys2.contains(pbNbSWG.getIdentifier()));
        //
        // // assert SA not null
        // assertNotNull(customerGrNatBank.getServiceAlarm());
        // assertNotNull(nbphoneGr.getServiceAlarm());
        //
        // assertTrue(customerGrNatBank.getServiceAlarm().getNetworkState().equals(NetworkState.NOT_CLEARED));
        // assertTrue(customerGrNatBank.getFirstLevelOfSubServiceAlarms().contains(nbphoneGr.getServiceAlarm()));
        //
        // assertTrue(propGroupsCollection.getSetAs(customerGrNatBank, customerGrNatBank.getServiceAlarm())
        // .equals(PropagationQualifier.ServiceAlarm));
        //
        // assertTrue(propGroupsCollection.getSetAs(customerGrNatBank, nbphoneGr.getServiceAlarm())
        // .equals(PropagationQualifier.SubServiceAlarm));
        //
        // /*
        // * Check state transition minimal
        // */
        // List<State> y = TP_Process.getCounterStates();
        // assertTrue("counterStates=" + y.size(), y.size() <= 66);
        // if (LOG.isDebugEnabled()) {
        // for (State s : y) {
        // LOG.debug(s.toString());
        // }
        // }
        //
        // /*
        // * Checking Actions Number done on DB
        // */
        // List<AlarmBaseInterface> z = DBActionsFactory.getWrittenAlarms();
        // assertTrue("writtenAlarms=" + z.size(), z.size() <= 51);
        // if (LOG.isDebugEnabled()) {
        // LOG.debug("writtenAlarms=" + z.size());
        // for (AlarmBaseInterface a : z) {
        // LOG.debug("FORWARDED TO DB:\n" + a.toXMLString());
        // }
        // }
        //
        // /*
        // * Check associations for root causes of TSP (pb alarms in PD) whole sub tree of customers
        // */
        // // wst gardens
        // for (Alarm a : wst) {
        // assertEquals(2, a.getParentsNumber());
        // }
        // // wst national bank
        // for (Alarm a : wst2) {
        // assertEquals(2, a.getParentsNumber());
        // }
        //
        // /*
        // * Check associations for service alarms
        // */
        //
        // assertEquals(2, nbphoneGr.getServiceAlarm().getChildrenNumber());
        // assertEquals(2, customerGrGardens.getServiceAlarm().getChildrenNumber());
        // assertEquals(3, customerGrNatBank.getServiceAlarm().getChildrenNumber());

    }

}
