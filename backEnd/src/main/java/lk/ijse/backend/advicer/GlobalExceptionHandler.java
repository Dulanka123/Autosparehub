package lk.ijse.backend.advicer;


import lk.ijse.backend.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidation(MethodArgumentNotValidException exception){
        Map<String ,String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(),"Validation Error", errors);
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseDTO> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(new ResponseDTO(HttpStatus.PAYLOAD_TOO_LARGE.value(), "File size exceeds the maximum limit!", null));
    }
}
