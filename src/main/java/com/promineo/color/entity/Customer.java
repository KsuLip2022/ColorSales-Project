/**
 * 
 */
package com.promineo.color.entity;


import lombok.Builder;
import lombok.Data;

/**
 * @author pbuda
 *
 */
  @Data
  @Builder
  public class Customer {
    private Long customerPK;
    private String customerId;
    private String firstName;
    private String lastName;
    private String phone;
  }

