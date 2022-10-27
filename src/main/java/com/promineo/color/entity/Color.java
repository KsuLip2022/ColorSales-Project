/**
 * 
 */
package com.promineo.color.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author pbuda
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Color implements Comparable<Color>{
  
  private Long brandPK;
  private ColorBrand brandId;
  private String typePaint;
  private BigDecimal basePrice;
  
  @JsonIgnore
  public Long getBrandPK() {
    return brandPK;
  }

  @Override
  public int compareTo(Color that) {
    // @formatter: off
    return Comparator
        .comparing(Color::getBrandId)
        .thenComparing(Color::getTypePaint)
        .compare(this, that);
    // @formatter: on
    
  }
}
