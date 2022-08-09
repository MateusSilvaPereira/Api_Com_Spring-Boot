package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repository.Repositorio;

@Service
public class servico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // Metodo para Cadastrar Pessoas
    public ResponseEntity<?> cadastrar(Pessoa obj) {

        if (obj.getNome().equals("")) {

            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        } else if (obj.getIdade() <= 0) {

            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // Metodo para selecionar Pessoas

    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Metodo para selecionar Pessoas através do codigo
    public ResponseEntity<?> selecionarPeloCodigo(int codigo) {

        if (acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("Não foi encontrado nehuma pessoa");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }
    // Metodo para editar todos

    public ResponseEntity<?> editar(Pessoa obj) {

        if (acao.countByCodigo(obj.getCodigo()) == 0) {
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

        } else if (obj.getNome().equals("")) {

            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() <= 0) {

            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }
    
 // Metodo para reover registros
    
    public ResponseEntity<?> remover(int codigo){
    	
    	if(acao.countByCodigo(codigo) == 0) {
    		mensagem.setMensagem("O codigo informado não existe");
    		return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    	}else {
    		Pessoa obj = acao.findByCodigo(codigo);
    		acao.delete(obj);
    		
    		mensagem.setMensagem("Pessoa removida com sucesso");
    		return new ResponseEntity<>(mensagem, HttpStatus.OK);
    	}
    }
}
