package com.hdactech.object;

/**
 * @author Justin
 *
 */
public class DecodeRawExchnageDetailed {
	ExchangeOfferDetailed offer;
	ExchangeAskDetailed ask;
	
	public DecodeRawExchnageDetailed() {
		offer = new ExchangeOfferDetailed();
		ask = new ExchangeAskDetailed();
	}
	
	/**
	 * @return the offerDetailed
	 */
	public ExchangeOfferDetailed getOfferDetailed() {
		return offer;
	}

	/**
	 * @return the askDetailed
	 */
	public ExchangeAskDetailed getAskDetailed() {
		return ask;
	}

	/**
	 * @param offerDetailed the offerDetailed to set
	 */
	public void setOfferDetailed(ExchangeOfferDetailed offer) {
		this.offer = offer;
	}

	/**
	 * @param askDetailed the askDetailed to set
	 */
	public void setAskDetailed(ExchangeAskDetailed ask) {
		this.ask = ask;
	}
}
