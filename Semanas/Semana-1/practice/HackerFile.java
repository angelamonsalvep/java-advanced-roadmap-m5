import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HackerFile {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Ingresa Usuario: ");
        String userInput = input.nextLine();

        System.out.println("Ingresa Clave: ");
        String passInput = input.next();

        //por defecto no tiene acceso
        boolean access = false;

        try{
            File file = new File("users.txt");
            Scanner inputFile = new Scanner(file);
            while(inputFile.hasNextLine()){
                String line = inputFile.nextLine();
                String[] parts = line.split(",");
                String user = parts[0];
                String pass = parts[1];
                if(user.equals(userInput) && pass.equals(passInput)){
                    access = true;
                    break;
                }
            }
            inputFile.close();
        }catch(FileNotFoundException e){
            System.out.println("Error: " + e);
        }
        if(access){
            System.out.println("Access Granted");
        }else{
            System.out.println("Access Denied");
        }
        input.close();   

    }
}
