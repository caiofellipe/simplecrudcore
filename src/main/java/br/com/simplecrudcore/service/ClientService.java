package br.com.simplecrudcore.service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.simplecrudcore.dto.ClientDTO;
import br.com.simplecrudcore.dto.mapper.ClientMapper;
import br.com.simplecrudcore.exception.ClientException;
import br.com.simplecrudcore.model.Client;
import br.com.simplecrudcore.model.Phone;
import br.com.simplecrudcore.repository.ClientRepository;
import br.com.simplecrudcore.repository.PhoneRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	private final PhoneRepository phoneRepository;
	private final ClientMapper clientMapper;
	
	public ClientService(ClientRepository clientRepository, PhoneRepository phoneRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.phoneRepository = phoneRepository;
		
		this.clientMapper = clientMapper;
	}
	
	public ClientDTO save(ClientDTO clientDTO) {
		Client client = new Client();
		client = clientRepository.findByClientCpfOrCnpj(clientDTO.cpf(), clientDTO.cnpj());
		
		if(client != null) {
			throw new ClientException("Já possui cadastro");
		}
		
		client = clientMapper.toEntity(clientDTO);
		client.setDateRegister(currentDate());
		client.setActive(true);
		
		List<Phone> phones = new ArrayList<Phone>();
		if(clientDTO.phone() != null) {
			for(Phone phone: clientDTO.phone()) {
				if(phone.getId() == null) {
					phone.setClient(client);
				}
				phones.add(phone);
			}
		}
		
		client.setPhone(phones);
		clientRepository.save(client);
		return clientMapper.toDTO(client);
	}
	
	public List<ClientDTO>  get(String name, Boolean active) {
		List<ClientDTO> clientsDTO = new ArrayList<ClientDTO>(); 
		List<Client> clients = clientRepository.findByClientNameAndActive(name, active);
				
		if(clients.isEmpty()) {
			throw new ClientException("Não encontrado");
		}		
		
		for(Client client: clients) {
			clientsDTO.add(clientMapper.toDTO(client));
		}
		
		return clientsDTO;
	}
	
	public ClientDTO update(ClientDTO clientDTO) {
		Client client = clientRepository.findClientId(clientDTO.id());
		
		if(client == null) {
			throw new ClientException("Não Encontrado");
		}
		
		LocalDateTime clientRegister = client.getDateRegister();
		
		client = clientMapper.toEntity(clientDTO);
		
		List<Phone> phones = new ArrayList<Phone>();
		if(clientDTO.phone() != null) {
			for(Phone phone: clientDTO.phone()) {
				if(phone.getId() != null) {
					phone.setClient(client);
				}
				phones.add(phone);
			}
		}
		
		client.setPhone(phones);
		
		client.setDateRegister(clientRegister);
		
		return clientMapper.toDTO(
				clientRepository.save(client));
	}
	
	public String disable(ClientDTO clientDTO) {
		Client client = clientRepository.disableClient(clientDTO.id());
		
		return "Usuario " + client.getName() + " desabilitado";
	}

	private LocalDateTime currentDate() {
		return LocalDateTime.now();
	}

}
