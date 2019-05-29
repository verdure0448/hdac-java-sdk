package com.hdactech.object;

import java.util.List;

public class TransactionWalletVoutDetailed {
	Long n = null;
	List<String> addresses;
	String type = null;
	Boolean ismine = null;
	Boolean iswatchonly = null;
	Long amount = null;
	List<BalanceAssetWalletTransactionNews> assets;
	List<PermissionDetailed> permissions;
	List<Item> items;
	/**
	 * @return the n
	 */
	public Long getN() {
		return n;
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
	public List<BalanceAssetWalletTransactionNews> getAssets() {
		return assets;
	}
	/**
	 * @return the permissions
	 */
	public List<PermissionDetailed> getPermissions() {
		return permissions;
	}
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param n the n to set
	 */
	public void setN(Long n) {
		this.n = n;
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
	public void setAssets(List<BalanceAssetWalletTransactionNews> assets) {
		this.assets = assets;
	}
	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<PermissionDetailed> permissions) {
		this.permissions = permissions;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
