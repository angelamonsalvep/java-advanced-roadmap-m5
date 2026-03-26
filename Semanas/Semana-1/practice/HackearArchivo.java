import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HackearArchivo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese el usuario:");
        String userInput = input.next();

        System.out.println("Ingrese la clave:");
        String passInput = input.next();

        boolean access = false;

        try{
            File usersFile = new File("users.txt");

            Scanner inputFile = new Scanner(usersFile);
            while(inputFile.hasNextLine()){
                String user = inputFile.nextLine();
                String[] userPart = user.split(",");
                String nameUser = userPart[0].trim();
                String passUser = userPart[1].trim();

                if(nameUser.equals(userInput) && passUser.equals(passInput)){
                    access = true;
                    break;
                }
            }
            inputFile.close();

        }catch(FileNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }

        if(access){
            System.out.println("Acceso Concedido");
        }else{
            System.out.println("Acceso Denegado");
        }
        input.close();

    }
    
}
