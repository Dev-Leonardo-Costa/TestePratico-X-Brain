package com.xbrain.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.xbrain.domain.exception.EntidadeNaoEncontradaException;
import com.xbrain.domain.exception.NegocioException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static String MSG_ERRO_DE_SINTAXE = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(exception);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.MSG_INCOPREENSIVEL;
        String detail = MSG_ERRO_DE_SINTAXE;
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(), status, request);
    }


//    private String joinPath(List<Reference> references) {
//        return references.stream()
//                .map(ref -> ref.getFieldName())
//                .collect(Collectors.joining("."));
//    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.MSG_RECURSO_NAO_ENCONTRADO;
        String detail = exception.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(EntidadeNaoEncontradaException exception, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.MSG_ERRO_NEGOCIO;
        String detail = exception.getMessage();
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(),
                status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindiResult = exception.getBindingResult();
        List<Problem.Field> problemFields = bindiResult.getFieldErrors().stream()
                .map(fieldErros -> Problem.Field.builder()
                        .name("bbbbbbbbb")
                        .userMessage("aaaaaa")
                        .build())
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .timestamp(LocalDateTime.now())
                .userMessage(detail)
                .fields(problemFields)
                .build();

        return handleExceptionInternal(exception, problem, headers, status, request);
    }


    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException exception,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = exception.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MSG_INCOPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, exception.getValue(), exception.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(exception, problem, headers, status, request);
    }


}
