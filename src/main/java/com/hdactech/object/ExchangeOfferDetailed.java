package com.hdactech.object;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin
 *
 */
public class ExchangeOfferDetailed {
	Double amount = null;
	List<BalanceAssetGeneral> assets;
	String address = null;
	String txid = null;
	long vout = 0;

	public ExchangeOfferDetailed() {
		assets = new ArrayList<BalanceAssetGeneral>();
	}
	
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the txid
	 */
	public String getTxid() {
		return txid;
	}

	/**
	 * @return the vout
	 */
	public long getVout() {
		return vout;
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

