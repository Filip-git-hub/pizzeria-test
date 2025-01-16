import java.util.Scanner;

public class Pizzeria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        funkcja funkcje = new funkcja(); // Obiekt zarządzający funkcjonalnością pizzerii
        int wybor;

        do {
            funkcje.wyswietlMenu(); // Wyświetlenie głównego menu
            wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    funkcje.zamowGotowaPizza(scanner); // Zamawianie gotowej pizzy
                    break;
                case 2:
                    funkcje.zlozWlasnaPizza(scanner); // Składanie własnej pizzy
                    break;
                case 3:
                    funkcje.wygenerujPromocje(); // Generowanie promocji
                    break;
                case 4:
                    funkcje.wyswietlPodsumowanie(); // Wyświetlenie rachunku
                    System.out.println("Dziękujemy za wizytę! Do zobaczenia!");
                    System.exit(0); // Zakończenie programu
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
            }
            System.out.println();
        } while (wybor != 4);

        scanner.close();
    }
}