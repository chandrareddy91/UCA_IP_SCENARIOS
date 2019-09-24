package com.hpe.uca.vp.IP_CEN_01.tp.core;

import org.slf4j.LoggerFactory;

import com.hp.uca.expert.vp.tp.core.GeneralBehaviorDefault;
import com.hp.uca.expert.vp.tp.interfaces.GeneralBehaviorInterface;

/**
 * @author UCA Team
 */
public class MyGeneralBehavior extends GeneralBehaviorDefault implements
		GeneralBehaviorInterface {

	public MyGeneralBehavior() {
		super(LoggerFactory.getLogger(MyGeneralBehavior.class));
	}

}
