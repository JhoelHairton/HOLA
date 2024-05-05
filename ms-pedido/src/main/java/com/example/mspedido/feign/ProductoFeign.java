package com.example.mspedido.feign;

import com.example.mspedido.dto.Cliente;
import com.example.mspedido.dto.Producto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalogo-service", path = "/producto")
public interface ProductoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "productoListarPorIdCB", fallbackMethod = "fallbackProducto")

    public ResponseEntity<Producto> listById(@PathVariable(required = true) Integer id);
    default ResponseEntity<Producto> fallbackProducto(Integer id, Exception e) {
        // Lógica de fallback, por ejemplo, devolver una respuesta predeterminada
        return ResponseEntity.ok(new Producto());
    }

}