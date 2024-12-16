package com.felipe.first_spring_app.service;

import com.felipe.first_spring_app.model.ImovelModel;
import com.felipe.first_spring_app.repositorio.ImovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    private final ImovelRepository imovelRepository;


    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }


    public List<ImovelModel> listarTodos() {
        return imovelRepository.findAll();
    }


    public Optional<ImovelModel> buscarPorId(Long id) {
        return imovelRepository.findById(id);
    }


    public ImovelModel salvar(ImovelModel imovel) {
        return imovelRepository.save(imovel);
    }


    public static ImovelModel update(Long id, ImovelModel imovel) {
        return imovel;
    }

    public void deletar(Long id) {
        imovelRepository.deleteById(id);
    }
}
