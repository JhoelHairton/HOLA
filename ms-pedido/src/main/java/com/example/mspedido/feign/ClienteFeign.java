package com.example.mspedido.feign;

import com.example.mspedido.dto.Cliente;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente-service", path = "/cliente")
public interface ClienteFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteListarPorIdCB", fallbackMethod = "fallbackCliente")

    public ResponseEntity<Cliente> listById(@PathVariable(required = true) Integer id);

    default ResponseEntity<Cliente> fallbackCliente(Integer id, Exception e) {
        // Lógica de fallback, por ejemplo, devolver una respuesta predeterminada
        return ResponseEntity.ok(new Cliente());
    }
    }