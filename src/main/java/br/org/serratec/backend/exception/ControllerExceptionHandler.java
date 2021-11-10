package br.org.serratec.backend.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(RecursoBadRequestException.class)
    public ResponseEntity<?> handleRecursoBadRquestException(RecursoBadRequestException rex) {
        String dataHoraAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());

        RespostaErro erro = new RespostaErro(
            "Bad Request",
            HttpStatus.BAD_REQUEST.value(),
            rex.getClass().getName(),
            dataHoraAtual);

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
    
}
