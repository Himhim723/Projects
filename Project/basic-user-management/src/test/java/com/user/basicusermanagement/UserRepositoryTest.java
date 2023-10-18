package com.user.basicusermanagement;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import com.user.basicusermanagement.config.TestDatabaseConfig;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.repository.UserRepository;

/**
 * @Danger Not YET Completed
 */

@Import(TestDatabaseConfig.class)
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=update"})
@TestMethodOrder(OrderAnnotation.class)
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TestEntityManager entityManager;

  @Order(1)
  @Test
  void testFindByUsername(){
    User user1 = User.builder().userID(1L).username("user001").password("Admin1234")
    .fullName("User1").gender("M").dob(LocalDate.of(2010, 11, 12)).email("abc123@gmail.com")
    .address("ABC Building").contact("+852 81118555").role("Admin").regDateTime(LocalDateTime.now()).build();
    
    //userRepository.save(user1);
    // entityManager.find(User.class,user1);

    assertThat(user1, (hasProperty("password", equalTo("Admin1234"))));
    
  
  }
}
