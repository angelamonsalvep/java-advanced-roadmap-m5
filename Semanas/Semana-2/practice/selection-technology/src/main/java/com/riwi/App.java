package com.riwi;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el Departamento al que pertenece (D para Desarrollo y M para Marketing): ");
        char dpto = sc.next().toUpperCase().charAt(0);
        int nivelR;

        /*switch(dpto){
            case 'D':
                System.out.println("Seleccionaste el departamento de Desarrollo");
                System.out.println("Ingrese el nivel de rendimiento (1. Estándar y 2:Premium) ");
                nivelR = sc.nextInt();

                switch(nivelR){
                    case 1:
                        System.out.println("Tu equipo asignado es: Laptop i7, 16GB RAM, Monitor 24\"");
                        break;
                    case 2:
                        System.out.println("Tu equipo asignado es: MacBook Pro M3, 32GB RAM, Monitor 32\" 4K");
                        break;
                    default:
                        System.out.println("Nivel de rendimiento inválido");
                        break;
                }
                break;
            case 'M':
                System.out.println("Seleccionaste el departamento de Marketing");
                System.out.println("Ingrese el nivel de rendimiento (1. Estándar y 2:Premium) ");
                nivelR = sc.nextInt();

                switch(nivelR){
                    case 1:
                        System.out.println("Tu equipo asignado es: Laptop i5, 8GB RAM, Tablet básica");
                        break;
                    case 2:
                        System.out.println("Tu equipo asignado es: iMac 24\", 16GB RAM, Tableta Digitalizadora Pro");
                        break;
                    default:
                        System.out.println("Nivel de rendimiento inválido");
                        break;
                }
                break;
            default:
                System.out.println("Departamento no reconocido");
                break;
        }*/

        String response = switch(dpto){
            case 'D' -> {
                System.out.println("Ingrese el nivel de rendimiento (1. Estándar y 2:Premium) ");
                nivelR = sc.nextInt();

                yield switch(nivelR){
                    case 1 -> "Laptop i7, 16GB RAM, Monitor 24\\\"";
                    case 2 -> "MacBook Pro M3, 32GB RAM, Monitor 32\\\" 4K";
                    default -> "Nivel de rendimiento inválido";
                };
            }
            case 'M' -> {
                System.out.println("Ingrese el nivel de rendimiento (1. Estándar y 2:Premium) ");
                nivelR = sc.nextInt();

                yield switch(nivelR){
                    case 1 -> "Laptop i5, 8GB RAM, Tablet básica";
                    case 2 -> "iMac 24\", 16GB RAM, Tableta Digitalizadora Pro";
                    default -> "Nivel de rendimiento inválido";
                };
            }
            default -> "Departamento no reconocido";
        };
        System.out.println("El dispositivo asignado es: " + response);
        sc.close();

    }
}
