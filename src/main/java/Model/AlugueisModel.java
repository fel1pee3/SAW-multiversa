package Model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Alugueis")

public class AlugueisModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @OneToMany(mappedBy="professor")
    private List<DisciplinaModel> disciplinas;
}