package com.vendas.api_vendas.repository;

import com.vendas.api_vendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoService extends JpaRepository<Produto, Long> { }
