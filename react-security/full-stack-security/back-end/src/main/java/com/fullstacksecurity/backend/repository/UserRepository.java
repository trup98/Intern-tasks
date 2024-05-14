package com.fullstacksecurity.backend.repository;

import com.fullstacksecurity.backend.entity.UserEntity;
import com.fullstacksecurity.backend.projection.GetAllUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String username);

  /*<>This method is native sql query,
   getting data from sql and storing them in class-based projection<>
   * */
  @Query(nativeQuery = true, value = "SELECT " +
    "um.id AS id, " +
    "um.is_active AS status, " +
    "um.user_name AS userName, " +
    "um.user_email AS email, " +
    "ud.gender AS gender, " +
    "ud.first_name AS firstName, " +
    "ud.address AS address, " +
    "ud.dob AS dateOfBirth, " +
    "ud.last_name AS lastName, " +
    "GROUP_CONCAT(rm.`role` SEPARATOR ',') AS roleNames " +
    "FROM " +
    "`react-security`.user_master um " +
    "JOIN " +
    "`react-security`.user_role_mapping urm ON um.id = urm.user_id " +
    "JOIN " +
    "`react-security`.role_master rm ON urm.role_id = rm.id " +
    "JOIN " +
    "`react-security`.user_details ud ON um.id = ud.user_id " +
    "WHERE " +
    "um.is_delete = FALSE " +
    "AND ( " +
    "ud.first_name  LIKE CONCAT('%', :searchKey, '%') " +
    "OR ud.last_name  LIKE CONCAT('%', :searchKey, '%') " +
    "OR um.user_email  LIKE CONCAT('%', :searchKey, '%') " +
    ") " +
    "GROUP BY um.id")
  Page<GetAllUserDetails> findAllUserDetail(Pageable pageable, @Param("searchKey") String searchKey);


}
