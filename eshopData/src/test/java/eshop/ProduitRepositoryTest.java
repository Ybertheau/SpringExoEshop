package eshop;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.entity.Produit;
import eshop.exception.ProduitException;
import eshop.repository.ProduitRepository;

@SpringJUnitConfig(JpaConfig.class)
class ProduitRepositoryTest {

    @Autowired
    private ProduitRepository produitRepo;

    @Test
    void requetePerso() {
        produitRepo.findByLibelleContaining("o");

    }

    @Test
    void test() {
        Produit f = produitRepo.findById(100L).orElseThrow(ProduitException::new);
        assertTrue(f instanceof Produit);
        // if (opt.isPresent()) {
        // System.out.println(opt.get());
        // }
    }

    @Test
    void findByIdException() {
        assertThrows(ProduitException.class, () -> {
            produitRepo.findById(500L).orElseThrow(ProduitException::new);
        });
    }

    @Test
    void PageTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Produit> page = produitRepo.findAll(pageable);
        System.out.println(page);
        page.forEach(f -> {
            System.out.println(f.getId());
        });
        page = produitRepo.findAll(page.nextOrLastPageable());
        System.out.println(page);
        page.forEach(f -> {
            System.out.println(f.getId());
        });
    }

}
