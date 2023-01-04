package eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.entity.Fournisseur;
import eshop.exception.FournisseurException;
import eshop.exception.IdException;
import eshop.repository.FournisseurRepository;
import eshop.repository.ProduitRepository;

@Service
public class FournisseurService {
	@Autowired
	private FournisseurRepository fournisseurRepo;
	@Autowired
	private ProduitRepository produitRepo;

	public void create(Fournisseur fournisseur) {
		checkFournisseurIsNotNull(fournisseur);
		if (fournisseur.getNom() == null || fournisseur.getNom().isEmpty()) {
			throw new FournisseurException("nom vide");
		}
		fournisseurRepo.save(fournisseur);
	}

	private void checkFournisseurIsNotNull(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		if (fournisseur == null) {
			throw new FournisseurException("fournisseur null");
		}
	}
	public void deleteById(Long id) {
		if(id==null) {
			throw new IdException();
		}
		fournisseurRepo.deleteById(id);
	}
	
}
