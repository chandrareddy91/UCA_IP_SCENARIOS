/**
 *
 */
package com.hpe.uca.vp.IP_CEN_01.tp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.uca.expert.vp.tp.core.PropagationDefault;
import com.hp.uca.expert.vp.tp.interfaces.PropagationInterface;

/**
 * @author UCA Team
 * 
 */
public class MyPropagationDefault extends PropagationDefault implements PropagationInterface {

	public MyPropagationDefault() {
		super(LoggerFactory.getLogger(MyPropagationDefault.class));
	}

	public MyPropagationDefault(Logger log) {
		super(log);
	}

}
