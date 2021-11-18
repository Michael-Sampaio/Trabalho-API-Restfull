package br.org.serratec.backend.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Metodo responsável para tratar qualquer exception do tipo
     * ResourceBadRequestException em todo o projeto.
     * 
     * @param exception Exception criada para trabalhar com erros por parte do
     *                  cliente.
     * @return Erro personalizado do projeto.
     */
    @ExceptionHandler(RecursoBadRequestException.class)
    public ResponseEntity<?> handlerResourceBadRequestException(RecursoBadRequestException badException) {

        String dataHoraAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());

        RespostaErro erro = new RespostaErro("Bad Request", HttpStatus.BAD_REQUEST.value(), badException.getMessage(),
                badException.getClass().getName(), dataHoraAtual);

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(RecursoNotFoundException notFoundException) {

        String dataHoraAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());

        RespostaErro erro = new RespostaErro("Not found", HttpStatus.NOT_FOUND.value(), notFoundException.getMessage(),
                notFoundException.getClass().getName(), dataHoraAtual);

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception exception) {

        String dataHoraAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());

        RespostaErro erro = new RespostaErro("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(), exception.getClass().getName(), dataHoraAtual);

        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
