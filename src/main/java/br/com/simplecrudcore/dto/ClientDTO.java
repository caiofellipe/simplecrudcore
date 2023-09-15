package br.com.simplecrudcore.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.simplecrudcore.model.Phone;

public record ClientDTO(
		Long id,
		String name,
		String people,
		String cpf,
		String cnpj,
		String rg,
		String ie,
		Boolean active,
		LocalDateTime dateRegister,
		List<Phone> phone
		) {

}
