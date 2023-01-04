package eshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    @Query("select c from Commande c left join fetch c.achats where c.numero=:numero")
    Optional<Commande> findByNumeroFetchAchat(@Param("numero") Long numero);
}
