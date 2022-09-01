package br.com.gestor.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import antlr.TokenStreamException;
import br.com.gestor.model.SegPerfilAplicacao;

@RestControllerAdvice
public class ApiError {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 409
	public String badRequest(SQLException mensagem ) {
		return mensagem.getMessage();
	}

	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) //404
	public String notFound(ResponseStatusException mensagem) {
		return mensagem.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String internalServerError(MethodArgumentNotValidException mensagem) {
		return mensagem.getMessage();
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public String internalServerError(HttpStatus badRequest, String erro) {
//		return "usuario nao encontrado";
//	}

	@ExceptionHandler(antlr.TokenStreamException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String unauthorized(TokenStreamException mensagem) {
		return mensagem.getMessage();
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) //405
	public String methodNotAllowed(HttpRequestMethodNotSupportedException mensagem) {
		return mensagem.getMessage();
	}


	public static ResponseEntity error(String mensagem, HttpStatus httpStatus) {
		Map<Object, Object> model = new HashMap<>();
		model.put("error", httpStatus.getReasonPhrase());
		model.put("status", httpStatus.value());
		model.put("message", mensagem);

		return new ResponseEntity(model, httpStatus);
	}

	

}
