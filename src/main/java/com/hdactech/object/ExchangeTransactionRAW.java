package com.hdactech.object;

/**
 * @author Justin
 *
 */
public class ExchangeTransactionRAW {

	ExchangeOffer offer;
	ExchangeAsk ask;
	Double requiredfee = null;
	Boolean candisable = null;
	Boolean cancomplete = null;
	Boolean complete = null;
	
	public ExchangeTransactionRAW() {
		super();
		offer = new ExchangeOffer();
		ask = new ExchangeAsk();
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
	 * @param offer the offer to set
	 */
	public void setOffer(ExchangeOffer offer) {
		this.offer = offer;
	}

	/**
	 * @param ask the ask to set
	 */
	public void setAsk(ExchangeAsk ask) {
		this.ask = ask;
	}
}
