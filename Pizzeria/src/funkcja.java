import java.util.*;

// Klasa funkcja zawiera logikę działania programu Pizzeria
public class funkcja {
    private final Random RANDOM = new Random(); // Generator liczb losowych do losowania promocji
    private final List<String> PROMOCJE = Arrays.asList(
            "Zniżka 10% na wszystkie pizze!",
            "Druga pizza za pół ceny!",
            "20% zniżki na zamówienia powyżej 50 zł!"
    ); // Lista dostępnych promocji

    private List<String> zamowienia = new ArrayList<>(); // Lista zamówionych pizz z opisami
    private List<Double> ceny = new ArrayList<>(); // Lista cen zamówionych pizz
    private double suma = 0.0; // Łączna suma zamówienia
    private String promocja = ""; // Aktualna promocja

    // Wyświetlanie menu głównego
    public void wyswietlMenu() {
        System.out.println("Witaj w Pizzerii! Wybierz opcję:");
        System.out.println("1. Zamów pizzę gotową");
        System.out.println("2. Złóż swoją własną pizzę");
        System.out.println("3. Sprawdź aktualne promocje");
        System.out.println("4. Wyjdź");
        System.out.print("Twój wybór: ");
    }

    // Obsługa zamawiania gotowej pizzy
    public void zamowGotowaPizza(Scanner scanner) {
        // Mapa przechowująca gotowe pizze i ich ceny
        Map<String, Double> gotowePizze = new LinkedHashMap<>();
        gotowePizze.put("Margherita", 20.0);
        gotowePizze.put("Pepperoni", 25.0);
        gotowePizze.put("Hawajska", 22.5);
        gotowePizze.put("Wegetariańska", 24.0);

        // Wyświetlenie listy dostępnych pizz
        wyswietlGotowePizze(gotowePizze);

        System.out.print("Wybierz numer pizzy: ");
        int numerPizzy = scanner.nextInt();

        // Sprawdzenie poprawności wyboru użytkownika
        if (numerPizzy > 0 && numerPizzy <= gotowePizze.size()) {
            String wybranaPizza = new ArrayList<>(gotowePizze.keySet()).get(numerPizzy - 1);
            double cena = gotowePizze.get(wybranaPizza);
            System.out.println("Wybrałeś pizzę: " + wybranaPizza + " za " + cena + " zł");
            dodajDoRachunku(wybranaPizza, cena); // Dodanie pizzy do rachunku
        } else {
            System.out.println("Nieprawidłowy wybór.");
        }
    }

    // Wyświetlenie listy gotowych pizz
    private void wyswietlGotowePizze(Map<String, Double> gotowePizze) {
        System.out.println("Dostępne gotowe pizze:");
        int i = 1;
        for (Map.Entry<String, Double> entry : gotowePizze.entrySet()) {
            System.out.println(i++ + ". " + entry.getKey() + " - " + entry.getValue() + " zł");
        }
    }

    // Obsługa składania własnej pizzy
    public void zlozWlasnaPizza(Scanner scanner) {
        // Lista dostępnych składników z cenami
        List<Skladnik> skladniki = Arrays.asList(
                new Skladnik("Ser", 3.0),
                new Skladnik("Szynka", 5.0),
                new Skladnik("Pieczarki", 4.0),
                new Skladnik("Papryka", 2.5),
                new Skladnik("Oliwki", 4.5),
                new Skladnik("Kukurydza", 3.5)
        );

        Set<String> wybraneSkladniki = new HashSet<>(); // Zbiór wybranych składników
        double suma = 15.0; // Bazowa cena pizzy

        int wybor; // Wybór użytkownika
        do {
            // Wyświetlenie dostępnych składników
            wyswietlSkladniki(skladniki, wybraneSkladniki);
            System.out.print("Twój wybór: ");
            wybor = scanner.nextInt();

            // Obsługa dodawania i usuwania składników
            if (wybor > 0 && wybor <= skladniki.size()) {
                Skladnik skladnik = skladniki.get(wybor - 1);
                if (wybraneSkladniki.contains(skladnik.nazwa)) {
                    wybraneSkladniki.remove(skladnik.nazwa);
                    suma -= skladnik.cena;
                    System.out.println("Usunięto składnik: " + skladnik.nazwa);
                } else {
                    wybraneSkladniki.add(skladnik.nazwa);
                    suma += skladnik.cena;
                    System.out.println("Dodano składnik: " + skladnik.nazwa);
                }
            } else if (wybor != skladniki.size() + 1) {
                System.out.println("Nieprawidłowy wybór.");
            }
        } while (wybor != skladniki.size() + 1); // Wyjście z pętli przy zakończeniu wyboru składników

        // Dodanie pizzy własnej do rachunku
        dodajDoRachunku("Własna pizza", suma);
        System.out.println("Dodano pizzę własną za " + suma + " zł");
    }

    // Wyświetlenie listy składników
    private void wyswietlSkladniki(List<Skladnik> skladniki, Set<String> wybraneSkladniki) {
        System.out.println("Dostępne składniki:");
        for (int i = 0; i < skladniki.size(); i++) {
            Skladnik skladnik = skladniki.get(i);
            System.out.println((i + 1) + ". " + skladnik.nazwa + " - " + skladnik.cena + " zł"
                    + (wybraneSkladniki.contains(skladnik.nazwa) ? " (wybrano)" : ""));
        }
        System.out.println((skladniki.size() + 1) + ". Zakończ wybieranie składników");
    }

    // Losowanie promocji
    public void wygenerujPromocje() {
        int index = RANDOM.nextInt(PROMOCJE.size());
        promocja = PROMOCJE.get(index); // Losowanie promocji z listy
        System.out.println("Dzisiejsza promocja: " + promocja);
    }

    // Dodanie pizzy do rachunku
    public void dodajDoRachunku(String nazwa, double cena) {
        zamowienia.add(nazwa + " - " + cena + " zł");
        ceny.add(cena); // Dodanie ceny do listy
        suma += cena; // Aktualizacja sumy
    }

    // Wyświetlenie podsumowania zamówienia
    public void wyswietlPodsumowanie() {
        System.out.println("Twoje zamówienia:");
        for (String zamowienie : zamowienia) {
            System.out.println("- " + zamowienie);
        }

        // Zastosowanie promocji
        if (!promocja.isEmpty()) {
            System.out.println("Promocja zastosowana: " + promocja);

            if (promocja.contains("10%")) {
                suma *= 0.9; // Obniżenie sumy o 10%
            } else if (promocja.contains("Druga pizza za pół ceny") && ceny.size() >= 2) {
                double najtanszaPizza = Collections.min(ceny); // Znalezienie najtańszej pizzy
                suma -= najtanszaPizza / 2; // Odjęcie połowy ceny najtańszej pizzy
                System.out.println("Zniżka: -" + (najtanszaPizza / 2) + " zł za drugą pizzę.");
            } else if (promocja.contains("20%") && suma > 50) {
                suma *= 0.8; // Obniżenie sumy o 20% dla zamówień powyżej 50 zł
            }
        }

        System.out.println("Łączny koszt: " + suma + " zł");
    }
}