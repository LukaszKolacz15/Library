import java.util.Scanner;

/**
 * Created by Lukasz Kolacz on 10.04.2017.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        do {
            menu();
            int numb = scanner.nextInt();

            switch (numb) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                case 7:
                    System.exit(0);
                    break;
            }
        } while (true);

    }

    private static void menu(){

        System.out.println("--------- WITAMY W BIBLIOTECE ---------");
        System.out.println("WYBIERZ DZIAŁANIE");
        System.out.println("1. Dodaj użytkownika");
        System.out.println("2. Dodaj książkę");
        System.out.println("3. Dodaj wypożyczenie");
        System.out.println("4. Wyświetl wszystkich użytkowników");
        System.out.println("5. Wyświetl wszystkie książki");
        System.out.println("6. Wyświetl wszystkie wypożyczenia");
        System.out.println("7. Exit");
        System.out.println("---------------------------------------");

    }


}
