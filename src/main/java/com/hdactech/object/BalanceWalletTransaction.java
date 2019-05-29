/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object;

import java.util.List;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class BalanceWalletTransaction {
	Double amount = null;
	List<BalanceAssetWalletTransaction> assets;
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the assets
	 */
	public List<BalanceAssetWalletTransaction> getAssets() {
		return assets;
	}
	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<BalanceAssetWalletTransaction> assets) {
		this.assets = assets;
	}
	
}
