package br.com.simplecrudcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simplecrudcore.dto.ClientDTO;
import br.com.simplecrudcore.dto.ResponseDTO;
import br.com.simplecrudcore.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/{name}/{active}")
	public List<ClientDTO> getClients(@PathVariable String name, @PathVariable Boolean active){
		return clientService.get(name, active);
	}
	
	@PostMapping("/")
	public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) {
		return ResponseEntity.ok(clientService.save(clientDTO));
	}
	
	@PutMapping("/")
	public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
		return ResponseEntity.ok(clientService.update(clientDTO));
	}
	
	@PutMapping("/disableorenable")
	public ResponseEntity<ResponseDTO> disableOrEnableClient(@RequestBody ClientDTO clientDTO) {
		return ResponseEntity.ok(clientService.disableOrEnable(clientDTO));
	}
}
