package com.user.basicusermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.basicusermanagement.model.User;
import java.util.List;
import java.util.Optional;


/**
 * This is the repository Layer controlling the database with the use of
 * Spring Data JPA and Hibernate. SQL statement in the following will not
 * be called as to the consideration of the BigO efficiency (just for reference).
 * 
 * Reason: Data extraction in the database is in synnchronized which may slow down the
 * performance of the application
 * 
 * Solution: use findAll() to extract data in the service layer and then use
 * stream and filtering to extract the data needed
 */
public interface UserRepository extends JpaRepository<User,Long>{

  //Hibernate SQL convertion help us to generate the SQL statement according to which database Driver using
  // select * from Users where username = username
  Optional<User> findByUsername (String username);

  //Native Query
  @Query(nativeQuery = true,
  value = "SELECT * FROM USERS U WHERE U.ROLE = :role")
  List<User> findByRole(@Param(value = "role") String role);

  //JPQL
  @Query(value = "select u.* from Users u where u.gender =:gender")
  List<User> findByGender(@Param(value = "gender") String gender);

  
}
