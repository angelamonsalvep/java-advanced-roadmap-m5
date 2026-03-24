package core;

public class EmpleadoLegacy {
    public static void main(String[] args) {
        // Un byte solo aguanta hasta 127
        byte n = 127;
        n = (byte)(n + 1); // Forzamos desbordamiento manual

        System.out.println("----------------------------------------");
        System.out.println("Prueba de Desbordamiento de Primitivos:");
        System.out.println("127 + 1 en un byte de Java es: " + n);
        System.out.println("¿Por qué es negativo? Investígalo.");
        System.out.println("----------------------------------------");
    }
}
