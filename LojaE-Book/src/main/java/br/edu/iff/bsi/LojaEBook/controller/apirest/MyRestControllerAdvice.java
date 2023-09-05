package br.edu.iff.bsi.LojaEBook.controller.apirest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.iff.bsi.LojaEBook.exception.ErrorInfo;
import jakarta.servlet.http.HttpServletRequest;

public class MyRestControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURI(),ex);
	}
	
}
