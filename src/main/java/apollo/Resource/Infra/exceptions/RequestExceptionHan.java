package apollo.Resource.Infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RequestExceptionHan {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<Map<String, String>>> handle400(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        Map<String, List<Map<String, String>>> errorsMap = new HashMap<>();
        List<Map<String, String>> errors = fieldErrors.stream().map(fieldError -> {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("field", fieldError.getField());
            errorMap.put("message", fieldError.getDefaultMessage());
            return errorMap;
        }).collect(Collectors.toList());

        errorsMap.put("errors", errors);
        return errorsMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handle404(EntityNotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        String message = exception.getMessage();
        errorMap.put("error", "Erro 404: Dado não encontrado.");
        if (message != null && !message.isEmpty()) {
            errorMap.put("details", message);
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class) // Tratamento genérico para outras exceções
    public Map<String, String> handle500(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "Erro 500: Ocorreu um erro interno no servidor.");
        errorMap.put("details", exception.getMessage()); // Pode ser removido ou personalizado conforme necessário
        return errorMap;
    }
}
