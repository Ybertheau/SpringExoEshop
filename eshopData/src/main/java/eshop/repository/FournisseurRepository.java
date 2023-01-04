package eshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

	List<Fournisseur> findByContact(String contact);

	List<Fournisseur> findByNom(String nom);

	List<Fournisseur> findByNomContaining(String nom);

	Page<Fournisseur> findByNomContaining(String nom, Pageable pageable);

}
