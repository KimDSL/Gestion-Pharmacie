import java.util.ArrayList;
import java.util.Scanner;

public class Etagere {
    private int numero;
    private String typeTraitement;
    private ArrayList<Medicament> medicaments;

    Scanner sc = new Scanner(System.in);
    
    // Constructeur
    public Etagere(int numero, String typeTraitement) {
        this.numero = numero;
        this.typeTraitement = typeTraitement;
        this.medicaments = new ArrayList<>();
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public String getTypeTraitement() {
        return typeTraitement;
    }

    // Setters
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTypeTraitement(String typeTraitement) {
        this.typeTraitement = typeTraitement;
    }

    // ajouter médicament à l'étagère
    public void ajouterMedicament(Medicament m) {
        medicaments.add(m);
        System.out.println("Médicament " + m.getNomCommercial() + " ajouté à l'étagère " + numero);
    }

    // retirer médicament de l'étagère
    public void retirerMedicament(String nomCommercial) {

        if (chercherMedicament(nomCommercial) == null) {
            System.out.println("Médicament " + nomCommercial + " non trouvé à l'étagère " + numero);
        } else {
            medicaments.remove(chercherMedicament(nomCommercial));
            System.out.println("Médicament " + nomCommercial + " retiré de l'étagère " + numero);
        }
    }

    // Chercher un médicament dans l'étagère
    public Medicament chercherMedicament(String nomCommercial) {

        for (Medicament m : medicaments) {
            if (m.getNomCommercial().equalsIgnoreCase(nomCommercial)) {
                return m;
            }
        }

        return null;
    }
    
    // Afficher un médicament de l'étagère
    public void afficherMedicament(String nomCommercial) {
        Medicament m = chercherMedicament(nomCommercial);
        if (m != null) {
            System.out.println("Médicament trouvé: \n " + m);
        } else {
            System.out.println("Médicament " + nomCommercial + " non trouvé à l'étagère " + numero);
        }
    }

    // Afficher les médicaments de l'étagère
    public void afficherMedicaments() {
        if (medicaments.isEmpty()) {
            System.out.println("Aucun médicament dans l'étagère " + numero);
        } else {
            System.out.println("Médicaments dans l'étagère " + numero + ":");
            for (Medicament m : medicaments) {
                System.out.println(m);
            }
        }
    }
}