package com.vendas.api_vendas.repository;

import com.vendas.api_vendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
