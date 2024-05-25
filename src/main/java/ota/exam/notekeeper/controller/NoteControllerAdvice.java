package ota.exam.notekeeper.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ota.exam.notekeeper.exception.BaseExceptionResponse;
import ota.exam.notekeeper.exception.NoteNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static ota.exam.notekeeper.common.Constants.ERROR_MSG_REQUEST_PROBLEM;
import static ota.exam.notekeeper.common.Constants.STATUS_BAD_REQUEST;
import static ota.exam.notekeeper.common.Constants.STATUS_NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class NoteControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error(ERROR_MSG_REQUEST_PROBLEM + errors);
        return ResponseEntity.badRequest().body(BaseExceptionResponse.builder()
                .status(STATUS_BAD_REQUEST)
                .message(ERROR_MSG_REQUEST_PROBLEM)
                .errors(errors)
                .build());
    }

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> handleNoteNotFoundException(NoteNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseExceptionResponse.builder()
                        .status(STATUS_NOT_FOUND)
                        .message(ex.getMessage())
                        .build());
    }
}
