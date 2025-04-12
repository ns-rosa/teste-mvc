package com.teste.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiro_exemplo.model.Produto;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiro_exemplo.repository.ProdutoRepository;
import com.teste.primeiro_exemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {
     
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> obterTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());

    };

    public Optional<ProdutoDTO> obterPorId(Integer id){
        // obtendo Optional pelo Id
        Optional<Produto> produto = produtoRepository.findById(id);

        // se não encontrar, lança Exception
        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto ID: " + id + " não encontrado.");
        } 

        // convertendo meu Produto para ProdutoDTO
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        // criando e retornando um Optional do DTO
        return Optional.of(dto);

    }
    
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        produtoDto.setId(null);

        // criando um obj de mapeamento
        ModelMapper mapper = new ModelMapper();

        // converter o produtoDto para Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // salvar o produto no banco
        produto = produtoRepository.save(produto);

        // retornar o produtoDto atualizado para o usuário
        produtoDto.setId(produto.getId());

        return produtoDto;
    }

    public void deletar(Integer id){
        // verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);

        // se não existir, lança exception
        if (produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o produto com o ID: " + id + " pois o produto não existe.");
        }     

        // se existir deleta
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        // passar o id para o produtoDto
        produtoDto.setId(id);

        // criar objeto para mapeamento
        ModelMapper mapper = new ModelMapper();

        // converte o produtoDto para Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // fazer a atualização e salvar em produto
        produtoRepository.save(produto);

        return produtoDto;
    }
}
