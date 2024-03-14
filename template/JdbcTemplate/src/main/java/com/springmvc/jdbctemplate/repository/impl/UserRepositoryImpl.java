package com.springmvc.jdbctemplate.repository.impl;

import com.springmvc.jdbctemplate.dto.UserRequestDto;
import com.springmvc.jdbctemplate.model.UserResponseDTO;
import com.springmvc.jdbctemplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public String saveUser(UserRequestDto userRequestDto) {
        String query = "INSERT INTO user" + "(first_name, last_name, email, password)" + "VALUES(?,?,?,?);";
        jdbcTemplate.update(query, userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                userRequestDto.getEmail(),
                userRequestDto.getPassWord());
        return "Added";
    }

    @Override
    public List<UserResponseDTO> findAll() {
        String query = "SELECT Id as id,first_name as firstName,last_name as lastName,email as email,password as passWord From user ";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(UserResponseDTO.class));

/*        RowMapper<UserResponseDTO> rowMapper = (rs, rowNum) -> new UserResponseDTO(
                rs.getLong("Id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("passWord"));
        return jdbcTemplate.query(query, rowMapper);*/
/*
        Second Way
        return jdbcTemplate.query(query,(rs, rowNum) -> new UserEntity(rs.getLong("Id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("passWord")));
*/
    }

    @Override
    public UserResponseDTO findUser(Long findById) {
        String query = "SELECT Id as id,first_name as firstName,last_name as lastName,email as email,password as passWord From user WHERE Id= " + findById;
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(UserResponseDTO.class));

       /* String query = "SELECT * FROM user WHERE Id=?";
        return jdbcTemplate.query(query, (rs, rowNum) -> new UserResponseDTO(
                rs.getLong("Id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("passWord")
        ), findById);*/
    }

    @Override
    public void deleteUser(Long deleteId) {
        String query = "DELETE FROM user WHERE Id=?";
        jdbcTemplate.update(query, deleteId);
    }

    @Override
    public int updateUser(UserRequestDto userRequestDto, Long updateId) {
        String query = "UPDATE user SET first_name=? , last_name=? , email=? , password=? WHERE ID=?";
        return jdbcTemplate.update(query, userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                userRequestDto.getEmail(),
                userRequestDto.getPassWord(), updateId);

    }
}
