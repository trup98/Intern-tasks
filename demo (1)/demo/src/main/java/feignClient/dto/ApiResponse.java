package feignClient.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse {

    private int status;
    private String message;
    private Object data;

    public ApiResponse(int status, String message, Object data, Object notificationCount, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.notificationCount = notificationCount;
        this.httpStatus = httpStatus;
    }

    private Object notificationCount;
    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResponse(HttpStatus httpStatus, String message, Object data) {
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public ApiResponse(HttpStatus httpStatus, Object data) {
        this.status = httpStatus.value();
        this.data = data;

    }
}
