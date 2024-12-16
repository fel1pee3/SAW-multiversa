package com.felipe.first_spring_app.service;


import com.felipe.first_spring_app.model.InquilinoModel;
import com.felipe.first_spring_app.repositorio.InquilinoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class InquilinoService {


    private final InquilinoRepository inquilinoRepository;


    public InquilinoService(InquilinoRepository inquilinoRepository) {
        this.inquilinoRepository = inquilinoRepository;
    }


    public List<InquilinoModel> listarTodos() {
        return inquilinoRepository.findAll();
    }


    public Optional<InquilinoModel> buscarPorId(Long id) {
        return inquilinoRepository.findById(id);
    }


    public InquilinoModel salvar(InquilinoModel inquilino) {
        return inquilinoRepository.save(inquilino);
    }


    public static InquilinoModel update(Long id, InquilinoModel inquilino) {
        return inquilino;
    }

    public void deletar(Long id) {
        inquilinoRepository.deleteById(id);
    }
}
