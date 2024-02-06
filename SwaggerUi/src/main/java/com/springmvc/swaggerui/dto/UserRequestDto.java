package com.springmvc.swaggerui.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contains All the attribute required under Entity")
public class UserRequestDto {
    private String userName;
    private String lastName;
    private String email;
}
