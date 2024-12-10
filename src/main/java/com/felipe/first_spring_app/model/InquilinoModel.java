package com.felipe.first_spring_app.model;


import jakarta.persistence.*;
        import lombok.Data;

@Entity
@Table(name = "Inquilino")
@Data
public class InquilinoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "imovel_id", nullable = false)
    private ImovelModel imovel;
}
