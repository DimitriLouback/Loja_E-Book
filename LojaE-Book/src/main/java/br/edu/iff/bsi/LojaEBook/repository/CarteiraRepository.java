package br.edu.iff.bsi.LojaEBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Carteira;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}
