package com.hdactech.object;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin
 *
 */
public class ExchangeOffer {
	Double amount;
	List<BalanceAssetGeneral> assets;
	
	
	public ExchangeOffer() {
		assets = new ArrayList<BalanceAssetGeneral>();
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @return the assets
	 */
	public List<BalanceAssetGeneral> getAssets() {
		return assets;
	}
	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<BalanceAssetGeneral> assets) {
		this.assets = assets;
	}
	/**
	 * @param asset to add to the assets list
	 */
	public void addAsset(BalanceAssetGeneral asset) {
		this.assets.add(asset);
	}
}
