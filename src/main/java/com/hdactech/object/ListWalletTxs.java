package com.hdactech.object;

import java.util.ArrayList;
import java.util.List;

public class ListWalletTxs {
	BalanceWalletTransaction balance;
	List<String> myaddresses;
	List<String> addresses;
	List<PermissionDetailed> permissions;
	StreamBasic create;
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
	
	public ListWalletTxs() {
		balance = new BalanceWalletTransaction();
		myaddresses = new ArrayList<String>();
		addresses = new ArrayList<String>();
		permissions = new ArrayList<PermissionDetailed>();
		create = new StreamBasic();
		items = new ArrayList<Item>();
		data = new ArrayList<String>();
	}

	/**
	 * @return the balance
	 */
	public BalanceWalletTransaction getBalance() {
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
	 * @return the create
	 */
	public StreamBasic getCreate() {
		return create;
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
	 * @param balance the balance to set
	 */
	public void setBalance(BalanceWalletTransaction balance) {
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
	 * @param create the create to set
	 */
	public void setCreate(StreamBasic create) {
		this.create = create;
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
}
