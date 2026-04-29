import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ordonnance {

    private int numero;
    private int idClient;
    private ArrayList<LigneOrdonnance> lignes;
    private boolean estPeriodique;
    private String periodicite;

    // Constructeur
    public Ordonnance(int numero, int idClient, boolean estPeriodique, String periodicite) {
        this.numero = numero;
        this.idClient = idClient;
        this.estPeriodique = estPeriodique;
        this.periodicite = periodicite;
        this.lignes = new ArrayList<>();
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public int getIdClient() {
        return idClient;
    }

    // Lecture seule
    public List<LigneOrdonnance> getLignes() {
        return Collections.unmodifiableList(lignes);
    }

    public boolean isEstPeriodique() {
        return estPeriodique;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    // Ajouter une ligne (à la création)
    public void ajouterLigne(LigneOrdonnance lo) {
        lignes.add(lo);
    }

    // Affichage
    public void afficher() {
        System.out.println("Ordonnance N° : " + numero);
        System.out.println("Client ID : " + idClient);
        System.out.println("Périodique : " + estPeriodique);
        if (estPeriodique) {
            System.out.println("Périodicité : " + periodicite);
        }

        System.out.println("---- Lignes ----");
        for (LigneOrdonnance lo : lignes) {
            lo.afficher();
        }
        System.out.println("=========================");
    }
}