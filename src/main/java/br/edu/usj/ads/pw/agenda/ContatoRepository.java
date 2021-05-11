package br.edu.usj.ads.pw.agenda;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato, Long> {
    List<Contato> findAll();
}
