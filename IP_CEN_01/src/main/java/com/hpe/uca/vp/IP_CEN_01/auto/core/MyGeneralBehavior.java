package com.hpe.uca.vp.IP_CEN_01.auto.core;

import org.slf4j.LoggerFactory;

import com.hpe.uca.expert.vp.auto.interfaces.GeneralBehaviorInterface;
import com.hpe.uca.expert.vp.auto.core.GeneralBehaviorDefault;

/**
 * The Class MyGeneralBehavior.
 * 
 * This behavior is empty and ready to define methods to customize the
 * GeneralBehaviorDefault
 */
public class MyGeneralBehavior extends GeneralBehaviorDefault implements
		GeneralBehaviorInterface {

	/**
	 * Instantiates a new my general behavior.
	 */
	public MyGeneralBehavior() {
		super(LoggerFactory.getLogger(MyGeneralBehavior.class));
	}

}
