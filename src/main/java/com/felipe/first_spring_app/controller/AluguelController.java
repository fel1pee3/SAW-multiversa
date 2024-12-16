package com.felipe.first_spring_app.controller;

import com.sistemamultiversa.ProjetoMultiversa.model.AluguelModel;
import com.sistemamultiversa.ProjetoMultiversa.service.AluguelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }


    // Criar um Aluguel
    @PostMapping("/create")
    public ResponseEntity<AluguelModel> salvar(@RequestBody AluguelModel aluguel) {
        return ResponseEntity.ok(aluguelService.salvar(aluguel));
    }


    // Listar todos os alugueis
    @GetMapping
    public List<AluguelModel> listarTodos() {
        return aluguelService.listarTodos();
    }

    // Buscar um aluguel por id
    @GetMapping("/{id}")
    public ResponseEntity<AluguelModel> buscarPorId(@PathVariable Long id) {
        return aluguelService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<AluguelModel> atualizar(@PathVariable Long id, @RequestBody AluguelModel aluguel) {
        return aluguelService.buscarPorId(id)
                .map(aluguelExistente -> {
                    aluguel.setId(id); // Garante que o ID do objeto a ser atualizado permaneça o mesmo
                    AluguelModel aluguelAtualizado = aluguelService.salvar(aluguel);
                    return ResponseEntity.ok(aluguelAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // Atualizar os dados de um aluguel
    @PutMapping("/update/{id}")
    public ResponseEntity<AluguelModel> update(@PathVariable Long id, @RequestBody AluguelModel aluguel) {
        return ResponseEntity.ok(AluguelService.update(id, aluguel));
    }



    // Deletar um aluguel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        aluguelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
