package br.com.simplecrudcore.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.simplecrudcore.dto.ClientDTO;
import br.com.simplecrudcore.model.Client;
import br.com.simplecrudcore.model.Phone;

@Component
public class ClientMapper {

	public ClientDTO toDTO(Client client) {
		if(client == null) {
			return null;
		}
		
		return new ClientDTO(
				client.getId(), client.getName(), client.getPeople(), client.getCpf(),
				client.getCnpj(), client.getRg(), client.getIe(), client.getActive(), 
				client.getDateRegister(), client.getPhone()
				);
	}
	
	public Client toEntity(ClientDTO clientDTO) {
		if(clientDTO == null) {
			return null;
		}
		
		Client client = new Client();
		if(clientDTO.id() != null) {
			client.setId(clientDTO.id());
		}
		
		client.setName(clientDTO.name());
		client.setPeople(clientDTO.people());
		client.setCpf(clientDTO.cpf());
		client.setCnpj(clientDTO.cnpj());
		client.setRg(clientDTO.rg());
		client.setIe(clientDTO.ie());
		client.setActive(clientDTO.active());
	
		return client;
	}
	
}
