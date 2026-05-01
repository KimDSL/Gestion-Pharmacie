import java.util.ArrayList;

public class Pharmacien {

    // Attribut
    private int    idPharmacien;
    private String nomPharmacien;
    private String heureArrivee;
    private String heureDepart;

    // Construire
    public Pharmacien(int idPharmacien, String nomPharmacien,
                      String heureArrivee, String heureDepart) {
        this.idPharmacien  = idPharmacien;
        this.nomPharmacien = nomPharmacien;
        this.heureArrivee  = heureArrivee;
        this.heureDepart   = heureDepart;
    }

    // Getters
    public int    getIdPharmacien()  { return idPharmacien; }
    public String getNomPharmacien() { return nomPharmacien; }
    public String getHeureArrivee()  { return heureArrivee; }
    public String getHeureDepart()   { return heureDepart; }

    // Setters
    public void setHeureArrivee(String heureArrivee) { this.heureArrivee = heureArrivee; }
    public void setHeureDepart(String heureDepart)   { this.heureDepart  = heureDepart; }

    
    // Vérifie une ordonnance
    public boolean verifierOrdonnance(Ordonnance o, Etagere[] etageres) {
        System.out.println("");
        System.out.println("Vérification ordonnance N°" + o.getNumero()
                + " par " + nomPharmacien);

        if (o.isEstPeriodique()) {
            System.out.println("  [INFO] Ordonnance périodique : " + o.getPeriodicite());
        }

        ArrayList<LigneOrdonnance> lignes = o.getLignes();
        boolean toutDisponible = true;

        for (LigneOrdonnance lo : lignes) {
            Medicament trouve = null;

            for (Etagere e : etageres) {
                Medicament m = e.chercherMedicament(lo.getNomMedicament());
                if (m != null) {
                    trouve = m;
                    break;
                }
            }

            if (trouve == null) {
                System.out.println("  [MANQUANT] " + lo.getNomMedicament()
                        + " — introuvable en stock.");
                toutDisponible = false;
            } else if (trouve.getQuantiteDisponible() < lo.getQuantitePrescrite()) {
                System.out.println("  [STOCK INSUFFISANT] " + lo.getNomMedicament()
                        + " — prescrit : " + lo.getQuantitePrescrite()
                        + ", disponible : " + trouve.getQuantiteDisponible());
                toutDisponible = false;
            } else {
                System.out.println("  [OK] " + lo.getNomMedicament()
                        + " — quantité suffisante.");
            }
        }

        System.out.println("");
        if (toutDisponible) {
            System.out.println("  Résultat : ordonnance validée.");
        } else {
            System.out.println("  Résultat : ordonnance NON validée — stock insuffisant.");
        }
        System.out.println("");
        return toutDisponible;
    }

    // Affiche toutes les informations d'un pharmacien
    public void afficher() {
        System.out.println("------------------------------------");
        System.out.println("Pharmacien n° : " + idPharmacien);
        System.out.println("Nom           : " + nomPharmacien);
        System.out.println("Arrivée       : " + heureArrivee);
        System.out.println("Départ        : " + heureDepart);
        System.out.println("------------------------------------");
    }
}
