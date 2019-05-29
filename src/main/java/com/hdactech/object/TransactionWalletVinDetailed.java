package com.hdactech.object;

import java.util.List;

public class TransactionWalletVinDetailed {
	String txid = null;
	Long vout = null;
	List<String> addresses;
	String type;
	Boolean ismine = null;
	Boolean iswatchonly = null;
	Long amount = null;
	List<BalanceAssetWalletTransactionNew> assets;
	/**
	 * @return the txid
	 */
	public String getTxid() {
		return txid;
	}
	/**
	 * @return the vout
	 */
	public Long getVout() {
		return vout;
	}
	/**
	 * @return the addresses
	 */
	public List<String> getAddresses() {
		return addresses;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the ismine
	 */
	public Boolean getIsmine() {
		return ismine;
	}
	/**
	 * @return the iswatchonly
	 */
	public Boolean getIswatchonly() {
		return iswatchonly;
	}
	/**
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}
	/**
	 * @return the assets
	 */
	public List<BalanceAssetWalletTransactionNew> getAssets() {
		return assets;
	}
	/**
	 * @param txid the txid to set
	 */
	public void setTxid(String txid) {
		this.txid = txid;
	}
	/**
	 * @param vout the vout to set
	 */
	public void setVout(Long vout) {
		this.vout = vout;
	}
	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param ismine the ismine to set
	 */
	public void setIsmine(Boolean ismine) {
		this.ismine = ismine;
	}
	/**
	 * @param iswatchonly the iswatchonly to set
	 */
	public void setIswatchonly(Boolean iswatchonly) {
		this.iswatchonly = iswatchonly;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<BalanceAssetWalletTransactionNew> assets) {
		this.assets = assets;
	}
	
}
