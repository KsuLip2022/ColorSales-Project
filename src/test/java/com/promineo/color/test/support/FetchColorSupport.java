/**
 * 
 */
package com.promineo.color.test.support;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;

/**
 * @author pbuda
 *
 */
public class FetchColorSupport extends BaseTest{
  /**
   * @return
   */
  protected List<Color> buildExpected() {
    List<Color> list = new LinkedList<>();
    
    // @formatter: off
    list.add(Color.builder()
        .brandId(ColorBrand.NEVSKAYA_PALITRA)
        .typePaint("Watercolor")
        .basePrice(new BigDecimal("28.99"))
        .build());
    
    list.add(Color.builder()
        .brandId(ColorBrand.CRAYOLA)
        .typePaint("Watercolor")
        .basePrice(new BigDecimal("35.00"))
        .build());
    
    list.add(Color.builder()
        .brandId(ColorBrand.CARAN_DACHE)
        .typePaint("Watercolor Pencils Small")
        .basePrice(new BigDecimal("75.00"))
        .build());
    
    list.add(Color.builder()
        .brandId(ColorBrand.ARTEZA)
        .typePaint("Watercolor Small")
        .basePrice(new BigDecimal("75.99"))
        .build());
    
    list.add(Color.builder()
        .brandId(ColorBrand.VAN_GOGH)
        .typePaint("Watercolor")
        .basePrice(new BigDecimal("218.00"))
        .build());
    
    list.add(Color.builder()
        .brandId(ColorBrand.SCHMINCKE)
        .typePaint("Watercolor")
        .basePrice(new BigDecimal("410.00"))
        .build());
    
    // @formatter: on
    
    Collections.sort(list);
    return list;
  }
  
}
