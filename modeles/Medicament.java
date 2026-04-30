public class Medicament {
    private String nomCommercial;
    private String forme;
    private double prix;
    private int stock;

    public Medicament(String nomCommercial, String forme, double prix, int stock) {
        this.nomCommercial = nomCommercial;
        this.forme = forme;
        this.prix = prix;
        this.stock = stock;
    }

    // Getters
    public String getNomCommercial() {
        return nomCommercial;
    }

    public String getForme() {
        return forme;
    }

    public double getPrix() {
        return prix;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Savoir si le médicament est disponible selon une quantité demandée
    public boolean estDispo(int quantiteDemandee) {
        return this.stock >= quantiteDemandee;
    }

    // Diminuer le stock (par exemple lors d'une vente)
    public void diminuerStock(int quantite) {
        if (estDispo(quantite)) {
            this.stock -= quantite;
        } else {
            System.out.println("Stock insuffisant pour " + nomCommercial + ". Stock actuel : " + this.stock);
        }
    }

    // Ajouter au stock (par exemple lors d'une livraison)
    public void ajouterStock(int quantite) {
        if (quantite > 0) {
            this.stock += quantite;
        }
    }

    // Affichage personnalisé pour le médicament
    @Override
    public String toString() {
        return nomCommercial + " (" + forme + ") - Prix: " + prix + " FCFA | Stock: " + stock;
    }
}
