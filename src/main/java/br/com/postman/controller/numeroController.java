package br.com.postman.controller;

import br.com.postman.entity.numero;
import br.com.postman.repository.numeroRepository;
import br.com.postman.service.numeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api")
public class numeroController {

    @Autowired
    private numeroRepository numeroRep;

    @Autowired
    private numeroService numeroserv;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id) {
        final numero numero = this.numeroRep.findById(id).orElse(null);
        return ResponseEntity.ok(numero);
    }


    @GetMapping("/calculo")
    public ResponseEntity<String> calcular() {
        String estatisticasFormatadas = numeroserv.calcularEstatisticas();
        return ResponseEntity.ok(estatisticasFormatadas);
    }


    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.numeroRep.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final numero numero) {
        try {
            this.numeroRep.save(numero);

            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable("id") final Long id,
            @RequestBody final numero numero
    ) {
        try {
            this.numeroRep.save(numero);
            return ResponseEntity.ok("Registro atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ) {
        try {
            this.numeroRep.deleteById(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}
