package mx.unam.biblioteca;

import mx.unam.biblioteca.backend.Biblioteca;
import mx.unam.biblioteca.frontend.BibliotecaGUI;

public class Principal {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        BibliotecaGUI gui = new BibliotecaGUI(biblioteca);
        biblioteca.setGUI(gui);
    }
}
