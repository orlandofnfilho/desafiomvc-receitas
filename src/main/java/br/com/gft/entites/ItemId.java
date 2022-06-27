package br.com.gft.entites;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ItemId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long receitaId;
	private Long ingredienteId;

	
}
