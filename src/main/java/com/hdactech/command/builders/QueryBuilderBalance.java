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
public class QueryBuilderBalance extends QueryBuilderCommon {

	/**
	 * 
	 * gettotalbalances ( minconf includeWatchonly includeLocked)
	 * 
	 * If account is not specified, returns the server's total available asset balances.
	 * If account is specified, returns the balances in the account.
	 * Note that the account "" is not the same as leaving the parameter out.
	 * The server total may be different to the balance in the default "" account.
	 * 
	 * Arguments:
	 * 1. minconf (numeric, optional, default=1) Only include transactions confirmed at least this many times.
	 * 2. includeWatchonly (bool, optional, default=false) Also include balance in watchonly addresses (see
	 * 'importaddress')
	 * 3. includeLocked (bool, optional, default=false) Also take locked outputs into account
	 * Results are an array of Objects with totals and details for each asset.
	 * 
	 * @return a list of all the asset balances in this node's wallet
	 * @throws HdacException
	 */
	protected Object executeGetTotalBalances() throws HdacException {
		return execute(CommandEnum.GETTOTALBALANCES);
	}

	/**
	 * 
	 * getunconfirmedbalance
	 * Returns the server's total unconfirmed balance
	 * 
	 * @return a list of all the asset balances in this node's wallet
	 * @throws HdacException
	 */
	protected Object executeGetUnconfirmedBalance() throws HdacException {
		return execute(CommandEnum.GETTOTALBALANCES);
	}

}
