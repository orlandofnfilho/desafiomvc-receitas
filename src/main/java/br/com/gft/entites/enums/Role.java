package br.com.gft.entites.enums;

import java.util.ArrayList;
import java.util.List;

public enum Role {

	ADMIN("ADMIN"), USUARIO("USUARIO");

	private String nome;

	private Role(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return getNome();
	}

	public static List<String> getRoles() {
		List<String> roles = new ArrayList<String>();

		for (Role role : values()) {
			roles.add(role.getNome());
		}

		return roles;
	}

}
