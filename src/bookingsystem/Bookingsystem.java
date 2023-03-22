package bookingsystem;

import java.util.Scanner;

public class Bookingsystem {
    //GLOBAL VARIABLES
       static Scanner sc = new Scanner(System.in);
       static String userInput = "";
       
       
    public static void main(String[] args) {
        int convert = 0;
        while(true){
            try{
                System.out.println("MENU:");
                System.out.println("{1] BOOK SEAT");
                System.out.println("[2] AVAILABLE SEATS");
                System.out.println("[3] PROFIT");
                System.out.println("[4] END PROGRAM");
                System.out.println("INPUT:");
                userInput = sc.nextLine();
                convert = Integer.parseInt(userInput);
            }catch(Exception e){
                System.out.println("INVALID INPUT");
            }
            switch(convert){
                case 1:
                    bookSeat();
                case 2:
                    availableSeat();
                case 3:
                    profit();
                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("INAVLID INPUT");
                    continue;
            }           
        }
    }
    
    static void bookSeat(){
    
    }
    
    static void availableSeat(){
    
    }
    
    static void profit(){
    
 }
    
}
