package br.com.gft.entites;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemId id = new ItemId();

	@ManyToOne
	@MapsId("receitaId")
	@JoinColumn(name = "receita_id")
	private Receita receita;

	@ManyToOne
	@MapsId("ingredienteId")
	@JoinColumn(name = "ingrediente_id")
	private Ingrediente ingrediente;

	private Double quantidade;

	@OneToOne
	private UnidadeMedida unidadeMedida;

}
