package com.xbrain.exceptionhandler;

import com.xbrain.domain.exception.EntidadeNaoEncontradaException;
import com.xbrain.domain.exception.NegocioException;
import com.xbrain.enums.ProblemType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static String MSG_ERRO_DE_SINTAXE = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.MSG_INCOPREENSIVEL;
        String detail = MSG_ERRO_DE_SINTAXE;
        Problem problem = createProblemBuilder(status,problemType,detail).build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(),status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException exception, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.MSG_ENTIDADE_NAO_ENCONTRADA;
        String detail = exception.getMessage();
        Problem problem = createProblemBuilder(status,problemType,detail).build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(),
                status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(EntidadeNaoEncontradaException exception, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MSG_ERRO_NEGOCIO;
        String detail = exception.getMessage();
        Problem problem = createProblemBuilder(status,problemType,detail).build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(),
                status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType,String detail){
        return  Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
