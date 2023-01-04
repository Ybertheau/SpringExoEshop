package eshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    @Query("select o from Orders o left join fetch o.achats where o.numero=:id")
    Optional<Commande> findByNumeroFetchAchat(@Param("order_number") Long numero);
}
