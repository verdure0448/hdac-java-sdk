package com.hdactech.object;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Justin
 *
 */

public class UnspentList {
	String txid = null;
	Long vout = null;
	String address = null;
	String account = null;
	String scriptPubKey = null;
	Double amount = null;
	Long confirmations = null;
	Boolean cansend = null;
	Boolean spendable = null;
	List<BalanceAssetGeneral> assets;
	List<Permission> permissions;
	
	public UnspentList() {
		super();
		assets = new ArrayList<BalanceAssetGeneral>();
		permissions = new ArrayList<Permission>();
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
	public Long getVout() {
		return vout;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @return the scriptPubKey
	 */
	public String getScriptPubKey() {
		return scriptPubKey;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @return the confirmations
	 */
	public Long getConfirmations() {
		return confirmations;
	}

	/**
	 * @return the cansend
	 */
	public Boolean getCansend() {
		return cansend;
	}

	/**
	 * @return the spendable
	 */
	public Boolean getSpendable() {
		return spendable;
	}

	/**
	 * @return the assets
	 */
	public List<BalanceAssetGeneral> getAssets() {
		return assets;
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param asset to add to the assets list
	 */
	public void addAsset(BalanceAssetGeneral asset) {
		this.assets.add(asset);
	}
	
	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<BalanceAssetGeneral> assets) {
		this.assets = assets;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	/**
	 * @param permissions the permissions to set
	 */
	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}
}
