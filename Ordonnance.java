import java.util.ArrayList;
import java.util.List;

// Classe gérant une ligne spécifique de l'ordonnance
class LigneOrdonnance {
    private String nomMedicament;
    private String formeGalenique;
    private int quantitePrescrite;

    public LigneOrdonnance(String nom, String forme, int qte) {
        this.nomMedicament = nom;
        this.formeGalenique = forme;
        this.quantitePrescrite = qte;
    }

    // Getters conformément au MCD
    public String getNomMedicament() { return nomMedicament; }
    public String getFormeGalenique() { return formeGalenique; }
    public int getQuantitePrescrite() { return quantitePrescrite; }

    public void afficher() {
        System.out.println("- " + nomMedicament + " (" + formeGalenique + ") : x" + quantitePrescrite);
    }
}

// Classe principale pour l'ordonnance
public class Ordonnance {
    private int numero;
    private int idClient;
    private ArrayList<LigneOrdonnance> lignes;
    private boolean estPeriodique;
    private String periodicite;

    public Ordonnance(int num, int idClient, boolean estPeriodique, String periodicite) {
        this.numero = num;
        this.idClient = idClient;
        this.estPeriodique = estPeriodique;
        this.periodicite = periodicite;
        this.lignes = new ArrayList<>();
    }

    // Méthodes imposées par le chef de projet
    public int getNumero() { return numero; }
    public int getIdClient() { return idClient; }
    public boolean isEstPeriodique() { return estPeriodique; }
    public String getPeriodicite() { return periodicite; }
    
    // Pour la lecture seule demandée
    public List<LigneOrdonnance> getLignes() { 
        return new ArrayList<>(lignes); 
    }

    public void ajouterLigne(LigneOrdonnance lo) {
        this.lignes.add(lo);
    }

    public void afficher() {
        System.out.println("Ordonnance N°" + numero + " | Client ID: " + idClient);
        System.out.println("Périodique: " + (estPeriodique ? "Oui (" + periodicite + ")" : "Non"));
        for (LigneOrdonnance lo : lignes) {
            lo.afficher();
        }
    }
}