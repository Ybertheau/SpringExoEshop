package eshop.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long>{

	List<Fournisseur> findByContact(String contact);
	List<Fournisseur> findByName(String nom);
	List<Fournisseur> findByNameContaining(String nom);
	
	
	Page<Fournisseur> findByNomContaining(String nom, Pageable pageable);

	@Query("select f from Fournisseur f left join fetch f.fourniseeurCommeReferent where f.id=:id")
	Optional<Fournisseur> findByIdFetchFourniseurCommeReferent(@Param("id") Long id);
	@Modifying
	@Transactional
	@Query("update Fournisseur f set f.referent=null where f.referent=:referent")
	void updateByReferentSetReferentToNull(@Param("referent") Fournisseur referent);
	
	@Modifying
	@Transactional
	@Query("delete Formation f where f.referent=:referent")
	void deleteByReferent(@Param("referent") Fournisseur referent);
	
	Fournisseur findByIdFetchFournisseurCommeReferent(Long id);
	
}
