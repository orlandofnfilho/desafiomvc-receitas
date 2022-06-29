package br.com.gft.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private Long id;

	private String nome;

	private Integer tempoPreparo;

	private String modoPreparo;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "receita")
	private List<Item> itens = new ArrayList<>();

	public void addItem(Item item) {
		this.itens.add(item);
		item.setReceita(this);
	}

	public void removeItem(Item item) {
		this.itens.remove(item);
	}

	public void removeItem(int index) {
		Item item = this.itens.get(index);

		if (item != null) {
			this.itens.remove(index);
		}
	}

	public void corrigirItens() {
		limparItensVazios();

		for (Item item : itens) {
			item.setReceita(this);
		}
	}

	private void limparItensVazios() {
		List<Item> itensVazios = itens.stream().filter(i -> i.isVazio()).collect(Collectors.toList());

		for (Item item : itensVazios) {
			removeItem(item);
		}
	}
}
