package br.com.simplecrudcore.dto;

import br.com.simplecrudcore.model.Client;

public record PhoneDTO(
		Long id,
		Client client,
		String phone
		) {

}
