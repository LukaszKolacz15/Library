import java.util.Scanner;

/**
 * Created by Lukasz Kolacz on 20.04.2017.
 */
public class Menu {

    static int showMenu() {

        System.out.println(" ");
        System.out.println("--------- WITAMY W BIBLIOTECE ---------");
        System.out.println("WYBIERZ DZIAŁANIE");
        System.out.println("1. Dodaj użytkownika");
        System.out.println("2. Dodaj książkę");
        System.out.println("3. Dodaj wypożyczenie");
        System.out.println("4. Wyświetl wszystkich użytkowników");
        System.out.println("5. Wyświetl wszystkie książki");
        System.out.println("6. Wyświetl wszystkie wypożyczenia");
        System.out.println("7. Oddaj książkę ");
        System.out.println("8. Exit");
        System.out.println("---------------------------------------");
        System.out.println(" ");

        Scanner scanner = new Scanner(System.in);
        //TODO dodać przechwycenie InputMismatchException
        int numb = scanner.nextInt();

        return numb;
    }

}
