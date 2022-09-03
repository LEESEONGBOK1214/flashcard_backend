package com.teosprint.flashcard.config.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;


@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());


//    @ExceptionHandler(TokenExpiredException.class)
//    public ResponseEntity<ApiErrorResponse> tokenExpiredException(TokenExpiredException ex) {
//        ApiErrorResponse response
//                = ApiErrorResponse
//                .create()
//                .status(400)
//                .code("error-jwt-400")
//                .message(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(TokenIsNotValidException.class)
//    public ResponseEntity<ApiErrorResponse> tokenIsNotValidException(TokenIsNotValidException ex) {
//        ApiErrorResponse response
//                = ApiErrorResponse
//                .create()
//                .status(400)
//                .code("error-jwt-400")
//                .message(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }



    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(400)
                .code("error-miss match method")
                .message("맞는 요청을 찾지 못 했습니다. 요청 Method 및 URL을 확인해주세요.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "request body를 확인해주세요.";
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ifx = (InvalidFormatException) ex.getCause();
            if (ifx.getTargetType()!=null && ifx.getTargetType().isEnum()) {
                errorMessage = String.format("Invalid enum value: '%s' for the field: '%s'. The value must be one of: %s.",
                        ifx.getValue(), ifx.getPath().get(ifx.getPath().size()-1).getFieldName(), Arrays.toString(ifx.getTargetType().getEnumConstants()));
            }
        }

        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(400)
                .code("error-request-body-missing-400")
                .message(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(400)
                .code(errorMessage)
                .message(e.toString());


        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ApiErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
        logger.error("handleInvalidParameterException", e);
        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(400)
                .code(e.getCode())
                .message(e.toString())
                .errors(e.getErrors());
        return new ResponseEntity<>(response, HttpStatus.resolve(response.getStatus()));
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> DataIntegrityViolationException(DataIntegrityViolationException ex) {
        logger.error(ex.getMessage());
        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(400)
                .code("error-value-valid")
                .message("데이터가 제약조건 위반됩니다."+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BasicException.class)
    public ResponseEntity<ApiErrorResponse> unauthorizedException(BasicException ex) {
        ex.printStackTrace();
        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(ex.getStatus())
                .code(ex.getCode())
                .message(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.resolve(ex.getStatus()));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiErrorResponse> illegalArgumentExceptionhandleException(IllegalArgumentException e) {
        logger.error("handleException...", e);
        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(500)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("서버 오류");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiErrorResponse> handleException(Exception e) {
        logger.error("Exception Handler - Exception.class", e);
        ApiErrorResponse response
                = ApiErrorResponse
                .create()
                .status(Integer.parseInt(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(e.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
