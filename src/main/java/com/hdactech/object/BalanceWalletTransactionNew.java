package com.hdactech.object;

import java.util.List;

public class BalanceWalletTransactionNew {
	Double amount = null;
	List<BalanceAssetWalletTransactionNew> assets;
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @return the assets
	 */
	public List<BalanceAssetWalletTransactionNew> getAssets() {
		return assets;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<BalanceAssetWalletTransactionNew> assets) {
		this.assets = assets;
	}
	
}
