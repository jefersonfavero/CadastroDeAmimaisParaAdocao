package com.example.cadastroDeAnimaisParaAdocao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.cadastroDeAnimaisParaAdocao.model.Cadastro;
import com.example.cadastroDeAnimaisParaAdocao.repository.CadastroRepository;

@Controller
@RequestMapping("/listar")
public class CadastroController {
	
	@Autowired
	private CadastroRepository cadastroRepository;

	@GetMapping
	@ResponseBody
	public List<Cadastro> lista(){
		List<Cadastro> cadastro = cadastroRepository.findAll();
		return cadastro;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cadastro> detalhar(@PathVariable Long id) {

		Optional<Cadastro> cadastro = cadastroRepository.findById(id);
		if (cadastro.isPresent()) {
			return ResponseEntity.ok(cadastro.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Cadastro> cadastrar(@RequestBody Cadastro cadastro, UriComponentsBuilder uriBuilder) {
		cadastroRepository.save(cadastro);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(cadastro.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastro);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Cadastro> atualizar(@PathVariable Long id, @RequestBody Cadastro cadastro) {
		Optional<Cadastro> optional = cadastroRepository.findById(id);
		if (optional.isPresent()) {
			@SuppressWarnings("deprecation")
			Cadastro cadastro1 = cadastroRepository.getOne(id);
			cadastro1.setNome(null);
			return ResponseEntity.ok(cadastro);
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Cadastro> optional = cadastroRepository.findById(id);
		if (optional.isPresent()) {
			cadastroRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}
	
}
