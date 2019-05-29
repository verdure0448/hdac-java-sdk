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
public class ParamIssueMore extends ParamIssue {
  private String asset = null;
  /**
   * 
   */
  public ParamIssueMore() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  public boolean isFilled() {
    boolean filled = true;
    
    filled = super.isFilled();
    
    if (asset == null || asset.isEmpty()) {
      filled = filled  && false;
    }
    
    return filled;
  }
  
  /**
   * @param raw
   * @param asset
   */
  public ParamIssueMore(Long raw, String asset) {
    super(raw);
    this.asset = asset;
  }
  /**
   * @return the asset
   */
  public String getAsset() {
    return asset;
  }
  /**
   * @param asset the asset to set
   */
  public void setAsset(String asset) {
    this.asset = asset;
  }
  
  
}
