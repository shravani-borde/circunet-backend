package com.circunet.exception;
import org.springframework.dao.DuplicateKeyException; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestControllerAdvice public class GlobalExceptionHandler {
 @ExceptionHandler(ApiException.class) ResponseEntity<?> api(ApiException e){return ResponseEntity.status(e.status()).body(Map.of("timestamp",Instant.now(),"status",e.status().value(),"error",e.status().getReasonPhrase(),"message",e.getMessage(),"fieldErrors",List.of()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> validation(MethodArgumentNotValidException e){var fs=e.getBindingResult().getFieldErrors().stream().map(x->Map.of("field",x.getField(),"message",Objects.toString(x.getDefaultMessage(),"invalid"))).toList();return ResponseEntity.badRequest().body(Map.of("timestamp",Instant.now(),"status",400,"error","Bad Request","message","Validation failed","fieldErrors",fs));}
 @ExceptionHandler(DuplicateKeyException.class) ResponseEntity<?> duplicate(Exception e){return ResponseEntity.status(409).body(Map.of("timestamp",Instant.now(),"status",409,"error","Conflict","message","Duplicate value","fieldErrors",List.of()));}
 @ExceptionHandler(Exception.class) ResponseEntity<?> other(Exception e){return ResponseEntity.status(400).body(Map.of("timestamp",Instant.now(),"status",400,"error","Bad Request","message",Objects.toString(e.getMessage(),"Request failed"),"fieldErrors",List.of()));}
}
