package com.example.cadastroDeAnimaisParaAdocao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastroDeAnimaisParaAdocao.model.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Long>{

}
