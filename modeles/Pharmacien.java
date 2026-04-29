public class Pharmacien {
    private int idPharmacien;
    private String nomPharmacien;
    private String heureArrivee;
    private String heureDepart;

    public Pharmacien(int id, String nom, String arrivee, String depart) {
        this.idPharmacien = id;
        this.nomPharmacien = nom;
        this.heureArrivee = arrivee;
        this.heureDepart = depart;
    }

    // Getters standard
    public int getIdPharmacien() { return idPharmacien; }
    public String getNomPharmacien() { return nomPharmacien; }
    public String getHeureArrivee() { return heureArrivee; }
    public String getHeureDepart() { return heureDepart; }

    /**
     * Méthode métier cruciale de la Personne 4
     * Vérifie le stock sur les étagères (P1) pour chaque ligne de l'ordonnance (P3)
     */
    public void verifierOrdonnance(Ordonnance o) {
        System.out.println("Vérification par " + nomPharmacien + " de l'ordonnance n°" + o.getNumero());
        
        if (o.isEstPeriodique()) {
            System.out.println("Attention : Renouvellement " + o.getPeriodicite() + " détecté.");
        }

        // Ici, on parcourt les lignes de l'ordonnance
        for (LigneOrdonnance lo : o.getLignes()) {
            // Logique de vérification : on affiche simplement l'action ici
            System.out.println("Recherche de : " + lo.getNomMedicament() + " en stock...");
        }
    }

    public void afficher() {
        System.out.println("Pharmacien: " + nomPharmacien + " [ID: " + idPharmacien + "]");
        System.out.println("Service: " + heureArrivee + " - " + heureDepart);
    }
}