package com.springmvc.swaggerui.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contains All the attribute required under Entity")
public class UserRequestDto {
    private String userName;
    private String lastName;
    private String email;
}
