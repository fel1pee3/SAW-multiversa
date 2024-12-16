package com.felipe.first_spring_app.controller;


import com.felipe.first_spring_app.model.ImovelModel;
import com.felipe.first_spring_app.service.ImovelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/imovel")
public class ImovelController {


    private final ImovelService imovelService;


    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }


    // Criar um imovel
    @PostMapping("/create")
    public ImovelModel salvar(@RequestBody ImovelModel imovel) {
        return imovelService.salvar(imovel);
    }


    // Listar todos os imoveis
    @GetMapping
    public List<ImovelModel> listarTodos() {
        return imovelService.listarTodos();
    }


    // Buscar um Imovel por ID
    @GetMapping("/{id}")
    public ResponseEntity<ImovelModel> buscarPorId(@PathVariable Long id) {
        return imovelService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/imovel/{id}")
    public ResponseEntity<ImovelModel> atualizarImovel(@PathVariable Long id, @RequestBody ImovelModel imovel) {
        return imovelService.buscarPorId(id)
                .map(imovelExistente -> {
                    imovel.setId(id);
                    ImovelModel imovelAtualizado = imovelService.salvar(imovel);
                    return ResponseEntity.ok(imovelAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // Atualizar imovel
    @PutMapping("/update/{id}")
    public ResponseEntity<ImovelModel> update(@PathVariable Long id, @RequestBody ImovelModel imovel) {
        return ResponseEntity.ok(ImovelService.update(id, imovel));
    }


    // Deletar Imovel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        imovelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}