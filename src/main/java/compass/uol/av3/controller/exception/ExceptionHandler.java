package compass.uol.av3.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import compass.uol.av3.services.exception.ObjectNotFoundException;
import compass.uol.av3.services.exception.TempFoundationException;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(TempFoundationException.class)
	public ResponseEntity<StandardError> tempFoundation(TempFoundationException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Inválido",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
