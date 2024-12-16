package com.felipe.first_spring_app.controller;


import com.felipe.first_spring_app.model.ProprietarioModel;
import com.felipe.first_spring_app.service.ProprietarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }


    @PostMapping("/create")
    public ResponseEntity<ProprietarioModel> salvar(@RequestBody ProprietarioModel proprietario) {

        // Ver se tem os campos essenciais
        if (proprietario.getNome() == null || proprietario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome cannot be null or empty");
        }
        if (proprietario.getEmail() == null || proprietario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        // Validar o imovel se precisar
        if (proprietario.getImoveis() != null && !proprietario.getImoveis().isEmpty()) {
            // Exemplo de validação: verificar duplicatas ou outros critérios nos imóveis

        }

        // Salvar
        ProprietarioModel proprietarioSalvo = proprietarioService.salvar(proprietario);

        return new ResponseEntity<>(proprietarioSalvo, HttpStatus.CREATED);
    }


    // Lista de todos os proprietarios
    @GetMapping
    public List<ProprietarioModel> listarTodos() {
        return proprietarioService.listarTodos();
    }


    // Procurar um prorprietario por id
    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioModel> buscarPorId(@PathVariable Long id) {
        return proprietarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/proprietario/{id}")
    public ResponseEntity<ProprietarioModel> atualizarProprietario(@PathVariable Long id, @RequestBody ProprietarioModel proprietario) {
        return proprietarioService.buscarPorId(id)
                .map(proprietarioExistente -> {
                    proprietario.setId(id);
                    ProprietarioModel proprietarioAtualizado = proprietarioService.salvar(proprietario);
                    return ResponseEntity.ok(proprietarioAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    //Atualizar dados de um prorprietario
    @PutMapping("/update/{id}")
    public ResponseEntity<ProprietarioModel> update(@PathVariable Long id, @RequestBody ProprietarioModel proprietario) {
        return ResponseEntity.ok(ProprietarioService.update(id, proprietario));
    }


    // Deletar um proprietario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ProprietarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
