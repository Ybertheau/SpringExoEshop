package eshop.service;

import org.springframework.beans.factory.annotation.Autowired;

import eshop.entity.Commande;
import eshop.exception.CommandeException;
import eshop.exception.IdException;
import eshop.repository.AchatRepository;
import eshop.repository.CommandeRepository;

public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepo;
    @Autowired
    private AchatRepository achatRepo;

    private void checkCommandeIsNotNull(Commande commande) {
        if (commande == null) {
            throw new CommandeException("Commande null");
        }
    }

    public void create(Commande commande) {
        checkCommandeIsNotNull(commande);
        if (commande.getClient() == null) {
            throw new CommandeException("Aucun client");
        }
        if (commande.getAchats() == null) {
            throw new CommandeException("Aucun achats");
        }
        commandeRepo.save(commande);
    }

    public Commande getByNumero(Long numero) {
        if (numero == null) {
            throw new IdException();
        }
        return commandeRepo.findByNumeroFetchAchat(numero).orElseThrow(() -> {
            throw new CommandeException("Commande inconnue");
        });
    }

    public void delete(Commande commande) {
        checkCommandeIsNotNull(commande);
        deleteByNumero(commande.getNumero());
    }

    public void delete(Long numero) {
        deleteByNumero(numero);
    }

    private void deleteByNumero(Long numero) {
        Commande commande = getByNumero(numero);
        achatRepo.deleteByCommande(commande);
        commandeRepo.delete(commande);
    }
}
