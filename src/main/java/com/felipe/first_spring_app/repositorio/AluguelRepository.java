package com.felipe.first_spring_app.repositorio;

import com.sistemamultiversa.ProjetoMultiversa.model.AluguelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<AluguelModel, Long> {
}
