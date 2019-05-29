package com.hdactech.object;

import java.util.ArrayList;
import java.util.List;

public class ListWalletTxsDetailed {
	ListWalletTxs listWalletTxs;
	String comment = null;
	List<TransactionWalletVinDetailed> vin;
	List<TransactionWalletVoutDetailed> vout;
	String hex = null;

	public ListWalletTxsDetailed() {
		listWalletTxs = new ListWalletTxs();
		vin = new ArrayList<TransactionWalletVinDetailed>();
		vout = new ArrayList<TransactionWalletVoutDetailed>();
	}

	/**
	 * @return the listWalletTxs
	 */
	public ListWalletTxs getListWalletTxs() {
		return listWalletTxs;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
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
	 * @param listWalletTxs the listWalletTxs to set
	 */
	public void setListWalletTxs(ListWalletTxs listWalletTxs) {
		this.listWalletTxs = listWalletTxs;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
