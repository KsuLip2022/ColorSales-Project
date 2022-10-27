/**
 * 
 */
package com.promineo.color.entity;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

/**
 * @author pbuda
 *
 */
@Data
@Builder
public class Order {
  private Long orderPK;
  private Customer customer;
  private Color brand;
  private BigDecimal price;
  private List<Option> options;
  
  @JsonIgnore
  public Long getOrderPK() {
    return orderPK;
  }
}
