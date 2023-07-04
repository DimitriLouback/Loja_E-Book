package br.edu.iff.bsi.LojaEBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;

@Repository
public interface Colecao_E_BookRepository extends JpaRepository<Colecao_E_Book, Long> {

}
