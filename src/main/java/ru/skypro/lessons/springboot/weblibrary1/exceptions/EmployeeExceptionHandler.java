package ru.skypro.lessons.springboot.weblibrary1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.io.IOException;
import java.util.List;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleIOException(IOException ioException) {
        String message = "Введеный ID не найден";
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
         String message = "Ошибка приложения";
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}
