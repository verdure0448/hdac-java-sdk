package com.hdactech.object;

import java.util.List;

public class ListAddressTxsDetailed {
	BalanceWalletTransactionNew balance;
	List<String> myaddresses;
	List<String> addresses;
	List<PermissionDetailed> permissions;
	AssetWalletTransaction issue = null;
	List<Item> items;
	List<String> data;
	Long confirmations = null;
	String blockhash = null;
	Long blockindex = null;
	Long blocktime = null;
	String txid = null;
	Boolean valid = null;
	Long time = null;
	Long timereceived = null;
	List<TransactionWalletVinDetailed> vin;
	List<TransactionWalletVoutDetailed> vout;
	String hex = null;
	/**
	 * @return the balance
	 */
	public BalanceWalletTransactionNew getBalance() {
		return balance;
	}
	/**
	 * @return the myaddresses
	 */
	public List<String> getMyaddresses() {
		return myaddresses;
	}
	/**
	 * @return the addresses
	 */
	public List<String> getAddresses() {
		return addresses;
	}
	/**
	 * @return the permissions
	 */
	public List<PermissionDetailed> getPermissions() {
		return permissions;
	}
	/**
	 * @return the issue
	 */
	public AssetWalletTransaction getIssue() {
		return issue;
	}
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @return the data
	 */
	public List<String> getData() {
		return data;
	}
	/**
	 * @return the confirmations
	 */
	public Long getConfirmations() {
		return confirmations;
	}
	/**
	 * @return the blockhash
	 */
	public String getBlockhash() {
		return blockhash;
	}
	/**
	 * @return the blockindex
	 */
	public Long getBlockindex() {
		return blockindex;
	}
	/**
	 * @return the blocktime
	 */
	public Long getBlocktime() {
		return blocktime;
	}
	/**
	 * @return the txid
	 */
	public String getTxid() {
		return txid;
	}
	/**
	 * @return the valid
	 */
	public Boolean getValid() {
		return valid;
	}
	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}
	/**
	 * @return the timereceived
	 */
	public Long getTimereceived() {
		return timereceived;
	}
	/**
	 * @return the vin
	 */
	public List<TransactionWalletVinDetailed> getVin() {
		return vin;
	}
	/**
	 * @return the vout
	 */
	public List<TransactionWalletVoutDetailed> getVout() {
		return vout;
	}
	/**
	 * @return the hex
	 */
	public String getHex() {
		return hex;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BalanceWalletTransactionNew balance) {
		this.balance = balance;
	}
	/**
	 * @param myaddresses the myaddresses to set
	 */
	public void setMyaddresses(List<String> myaddresses) {
		this.myaddresses = myaddresses;
	}
	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<PermissionDetailed> permissions) {
		this.permissions = permissions;
	}
	/**
	 * @param issue the issue to set
	 */
	public void setIssue(AssetWalletTransaction issue) {
		this.issue = issue;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<String> data) {
		this.data = data;
	}
	/**
	 * @param confirmations the confirmations to set
	 */
	public void setConfirmations(Long confirmations) {
		this.confirmations = confirmations;
	}
	/**
	 * @param blockhash the blockhash to set
	 */
	public void setBlockhash(String blockhash) {
		this.blockhash = blockhash;
	}
	/**
	 * @param blockindex the blockindex to set
	 */
	public void setBlockindex(Long blockindex) {
		this.blockindex = blockindex;
	}
	/**
	 * @param blocktime the blocktime to set
	 */
	public void setBlocktime(Long blocktime) {
		this.blocktime = blocktime;
	}
	/**
	 * @param txid the txid to set
	 */
	public void setTxid(String txid) {
		this.txid = txid;
	}
	/**
	 * @param valid the valid to set
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}
	/**
	 * @param timereceived the timereceived to set
	 */
	public void setTimereceived(Long timereceived) {
		this.timereceived = timereceived;
	}
	/**
	 * @param vin the vin to set
	 */
	public void setVin(List<TransactionWalletVinDetailed> vin) {
		this.vin = vin;
	}
	/**
	 * @param vout the vout to set
	 */
	public void setVout(List<TransactionWalletVoutDetailed> vout) {
		this.vout = vout;
	}
	/**
	 * @param hex the hex to set
	 */
	public void setHex(String hex) {
		this.hex = hex;
	}
	
}
