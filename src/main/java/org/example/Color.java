package org.example;

public record Color(int red, int green, int blue, int alpha) {
    public Color {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255 || alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("Każdy kolor musi być w przedziale 0 - 255.");
        }
    }

    public Color(int red, int green, int blue) {
        this(red, green, blue, 0);
    }

}

// Konstruktor kanoniczny znajdujący się w linii 3 sprawdza dodatkowo czy podany kolor jest poprawny.
// Konstruktor domyślny z linii 9 generuje kolor z wartością alpha = 0.

