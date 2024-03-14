package com.springmvc.jdbcnameparametertemplate.repository.impl;

import com.springmvc.jdbcnameparametertemplate.dto.UserRequestDto;
import com.springmvc.jdbcnameparametertemplate.model.UserResponseDTO;
import com.springmvc.jdbcnameparametertemplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public int saveUser(UserRequestDto userRequestDto) {
        String sql = "INSERT INTO user (first_name, last_name, email, password) VALUES (:firstName, :lastName, :email, :passWord)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstName", userRequestDto.getFirstName());
        params.addValue("lastName", userRequestDto.getLastName());
        params.addValue("email", userRequestDto.getEmail());
        params.addValue("passWord", userRequestDto.getPassWord());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(sql, params, keyHolder);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        String query = "SELECT Id as id,first_name as firstName,last_name as lastName,email as email,password as passWord From user ";
        return namedParameterJdbcTemplate.query(query, new BeanPropertyRowMapper<>(UserResponseDTO.class));

    }

    @Override
    public UserResponseDTO findUser(Long findById) {
        String sql = "SELECT * FROM user WHERE Id = :userId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", findById);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(UserResponseDTO.class));
    }

    @Override
    public void deleteUser(Long deleteId) {
        String query = "DELETE FROM user WHERE Id = :deleteId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("deleteId", deleteId);
        int num = namedParameterJdbcTemplate.update(query, parameterSource);
        System.out.println("Deleted = " + num);
    }

    @Override
    public int updateUser(UserRequestDto userRequestDto, Long updateId) {
        String query = "UPDATE user SET first_name=:firstName , last_name=:lastName , email=:email , password=:passWord WHERE ID=:updateId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("updateId", updateId);
        parameterSource.addValue("firstName", userRequestDto.getFirstName());
        parameterSource.addValue("lastName", userRequestDto.getLastName());
        parameterSource.addValue("email", userRequestDto.getEmail());
        parameterSource.addValue("passWord", userRequestDto.getPassWord());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(query, parameterSource, keyHolder);
    }
}
