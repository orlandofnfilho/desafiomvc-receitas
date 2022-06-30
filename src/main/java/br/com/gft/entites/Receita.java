package br.com.gft.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Receita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long receitaId;

	@NotBlank(message = "Insira o nome")
	private String nome;

	@Min(value = 1, message = "O tempo minimo deve ser 1")
	private Integer tempoPreparo;

	@Column(columnDefinition = "TEXT")
	private String modoPreparo;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "receita")
	private List<Item> itens = new ArrayList<>();

}
