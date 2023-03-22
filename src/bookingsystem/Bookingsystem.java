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
                System.out.print("INPUT: ");
                userInput = sc.nextLine();
                convert = Integer.parseInt(userInput);
            }catch(Exception e){
                System.out.println("INVALID INPUT");
            }
            switch(convert){
                case 1:
                    bookSeat();
                    continue;
                case 2:
                    availableSeat();
                    continue;
                case 3:
                    profit();
                    continue;
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
        int[] seatPlaces = new int[22];
        int socialNumber = 0;
        
        while(true){
            try{
                System.out.print("ENTER YOUR SOCIAL NUMBER [YYMMDD]: ");
                socialNumber = sc.nextInt();
            }catch(Exception e){
                System.out.println("INVALID INPUT");
                continue;
            }
            if(socialNumber < 000101 || socialNumber >991231){
                System.out.println("INVALID INPUT");
                continue;
            }
            break;
        }
        
        System.out.println(seatPlaces[1]);
        for(int i = 1;i < seatPlaces.length;i++){
            
            if(seatPlaces[i] == 0){
                seatPlaces[i] = socialNumber;
                System.out.println("YOUR SEAT NUMBER IS: "+i);
                break;
            }
        }
        System.out.print("PRESS ENTER TO RETURN");
        sc.nextLine();
        sc.nextLine();
        
    }
    
    static void availableSeat(){
    
    }
    
    static void profit(){
    
 }
    
}
