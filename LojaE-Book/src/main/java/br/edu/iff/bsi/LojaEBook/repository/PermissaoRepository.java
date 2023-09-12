package br.edu.iff.bsi.LojaEBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.E_Book;

@Repository
public interface PermissaoRepository extends JpaRepository<E_Book, Long> {

}
