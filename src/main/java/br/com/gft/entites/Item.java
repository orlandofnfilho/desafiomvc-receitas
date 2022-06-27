package br.com.gft.entites;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal quantity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Receita receita;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Ingrediente ingrediente;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private UnidadeMedida unidadeMedida;

}
