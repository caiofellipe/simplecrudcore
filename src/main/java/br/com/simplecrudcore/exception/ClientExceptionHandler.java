package br.com.simplecrudcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.simplecrudcore.dto.ErrorDTO;

@ControllerAdvice
public class ClientExceptionHandler {
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ErrorDTO> clientException(ClientException exception){
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
		
	}
}
