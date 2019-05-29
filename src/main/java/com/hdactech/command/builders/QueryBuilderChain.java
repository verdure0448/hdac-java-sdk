/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command.builders;

import com.hdactech.command.HdacException;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class QueryBuilderChain extends QueryBuilderCommon {
	
	protected Object executeObjectGetInfo() throws HdacException {
		return execute(CommandEnum.GETINFO);
	}
	
	protected String executeGetInfo() throws HdacException {
		return execute(CommandEnum.GETINFO).toString();
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeGetPeerInfo() throws HdacException {
		return execute(CommandEnum.GETPEERINFO);
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	protected String executeHelp(String arg)  throws HdacException {
		if(arg == null) {
			return (String) execute(CommandEnum.HELP);
		} else {
			return (String) execute(CommandEnum.HELP, arg);
		}
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeGetBlockchainParams() throws HdacException {
		return execute(CommandEnum.GETBLOCKCHAINPARAMS);
	}
}
