/**
 * 
 */
package com.promineo.color.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.promineo.color.entity.ColorBrand;
import com.promineo.color.entity.Order;
import com.promineo.color.test.support.OrderTestSupport;

/**
 * @author pbuda
 *
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
    "classpath:flyway/migrations/Script-4.sql",
    "classpath:flyway/migrations/Script-3.sql"},
    config = @SqlConfig(encoding = "UTF-8"))

class CreateOrderTest extends OrderTestSupport{

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  
  @Test
  void testCreateOrderReturnsSuccess201() {
    // Given: an order of JSON
   String body =  createOrderBody();
   String uri = getBaseUriForOrders();
   
   int numRowsOrders = JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders");
   int numRowsOptions = JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options");
   
   HttpHeaders headers = new HttpHeaders();
   headers.setContentType(MediaType.APPLICATION_JSON);
   
   HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
   
    // When: the order is sent
     ResponseEntity<Order> response = getRestTemplate().exchange
         (uri, HttpMethod.POST, bodyEntity, Order.class);
   
    // Then: a 201 status is returned
     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
   
   // And: the returned order is correct
     assertThat(response.getBody()).isNotNull();
     
     Order order = response.getBody();
     
     assertThat(order.getCustomer().getCustomerId()).isEqualTo("PETROV_ALEX");
     assertThat(order.getBrand().getBrandId()).isEqualTo(ColorBrand.NEVSKAYA_PALITRA);
     assertThat(order.getBrand().getTypePaint()).isEqualTo("Watercolor");
     assertThat(order.getOptions()).hasSize(5);
   
     assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders"))
     .isEqualTo(numRowsOrders + 1);
     assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options"))
     .isEqualTo(numRowsOptions + 5);
  }

 
}
