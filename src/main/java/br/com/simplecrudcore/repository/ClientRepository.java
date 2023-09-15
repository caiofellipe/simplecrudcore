package br.com.simplecrudcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.simplecrudcore.dto.ClientDTO;
import br.com.simplecrudcore.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query(value = "SELECT c FROM Client c WHERE c.cpf = :cpf OR c.cnpj = :cnpj")
	Client findByClientCpfOrCnpj(String cpf, String cnpj);
	
	@Query(value = "SELECT c FROM Client c WHERE c.name LIKE %:name% AND c.active = :active")
	List<Client> findByClientNameAndActive(String name, Boolean active);

	@Query(value = "SELECT c, p FROM Client c INNER JOIN Phone p ON p.client.id = c.id WHERE c.id = :id")
	Client findClientId(Long id);
	
	@Query(value = "UPDATE Client c SET c.active = false WHERE c.id = :id")
	Client disableClient(Long id);
}
