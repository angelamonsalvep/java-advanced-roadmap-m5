import com.startup.models.Empleado;
import com.startup.utils.CalculadoraNomina;

public class Main {

    public static void main(String[] args) {
        // 1. Representación Visual con Text Block
        String banner = """
                ******************************************
                * CORPORATE TALENT HUB v1.0              *
                * Gestionando el futuro del código       *
                ******************************************               
                """;
        System.out.println(banner);
        Empleado dev = new Empleado("Luis", 123, 2000, true);
        if(dev.esRemoto() && dev.id() % 2 == 0){
            System.out.println("Empleado " + dev.nombre() + " asignado a Sede Virtual Par.");
        }

        // 3. Cálculo de Nómina
        double neto = CalculadoraNomina.calcularNeto(dev.salarioBase(), 500);
        System.out.println("Salario Neto: " + neto);

    }

}
