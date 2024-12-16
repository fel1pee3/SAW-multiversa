package com.felipe.first_spring_app.repositorio;


import com.felipe.first_spring_app.model.ProprietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProprietarioRepository extends JpaRepository<ProprietarioModel, Long> {
}

