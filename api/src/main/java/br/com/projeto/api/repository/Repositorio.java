package br.com.projeto.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {
    List<Pessoa> findAll();

     // busca pelo id
    Pessoa findByCodigo(int codigo);

     // busca pela order dos nomes
    List<Pessoa> findByOrderByNome();

    // busca por id maior (findByNomeOrderByIdadeDesc) /
    //ou pelo menor (findByNomeOrderByIdade)...
    
    List<Pessoa> findByNomeOrderByIdade(String nome);
    
    /*Verifica e busca os nome que
    contem uma serta letra ou termo*/ 

    List<Pessoa> findByNomeContaining(String termo);

    /*Verifica e busca um nome que inicia 
    com certa letra passada como parametro*/

    List<Pessoa> findByNomeStartsWith(String termo);

    /*Verifica e busca um nome que finaliza 
    com certa letra passada como parametro*/

    List<Pessoa> findByNomeEndsWith(String termo);

    @Query(value= "SELECT SUM(idade) FROM pessoas", nativeQuery = true)
    int somaIdades();

    //busca por idade maior ou igual >= 
    
    @Query(value = "SELECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int idade);

    // contador de id no banco de dados cadastrados
    int countByCodigo(int codigo);

}
