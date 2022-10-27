/**
 * 
 */
package com.promineo.color.entity;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;
import lombok.Data;

/**
 * @author pbuda
 *
 */
@Data
@Builder
public class OrderRequest {
 
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
private String customer;
  
  @NotNull
private ColorBrand brand;
  
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
private String type;
  
  
  private List<@NotNull @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")String> options;
}
