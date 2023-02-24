package com.spring.basics.exception;
import com.spring.basics.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class BookExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        List<ObjectError> errorList= ex.getBindingResult().getAllErrors();
        List<String> errMesg =  errorList
                                .stream()
                                .map(objErr -> objErr.getDefaultMessage())
                                .collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception While processing REST Request", errMesg.toString());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseDTO> handleEmployeePayrollException(BookException ex)
    {
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request",ex.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }
}
