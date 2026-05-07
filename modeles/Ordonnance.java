import java.util.ArrayList;

public class Ordonnance {

    // Attributs
    private int    numero;
    private int    idClient;
    private ArrayList<LigneOrdonnance> lignes;
    private boolean estPeriodique;
    private String  periodicite;  // ex: "mensuelle", "hebdomadaire"

    // Constructeur
    public Ordonnance(int numero, int idClient, boolean estPeriodique, String periodicite) {
        this.numero        = numero;
        this.idClient      = idClient;
        this.estPeriodique = estPeriodique;
        this.periodicite   = estPeriodique ? periodicite : "aucune";
        this.lignes        = new ArrayList<>();
    }

    // Getters
    public int     getNumero()        { return numero; }
    public int     getIdClient()      { return idClient; }
    public boolean isEstPeriodique()  { return estPeriodique; }
    public String  getPeriodicite()   { return periodicite; }

    // retourne une copie de l'ordonnance
    public ArrayList<LigneOrdonnance> getLignes() {
        return new ArrayList<>(lignes);
    }

    // Ajoute une ligne d'ordonnance à la liste des lignes
    public void ajouterLigne(LigneOrdonnance lo) {
        lignes.add(lo);
    }

    // Affiche le contenu complet de l'ordonnance.
    public void afficher() {
        System.out.println("");
        System.out.println("       ORDONNANCE n°" + numero);
        System.out.println("  Client n°   : " + idClient);
        System.out.println("  Périodique  : " + (estPeriodique ? "Oui — " + periodicite : "Non"));
        System.out.println("");
        System.out.println("  Médicaments prescrits :");
        if (lignes.isEmpty()) {
            System.out.println("  (aucune ligne)");
        } else {
        for (LigneOrdonnance lo : lignes) {
            lo.afficher();
        }
        }
        System.out.println("");
    }
}
