package com.example.lab.spring.boot.mvc.spring.exception;

import com.example.lab.spring.boot.mvc.spring.controller.response.ResponsePayload;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandlers extends ResponseEntityExceptionHandler {

    // handle Spring MVC generated exceptions
    @Nullable
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex, Object body, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {

        if (request instanceof ServletWebRequest servletWebRequest) {
            HttpServletResponse response = servletWebRequest.getResponse();
            if (response != null && response.isCommitted()) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Response already committed. Ignoring: " + ex);
                }
                return null;
            }
        }

        if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
        }

        Object newBody = generateNewBody(ex);

        return createResponseEntity(newBody, headers, statusCode, request);
    }

    /**
     * Generate body based on whether ex is instance of ErrorResponse.
     *
     * <p>Only following exceptions are not instance of {@link ErrorResponse}</p>
     * <ul>
     *     <li>ConversionNotSupportedException.class</li>
     *     <li>TypeMismatchException.class</li>
     *     <li>HttpMessageNotReadableException.class</li>
     *     <li>HttpMessageNotWritableException.class</li>
     *     <li>BindException.class</li>
     * </ul>
     */
    private Object generateNewBody(Exception ex) {
        String code = null;
        String message;
        Map<String, Object> contentMap = null;
        if (ex instanceof ErrorResponse errorResponse) {
            ProblemDetail body = errorResponse.getBody();
            // TODO: not sure about what to present in code, might need to change in the future
            code = body.getTitle();
            message = body.getDetail();
            contentMap = body.getProperties();
        } else {
            message = ex.getLocalizedMessage();
        }
        return ResponsePayload.error(ResponsePayload.Status.build(code, message, contentMap));
    }
}
