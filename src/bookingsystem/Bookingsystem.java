package bookingsystem;

import java.util.Scanner;

public class Bookingsystem {
    //GLOBAL VARIABLES
       static Scanner sc = new Scanner(System.in);
       static String userInput = "";
       
    public static void main(String[] args) {
        int convert = 0;
        int[] seatPlaces = new int[22];
        while(true){
            try{
                System.out.println("\nMENU:");
                System.out.println("{1] BOOKING");
                System.out.println("[2] AVAILABLE SEATS");
                System.out.println("[3] PROFIT");
                System.out.println("[4] END PROGRAM");
                System.out.print("INPUT: ");
                userInput = sc.nextLine();
                convert = Integer.parseInt(userInput);
            }catch(Exception e){
                System.out.println("\nINVALID INPUT");
                continue;
            }
            switch(convert){
                case 1:
                    seatPlaces =  booking(seatPlaces);
                    continue;
                case 2:
                    availableSeat(seatPlaces);
                    continue;
                case 3:
                    profit(seatPlaces);
                    continue;
                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("\nINAVLID INPUT");
                    continue;
            }           
        }
    }
    
    static int[] booking(int [] seatPlaces){
        while(true){
            try{
                System.out.println("\nBOOKING:");
                System.out.println("[1] BOOK SEAT");
                System.out.println("[2] FIND BOOKING");
                System.out.println("[3] CANCEL BOOKING");
                System.out.println("[4] RETURN TO MENU");
                System.out.print("INPUT: ");
                userInput = sc.nextLine();
            }catch(Exception e){
                System.out.println("\nINVALID INPUT");
                continue;
            }
            if(userInput.equals("1")){
                seatPlaces = bookSeat(seatPlaces);
            }else if(userInput.equals("2")){
                findBooking(seatPlaces);
            }else if(userInput.equals("3")){
                seatPlaces = cancelBooking(seatPlaces);
            }else if(userInput.equals("4")){
                main(null);
            }else{
                System.out.println("\nINVALID INPUT");
                continue;
            } 
            return seatPlaces;
        }
    }
    
    static int[] bookSeat(int [] seatPlaces){
        int socialNumber;
        while(true){
            try{
                System.out.print("\nENTER YOUR SOCIAL NUMBER [YYMMDD]: ");
                socialNumber = sc.nextInt();
            }catch(Exception e){
                System.out.println("\nINVALID INPUT");
                sc.nextLine();
                continue;
            }
            if(socialNumber < 000101 || socialNumber >991231){
                System.out.println("\nINVALID INPUT");
                sc.nextLine();
                continue;
            }
            break;
        }
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == 0){
                seatPlaces[i] = socialNumber;
                System.out.println("YOUR SEAT NUMBER IS: "+i);
                break;
            }
        }
        System.out.print("PRESS ENTER TO CONFIRM");
        sc.nextLine();
        sc.nextLine();
        return seatPlaces;
    }
    
    static void findBooking(int [] seatPlaces){
        int age = 0;
        int foundSeat = 0;
        int socialNumber;
        while(true){
            try{
                System.out.print("\nENTER YOUR SOCIAL NUMBER [YYMMDD]: ");
                socialNumber = sc.nextInt();
                System.out.println("ENTER YOUR AGE:");
                
            }catch(Exception e){
                System.out.println("\nINVALID INPUT");
                sc.nextLine();
                continue;
            }
            if(socialNumber < 000101 || socialNumber >991231){
                System.out.println("\nINVALID INPUT");
                sc.nextLine();
                continue;
            }
            break;
        }
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == socialNumber){
                foundSeat++;
                System.out.println("BOOKED SEAT NUMBER: "+i);
                break;
            }
        }
        if(foundSeat == 0){
            System.out.println("NO SEAT IS ASSOCIATED WITH GIVEN SOCIAL NUMBER");
        }
        System.out.print("\nPRESS ENTER TO CONFIRM");
        sc.nextLine();
        sc.nextLine();
    }
    
    static int[] cancelBooking(int [] seatPlaces){
        int foundSeat = 0;
        int convert = 0;
        int socialNumber;
        while(true){
            try{
                System.out.print("\nENTER YOUR SOCIAL NUMBER [YYMMDD]: ");
                socialNumber = sc.nextInt();
            }catch(Exception e){
                System.out.println("\nINVALID INPUT");
                sc.nextLine();
                continue;
            }
            if(socialNumber < 000101 || socialNumber >991231){
                System.out.println("\nINVALID INPUT");
                sc.nextLine();
                continue;
            }
            break;
        }
        
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == socialNumber){
                foundSeat++;
                while(true){
                    try{
                        System.out.println("DO YOU WANT TO CANCEL YOUR SEAT?");
                        System.out.println("[1] CONFIRM");
                        System.out.println("[2] DECLINE");
                        System.out.print("INPUT: ");
                        sc.nextLine();
                        userInput = sc.nextLine();
                        convert = Integer.parseInt(userInput);
                    }catch(Exception e){
                        System.out.println("\nINAVLID INPUT");
                        continue;
                    } 
                    
                    switch (convert){
                        case 1:
                            System.out.println("YOUR SEAT HAS BEEN SUCCESFULLY CANCELD");
                            seatPlaces[i] = 0;
                            break;
                        case 2:
                            main(null);
                        default:
                            System.out.println("\nINAVLID INPUT");
                            continue;
                    }
                break;
                }   
            }
        }
        if(foundSeat == 0){
                System.out.println("NO SEAT IS ASSOCIATED WITH GIVEN SOCIAL NUMBER");
            }
        System.out.print("\nPRESS ENTER TO CONFIRM");
        sc.nextLine();
        return seatPlaces;
    }
 
    static void availableSeat(int [] seatPlaces){
        int availableSeats = 0;
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == 0){
                availableSeats++;
            }
        }
        if(availableSeats == 0){
            System.out.println("THE BUSS IS FULL");
        }else{
            System.out.println("AVAILABLE SEATS: "+availableSeats);
        }
        System.out.print("PRESS ENTER TO RETURN");
        sc.nextLine();
    }
    
    static void profit(int [] seatPlaces){
        int takenSeats = 0;
        final double price = 299.90;
        double profit = 0;
        for(int i = 1; i < seatPlaces.length;i++){
            if(seatPlaces[i] != 0){
                takenSeats++;
            }        
        }
        profit = price*takenSeats;
        System.out.println("TOTAL PROFIT: "+profit+"kr");
        System.out.print("PRESS ENTER TO PROCCED");
        sc.nextLine();
    }
    
}
