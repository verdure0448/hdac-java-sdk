package com.hdactech.object;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justin
 *
 */
public class ExchangeTransactionRAWDetailed {
	
	ExchangeOffer offer;
	ExchangeAsk ask;
	Double requiredfee = null;
	Boolean candisable = null;
	Boolean cancomplete = null;
	Boolean complete = null;
	List<DecodeRawExchnageDetailed> exchanges;
	
	public ExchangeTransactionRAWDetailed() {
		offer = new ExchangeOffer();
		ask = new ExchangeAsk();
		exchanges = new ArrayList<DecodeRawExchnageDetailed>();
	}

	/**
	 * @return the offer
	 */
	public ExchangeOffer getOffer() {
		return offer;
	}

	/**
	 * @return the ask
	 */
	public ExchangeAsk getAsk() {
		return ask;
	}

	/**
	 * @return the requiredfee
	 */
	public Double getRequiredfee() {
		return requiredfee;
	}

	/**
	 * @return the candisable
	 */
	public Boolean getCandisable() {
		return candisable;
	}

	/**
	 * @return the cancomplete
	 */
	public Boolean getCancomplete() {
		return cancomplete;
	}

	/**
	 * @return the complete
	 */
	public Boolean getComplete() {
		return complete;
	}

	/**
	 * @return the exchanges
	 */
	public List<DecodeRawExchnageDetailed> getExchanges() {
		return exchanges;
	}

	/**
	 * @param exchanges the exchanges to set
	 */
	public void setExchanges(List<DecodeRawExchnageDetailed> exchanges) {
		this.exchanges = exchanges;
	}
	
	/**
	 * @param exchanges to add to the exchanges list
	 */
	public void addExchanges(List<DecodeRawExchnageDetailed> exchanges) {
		this.exchanges = exchanges;
	}
}
