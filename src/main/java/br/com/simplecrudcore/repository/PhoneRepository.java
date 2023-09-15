package br.com.simplecrudcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.simplecrudcore.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
