package br.edu.storeapi.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Entity @Getter @Setter @NoArgsConstructor
public class Produto {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(nullable=false) private String nome;
 private String descricao;
 @Column(nullable=false) private BigDecimal preco;
 @Column(nullable=false) private Integer estoque;
 @Column(nullable=false) private String categoria;
}
