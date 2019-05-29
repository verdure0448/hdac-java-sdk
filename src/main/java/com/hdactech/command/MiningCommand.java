/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command;

import com.hdactech.command.builders.QueryBuilderMining;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class MiningCommand extends QueryBuilderMining {
	public MiningCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}
	
	/**
	 * 
	 * pause 
	 * 
	 * Pauses local mining or the processing of incoming transactions and blocks.
	 * 
	 * @return 
	 * @throws HdacException
	 */
	public Object pauseMining() throws HdacException {
		return executePauseMining();
	}
	
	/**
	 * 
	 * resume 
	 * 
	 * Resumes local mining or the processing of incoming transactions and blocks
	 * 
	 * @return 
	 * @throws HdacException
	 */
	public Object resumeMining() throws HdacException {
		return executeResumeMining();
	}
	
}
