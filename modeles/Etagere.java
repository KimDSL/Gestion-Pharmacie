import java.util.ArrayList;

public class Etagere {

    // attributs
    private int    numero;
    private String typeTraitement;
    private ArrayList<Medicament> medicaments;

    // constructeur
    public Etagere(int numero, String typeTraitement) {
        this.numero         = numero;
        this.typeTraitement = typeTraitement;
        this.medicaments    = new ArrayList<>();
    }

    // getters
    public int    getNumero()        { return numero; }
    public String getTypeTraitement() { return typeTraitement; }

    // Retourne une copie de la liste des médicaments utilisée par Sauvegarde.java pour écrire dans le fichier
    public ArrayList<Medicament> getMedicaments() {
        return new ArrayList<>(medicaments);
    }


    // ajouter un médicament à l'étagère avec message pour confirmer à l'utilisateur
    public void ajouterMedicament(Medicament m) {
        medicaments.add(m);
        System.out.println(m.getNomCommercial() + " ajouté à l'étagère n°" + numero + ".");
    }

    // ajouter un médicament sans afficher de message utilisé par Sauvegarde.java
    public void ajouterMedicamentSilencieux(Medicament m) {
        medicaments.add(m);
    }

    // retirer un médicament de l'étagère
    public void retirerMedicament(String nom) {
        Medicament trouve = chercherMedicament(nom);
        if (trouve != null) {
            medicaments.remove(trouve);
            System.out.println(nom + " retiré de l'étagère n°" + numero + ".");
        } else {
            System.out.println("Médicament introuvable : " + nom);
        }
    }

    // chercher un médicament
    public Medicament chercherMedicament(String nom) {
        for (Medicament m : medicaments) {
            if (m.getNomCommercial().equalsIgnoreCase(nom)) {
                return m;
            }
        }
        return null;
    }

    // afficher tous les médicaments de l'étagère
    public void afficherMedicaments() {
        System.out.println("--- Étagère N°" + numero + " : " + typeTraitement + " ---");
        if (medicaments.isEmpty()) {
            System.out.println("  (aucun médicament)");
        } else {
            for (Medicament m : medicaments) {
                m.afficher();
            }
        }
    }
}
