package com.felipe.first_spring_app.controller;


import com.felipe.first_spring_app.model.InquilinoModel;
import com.felipe.first_spring_app.service.InquilinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/inquilino")
public class InquilinoController {


    @Autowired
    private InquilinoService inquilinoService;


    // Criar um inquilino
    @PostMapping("/create")
    public InquilinoModel salvar(@RequestBody InquilinoModel inquilino) {
        return inquilinoService.salvar(inquilino);
    }


    // Listar todos os inquilinos
    @GetMapping
    public List<InquilinoModel> listarTodos() {
        return inquilinoService.listarTodos();
    }


    //Procurar inquilino por id
    @GetMapping("/{id}")
    public ResponseEntity<InquilinoModel> buscarPorId(@PathVariable Long id) {
        return inquilinoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/inquilino/{id}")
    public ResponseEntity<InquilinoModel> atualizarInquilino(@PathVariable Long id, @RequestBody InquilinoModel inquilino) {
        return inquilinoService.buscarPorId(id)
                .map(inquilinoExistente -> {
                    inquilino.setId(id);
                    InquilinoModel inquilinoAtualizado = inquilinoService.salvar(inquilino);
                    return ResponseEntity.ok(inquilinoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // Atualizar um inquilino
    @PutMapping("/update/{id}")
    public ResponseEntity<InquilinoModel> update(@PathVariable Long id, @RequestBody InquilinoModel inquilino) {
        return ResponseEntity.ok(InquilinoService.update(id, inquilino));
    }


    // Deletar um inquilino
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        inquilinoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}