/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.queryobjects;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class DataParamHex implements DataParam {
  private String hexValue = null;
  
  @Override
  public String getFormatedvalue() {
    return getHexValue();
  }

  /**
   * 
   */
  public DataParamHex() {
    super();
  }

  /**
   * @param hexValue
   */
  public DataParamHex(String hexValue) {
    this.hexValue = hexValue;
  }

  /**
   * @return the hexValue
   */
  public String getHexValue() {
    return hexValue;
  }

  /**
   * @param hexValue the hexValue to set
   */
  public void setHexValue(String hexValue) {
    this.hexValue = hexValue;
  }

  
}
