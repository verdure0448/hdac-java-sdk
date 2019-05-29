/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command;

import java.util.ArrayList;
import java.util.List;

import com.hdactech.command.builders.QueryBuilderBalance;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.object.formatters.BalanceFormatter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class BalanceCommand extends QueryBuilderBalance {
	public BalanceCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * 
	 * gettotalbalances ( minconf includeWatchonly includeLocked)
	 * 
	 * If account is not specified, returns the server's total available asset
	 * balances. If account is specified, returns the balances in the account.
	 * Note that the account "" is not the same as leaving the parameter out.
	 * The server total may be different to the balance in the default ""
	 * account.
	 * 
	 * Arguments: 1. minconf (numeric, optional, default=1) Only include
	 * transactions confirmed at least this many times. 2. includeWatchonly
	 * (bool, optional, default=false) Also include balance in watchonly
	 * addresses (see 'importaddress') 3. includeLocked (bool, optional,
	 * default=false) Also take locked outputs into account Results are an array
	 * of Objects with totals and details for each asset.
	 * 
	 * @return a list of all the asset balances in this node's wallet
	 * @throws HdacException
	 */
	public List<BalanceAssetGeneral> getTotalBalances() throws HdacException {
		List<BalanceAssetGeneral> listBalanceAsset = new ArrayList<BalanceAssetGeneral>();

		Object objectBalanceAsset = executeGetTotalBalances();

		if (verifyInstance(objectBalanceAsset, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectBalanceAsset, BalanceAssetGeneral.class)) {
			listBalanceAsset = BalanceFormatter.formatBalanceAssetsGeneral((ArrayList<Object>) objectBalanceAsset);
		}

		return listBalanceAsset;
	}

	/**
	 * 
	 * getunconfirmedbalance Returns the server's total unconfirmed balance
	 * 
	 * @return not formated balance
	 * @throws HdacException
	 */
	public String getUnconfirmedBalance() throws HdacException {
		String stringUnconfirmedBalance = "";

		Object objectUnconfirmedBalance = executeGetUnconfirmedBalance();
		if (verifyInstance(objectUnconfirmedBalance, String.class)) {
			stringUnconfirmedBalance = (String) objectUnconfirmedBalance;
		}

		return stringUnconfirmedBalance;
	}

}
