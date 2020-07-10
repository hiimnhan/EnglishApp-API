package com.group1.EnglishApp.repository;

import com.group1.EnglishApp.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Hai Dang
 */
@Repository
public interface UserRepository extends GenericRepository<User, Long>{
    User findUserByUsername(String username);

}
