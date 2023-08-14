package com.mballem.curso.jasper.spring.repository;

import com.mballem.curso.jasper.spring.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findById(Long Id);
}
