package com.example.TrelloClone.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.TrelloClone.Helpers.User;

/**
 * Repository interface for CRUD operations related to the User entity.
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Fetches the name of the user with the given suid.
     * 
     * @param suid the suid of the user.
     * @return the name of the user.
     */
    @Query(value = "SELECT u.name FROM user u WHERE u.id = :suid", nativeQuery = true)
    String findUserNameBySuid(@Param("suid") Long suid);
}
