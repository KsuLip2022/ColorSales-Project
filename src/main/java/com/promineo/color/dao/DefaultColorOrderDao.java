/**
 * 
 */
package com.promineo.color.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;
import com.promineo.color.entity.Customer;
import com.promineo.color.entity.Option;
import com.promineo.color.entity.OptionType;
import com.promineo.color.entity.Order;
import lombok.Data;



/**
 * @author pbuda
 *
 */
@Component
@Data
public class DefaultColorOrderDao implements ColorOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate; 
  
  @Override
  public Order saveOrder(Customer customer, Color color, BigDecimal price, List<Option> options) {
   
  
  SqlParams params = 
      generateInsertSql(customer, color, price);
  
  KeyHolder keyHolder = new GeneratedKeyHolder();
  jdbcTemplate.update(params.sql, params.source, keyHolder);
  
  Long orderPK = keyHolder.getKey().longValue();
  saveOptions(options, orderPK);
  
  //@formatter:0ff
  return Order.builder()
      .orderPK(orderPK)
      .customer(customer)
      .brand(color)
      .options(options)
      .price(price)
      .build();
  //@formatter:on
  }  

/**
* @param options
* @param orderPK
*/
private void saveOptions(List<Option> options, Long orderPK) {
 for(Option option: options) {
   SqlParams params = generateInsertSql(option, orderPK);
   jdbcTemplate.update(params.sql, params.source);
 }   
}
/**
* 
* @param option
* @param orderPK
* @return
*/
private SqlParams generateInsertSql(Option option, Long orderPK) {
 SqlParams params = new SqlParams();
 
 // @formatter:off
 params.sql = ""
     + "INSERT INTO order_options ("
     + "option_fk, order_fk"
     + ") VALUES ("
     + ":option_fk, :order_fk"
     + ")";
 // @formatter:on
 
 params.source.addValue("option_fk", option.getOptionPK());
 params.source.addValue("order_fk", orderPK);
 
 return params;
}

/**
* 
* @param customer
* @param color
* @param price
* @return
*/
private SqlParams generateInsertSql(Customer customer, Color color, BigDecimal price) {
 // @formatter:off
 String sql = ""
     + "INSERT INTO orders ("
     + "customer_fk, brand_fk, price"
     + ") VALUES ("
     + ":customer_fk, :brand_fk, :price"
     + ")";
 // @formatter:on
 
 SqlParams params = new SqlParams();
 
 params.sql = sql;
 params.source.addValue("customer_fk", customer.getCustomerPK());
 params.source.addValue("brand_fk", color.getBrandPK());
 params.source.addValue("price", price);
 
 return params;
}
  
  
  
  @Override
  public List<Option> fetchOptions(List<String> optionIds) {
    if (optionIds.isEmpty()) {
      return new LinkedList<>();
    }
    Map<String, Object> params = new HashMap<>();
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM options "
        + "WHERE option_id IN(";
    // @formatter:on

    for (int index = 0; index < optionIds.size(); index++) {
      String key = "option_" + index;
      sql += ":" + key + ", ";
      params.put(key, optionIds.get(index));
    }

    sql = sql.substring(0, sql.length() - 2);
    sql += ")";
    
    return jdbcTemplate.query(sql, params, new RowMapper<Option>() {

      @Override
      public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        // @formatter:off
        return Option.builder()
            .category(OptionType.valueOf(rs.getString("category")))
            .manufacturer(rs.getString("manufacturer"))
            .name(rs.getString("name"))
            .optionId(rs.getString("option_id"))
            .optionPK(rs.getLong("option_pk"))
            .price(rs.getBigDecimal("price"))
            .build();
        // @formatter:on
      }});
  }  
  
  @Override
  public Optional <Customer> fetchCustomer(String customerId) {
    String sql = ""
        + "SELECT * "
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
 
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
  }
  
  class CustomerResultSetExtractor implements ResultSetExtractor<Customer>{
 
      @Override
      public Customer extractData(ResultSet rs) 
          throws SQLException, DataAccessException {
        rs.next();
        // @formatter:off
        return Customer.builder()
            .customerId(rs.getString("customer_id"))
            .customerPK(rs.getLong("customer_pk"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .phone(rs.getString("phone"))
            .build();
        // @formatter:on
      }  
  }
  @Override
  public Optional<Color> fetchBrand(ColorBrand brand, String type) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM brands "
        + "WHERE brand_id = :brand_id "
        + "AND type_paint = :type_paint";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("brand_id", brand.toString());
    params.put("type_paint", type);
 

    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new BrandResultSetExtractor()));
  }

  /**
   * 
   */
  class BrandResultSetExtractor implements ResultSetExtractor<Color> {
    @Override
    public Color extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Color.builder()
          .basePrice(rs.getBigDecimal("base_price"))
          .brandId(ColorBrand.valueOf(rs.getString("brand_id")))
          .brandPK(rs.getLong("brand_pk"))
          .typePaint(rs.getString("type_paint"))
          .build();
      // @formatter:on
    }
  }
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
  
  @Override
  public boolean deleteOption(String optionId) {
   String sql = """
           DELETE
           FROM options
           WHERE option_id = :option_id
           """;
   Map<String, Object> params = Map.of("option_id", optionId);
    return jdbcTemplate.update(sql, params) ==1;
  }

  @Override
  public boolean updateOption(Option option) {
    String sql = """
            UPDATE options SET
            option_id = :option_id,
            category = :category,
            manufacturer = :manufacturer,
            name = :name,
            price = :price
            WHERE option_pk = :option_pk
            """;
    
    // @formatter:off
    Map<String, Object> params =
        Map.of(
            "option_id", option.getOptionId(),
            "category", option.getCategory().toString(),
            "manufacturer", option.getManufacturer(),
            "name", option.getName(),
            "price", option.getPrice(),
            "option_pk", option.getOptionPK()
            );
    // @formatter:on
        
    return jdbcTemplate.update(sql, params) ==1;
  }
  
 
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

