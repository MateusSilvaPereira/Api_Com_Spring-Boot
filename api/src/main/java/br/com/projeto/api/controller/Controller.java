package br.com.projeto.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repository.Repositorio;
import br.com.projeto.api.servico.servico;

@RestController
public class Controller {

    // ingeção de dependencia....
    @Autowired
    private Repositorio acao;

    @Autowired
    private servico servico;

    // Deleta por id
    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
    	return servico.remover(codigo);
    }

    // Contador de registro cadastrado no banco.....
    @GetMapping("/api/contador")
    public Long contador() {
        return acao.count();
    }

    // Ordena Por nomes...
    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes() {
        return acao.findByOrderByNome();
    }

    // Busca pelo id do o menor para maior

    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2() {
        return acao.findByNomeOrderByIdade("Mateus");
    }

    /*
     * Verifica e busca os nome que
     * contem uma serta letra ou termo
     */

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem() {
        return acao.findByNomeContaining("M");
    }

    // busca pela letra inicial...

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom() {
        return acao.findByNomeStartsWith("M");
    }

    // busca pela letra final....

    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom() {
        return acao.findByNomeEndsWith("S");
    }

    // Soma todas as idades cadastradas no banco

    @GetMapping("/api/somaIdade")
    public int somaIdade(){
        return acao.somaIdades();
    }

    // Busca por idades >= 20

    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(20);
    }


    // Editar....
    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj) {
        return servico.editar(obj);
    }

    // Buscar por id......
    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
        return servico.selecionarPeloCodigo(codigo);
    }

    // Buscar todos.....
    @GetMapping("/api")
    public ResponseEntity<?> selecionar() {
        return servico.selecionar();
    }

    // Cardastrar......
    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
        return servico.cadastrar(obj);
    }

    // test
    @GetMapping("")
    public String mensagem() {
        return "Hello World!";
    }

    // test
    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Seja bem vindo(a)";
    }

    // test
    @GetMapping("/boasVindas/{nome}")
    public String boasVidas(@PathVariable String nome) {
        return "Seja bem vindo(a) " + nome;
    }

    // test
    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }

    // ResponseEntity test

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj) {
    	
    }
}
