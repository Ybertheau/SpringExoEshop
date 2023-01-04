package eshop;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.entity.Produit;
import eshop.repository.ProduitRepository;

@SpringJUnitConfig(JpaConfig.class)
class ProduitRepositoryTest {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    void requetePerso() {
        produitRepository.findByLibelleContaining("o");

    }

    @Test
    @Disabled
    void test() {
        Produit f = produitRepository.findById(50L).orElse(null);
        assertTrue(f instanceof Produit);
        // if (opt.isPresent()) {
        // System.out.println(opt.get());
        // }
    }

    @Test
    @Disabled
    void PageTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Produit> page = produitRepository.findAll(pageable);
        System.out.println(page);
        page.forEach(f -> {
            System.out.println(f.getId());
        });
        page = produitRepository.findAll(page.nextOrLastPageable());
        System.out.println(page);
        page.forEach(f -> {
            System.out.println(f.getId());
        });
    }

}
