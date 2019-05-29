/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command;

import com.hdactech.command.builders.QueryBuilderKey;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class KeyCommand extends QueryBuilderKey {
	public KeyCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}
	
	/**
	 * 
	 * dumpprivkey "address"
	 * 
	 * Reveals the private key corresponding to 'address'. Then the importprivkey can be used with this output
	 * 
	 * Arguments: 1. "address" (string, required) The Hdac address for the private key
	 * 
	 * Result: "key" (string) The private key
	 * 
	 * @param start
	 * @return 
	 * @throws HdacException
	 */
	public Object getPrivkey(String privkey) throws HdacException {
		return executeDumpPrivkey(privkey);
	}
	
	/**
	 * 
	 * importprivkey privkey(s)
	 * 
	 * Adds a private key (as returned by dumpprivkey) to your wallet.
	 * 
	 * Arguments: 1. "privkey(s)" (string, required) The private key (see dumpprivkey), comma delimited
	 * 
	 * 
	 * @param privkey
	 * @return 
	 * @throws HdacException
	 */
	public Object importPrivkey(String privkey) throws HdacException {
		return executeImportPrivkey(privkey);
	}

}
