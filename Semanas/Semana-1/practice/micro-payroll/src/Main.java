import com.startup.models.*;
import com.startup.utils.CalculadoraNomina;

public class Main {
    public static void main(String[] args) {
        // 1. Representación Visual con Text Block (Día 2)
        String banner = """
            ******************************************
            * CORPORATE TALENT HUB v1.0         *
            * Gestionando el futuro del código    *
            ******************************************
            """;
        System.out.println(banner);

        // 2. Instanciación y Lógica de Cortocircuito (Día 3)
        Empleado dev = new Empleado("Coder Java", 101, 3000.0, true);
        
        // Ejemplo de Cortocircuito: Si no es remoto, ni siquiera evalúa el ID
        if (dev.esRemoto() && dev.id() % 2 == 0) {
            System.out.println("Empleado " + dev.nombre() + " asignado a Sede Virtual Par.");
        }

        // 3. Cálculo de Nómina
        double neto = CalculadoraNomina.calcularNeto(dev.salarioBase(), 500);
        System.out.println("Salario Neto Final: $" + neto);

        // 4. Prueba de Memoria
        CalculadoraNomina.demostrarOverflow();
    }
}
