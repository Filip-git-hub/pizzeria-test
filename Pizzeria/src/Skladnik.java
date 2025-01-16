// Klasa reprezentująca składnik pizzy
public class Skladnik {
    public String nazwa; // Nazwa składnika, np. "Ser", "Szynka"
    public double cena; // Cena składnika, np. 3.0, 5.0

    // Konstruktor klasy Skladnik, umożliwiający zainicjalizowanie nazwy i ceny składnika
    public Skladnik(String nazwa, double cena) {
        this.nazwa = nazwa; // Przypisanie nazwy do pola klasy
        this.cena = cena; // Przypisanie ceny do pola klasy
    }
}