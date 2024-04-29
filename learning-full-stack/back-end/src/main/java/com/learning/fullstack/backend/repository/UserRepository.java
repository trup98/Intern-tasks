package com.learning.fullstack.backend.repository;

import com.learning.fullstack.backend.entity.UserEntity;
import com.learning.fullstack.backend.projection.GetAllUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  @Query(nativeQuery = true,
    value = "SELECT um.`first_ name` as firstName, um.id AS id, um.`last_name` AS lastName, um.gender AS gender, " +
            "um.email AS email, um.user_name AS userName, um.dob AS dob, " +
            "um.is_active as status, " +
            "GROUP_CONCAT(hm.name ORDER BY hm.name SEPARATOR ',') AS hobbyNames " +
            "FROM user_master um " +
            "JOIN user_hobby_mapping uhm ON um.id = uhm.user_id " +
            "JOIN hobby_master hm ON uhm.hobby_id = hm.id " +

            "WHERE (um.user_name LIKE CONCAT('%', :searchKey, '%') " +
            "OR um.last_name LIKE CONCAT('%', :searchKey, '%')  " +
            "OR um.email LIKE CONCAT('%', :searchKey, '%'))  " +
            "GROUP BY um.id ")
  Page<GetAllUserProjection> getAllUser(Pageable pageable, @Param("searchKey") String searchKey);


  @Query("SELECT um from UserEntity um WHERE um.id= :userID and um.isActive = TRUE ")
  Optional<UserEntity> findByIdAndIsActive(@Param("userID") Long id);

}
