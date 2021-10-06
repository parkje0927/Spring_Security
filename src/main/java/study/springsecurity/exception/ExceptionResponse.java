package study.springsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    /**
     * 예외가 발생한 시간
     * 예외가 발생한 메세지
     * 예외가 발생한 내용
     */
    private Date timeStamp;
    private String message;
    private String details;
}
