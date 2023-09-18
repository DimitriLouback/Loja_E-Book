package br.edu.iff.bsi.LojaEBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;

@SpringBootApplication
public class LojaEBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaEBookApplication.class, args);
	}
	
	@Autowired
	public FuncionarioService FuncionarioServ;
	
	@EventListener(ApplicationReadyEvent.class)
	public void garantirADM() {
	    FuncionarioServ.garantirFuncionarioADM();
	}

}
