/**
 * 
 */
package com.promineo.color.dao;



import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pbuda
 *
 */
@Slf4j
@Component
public class DefaultColorSalesDao implements ColorSalesDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Color> fetchColors(ColorBrand brand, String type) {
   log.debug("DAO: brand = {}, type = {}", brand, type);
   
   // @formatter: off
   String sql = ""
       + "SELECT * "
       + "FROM brands "
       + "WHERE brand_id = :brand_id AND type_paint = :type_paint";
   // @formatter: on
   
   Map<String, Object> params = new HashMap<>();
   params.put("brand_id", brand.toString());
   params.put("type_paint", type);
   
   return jdbcTemplate.query(sql, params,
       new RowMapper<>() {

   @Override
   public Color mapRow(ResultSet rs, int rowNum) throws SQLException {
     //formatter: off
     return Color.builder()
         .basePrice(new BigDecimal(rs.getString("base_price")))
         .brandId(ColorBrand.valueOf(rs.getString("brand_id")))
         .brandPK(rs.getLong("brand_pk"))
         .typePaint(rs.getString("type_paint"))
         .build();
   } });
  
  
  
  
  
  
  
  
  }  
}
