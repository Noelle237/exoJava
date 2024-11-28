import java.util.Scanner;

public class CrudApp {
    private static Person[] people = new Person[10]; // Taille initiale
    private static int count = 0; // Compteur pour le nombre de personnes

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;

        do {
            System.out.println("******* MON MENU ************");
            System.out.println("1. Afficher les personnes");
            System.out.println("2. Ajouter une personne");
            System.out.println("3. Supprimer une personne");
            System.out.println("4. Modifier une personne");
            System.out.println("Tapez 'exit' pour quitter");

            str = sc.nextLine();

            switch (str) {
                case "1":
                    afficherPersonnes();
                    break;
                case "2":
                    System.out.print("Entrez le nom de la personne à ajouter : ");
                    String nameToAdd = sc.nextLine();
                    ajouterPersonne(nameToAdd);
                    break;
                case "3":
                    System.out.print("Entrez le nom de la personne à supprimer : ");
                    String nameToRemove = sc.nextLine();
                    supprimerPersonne(nameToRemove);
                    break;
                case "4":
                    System.out.print("Entrez le nom de la personne à modifier : ");
                    String nameToEdit = sc.nextLine();
                    modifierPersonne(nameToEdit);
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Option invalide, essayez encore.");
            }

        } while (!"exit".equals(str));

        System.out.println("Au revoir");
        sc.close();
    }

    private static void afficherPersonnes() {
        if (count == 0) {
            System.out.println("Aucune personne à afficher.");
        } else {
            System.out.println("Liste des personnes :");
            for (int i = 0; i < count; i++) {
                System.out.println(people[i]);
            }
        }
    }

    private static void ajouterPersonne(String name) {
        if (count >= people.length) {
            redimensionnerTableau();
        }
        people[count++] = new Person(name);
        System.out.println("Personne ajoutée : " + name);
    }

    private static void supprimerPersonne(String name) {
        for (int i = 0; i < count; i++) {
            if (people[i].getName().equalsIgnoreCase(name)) {
                // Décaler les éléments
                for (int j = i; j < count - 1; j++) {
                    people[j] = people[j + 1];
                }
                people[--count] = null; // Réduire le compteur et vider la dernière position
                System.out.println("Personne supprimée : " + name);
                return;
            }
        }
        System.out.println("Personne non trouvée : " + name);
    }

    private static void modifierPersonne(String name) {
        for (int i = 0; i < count; i++) {
            if (people[i].getName().equalsIgnoreCase(name)) {
                System.out.print("Entrez le nouveau nom : ");
                Scanner sc = new Scanner(System.in);
                String newName = sc.nextLine();
                people[i].setName(newName);
                System.out.println("Personne modifiée : " + newName);
                return;
            }
        }
        System.out.println("Personne non trouvée : " + name);
    }

    private static void redimensionnerTableau() {
        Person[] newArray = new Person[people.length * 2];
        System.arraycopy(people, 0, newArray, 0, people.length);
        people = newArray;
        System.out.println("Tableau redimensionné à " + people.length);
    }
}