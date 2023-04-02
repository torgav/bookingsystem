package bookingsystem;

import java.util.Scanner;

public class Bookingsystem {
    //GLOBAL VARIABLES
       static Scanner sc = new Scanner(System.in);
       static String userInput = "";
      
    public static void main(String[] args) {
        int input = 0;
        int[] seatPlaces = new int[22];
        while(true){
            System.out.println("\nMENU:");
            System.out.println("{1] BOOKING");
            System.out.println("[2] AVAILABLE SEATS");
            System.out.println("[3] PROFIT");
            System.out.println("[4] END PROGRAM");
            input = inputControl();
            
            switch(input){
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
            System.out.println("\nBOOKING:");
            System.out.println("[1] BOOK SEAT");
            System.out.println("[2] FIND BOOKING");
            System.out.println("[3] CANCEL BOOKING");
            System.out.println("[4] RETURN TO MENU");
            int input = inputControl();
            
            switch(input){
                case 1:
                    seatPlaces = bookSeat(seatPlaces);
                    break;
                case 2:
                    findBooking(seatPlaces);
                    break;
                case 3:
                    seatPlaces = cancelBooking(seatPlaces);
                    break;
                case 4:
                    main(null);
            }
            return seatPlaces;
        }
    }
    
    static int[] bookSeat(int [] seatPlaces){
        int socialNumber = socialNumberControl();
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == 0){
                seatPlaces[i] = socialNumber;
                System.out.println("YOUR SEAT NUMBER IS: "+i);
                break;
            }
        }
        System.out.print("PRESS ENTER TO CONFIRM");
        sc.nextLine();
        return seatPlaces;
    }
    
    static void findBooking(int [] seatPlaces){
        int foundSeat = 0;
        int socialNumber;
        socialNumber = socialNumberControl();
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == socialNumber){
                foundSeat++;
                System.out.println("BOOKED SEAT NUMBER: "+i);
            }
        }
        if(foundSeat == 0){
            System.out.println("NO SEAT IS ASSOCIATED WITH GIVEN SOCIAL NUMBER");
        }
        System.out.print("\nPRESS ENTER TO CONFIRM");
        sc.nextLine();
    }
    
    static int[] cancelBooking(int [] seatPlaces){
        int foundSeat = 0;
        int convert = 0;
        int socialNumber = socialNumberControl();
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == socialNumber){
                foundSeat++;
                while(true){
                    try{
                        System.out.println("DO YOU WANT TO CANCEL YOUR SEAT?");
                        System.out.println("[1] CONFIRM");
                        System.out.println("[2] DECLINE");
                        System.out.print("INPUT: ");
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
                            System.out.println("YOUR BOOKING IS KEEPT");
                            break;
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
            System.out.println("\nTHE BUSS IS FULL");
        }else{
            System.out.println("\nAVAILABLE SEATS: "+availableSeats);
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
        System.out.println("\nTOTAL PROFIT: "+profit+"kr");
        System.out.print("PRESS ENTER TO PROCCED");
        sc.nextLine();
    }
    
    static int inputControl(){
        int input;
        int convertVariable;
        while(true){
            try{
                System.out.print("INPUT: ");
                userInput = sc.nextLine();
                convertVariable = Integer.parseInt(userInput);
            }catch(Exception e){
                System.out.println("\nINVALID INPUT");
                continue;
            }
            if(convertVariable > 4){
                System.out.println("\nINVALID INPUT");
                continue;
            }
            input = convertVariable;
            return input;
        }
    }
    
    static int socialNumberControl(){
        int year;
        int month;
        int day;
        String socialNumber = "";
        
        while(true){
            try{
            System.out.print("\nENTER YOUR SOCIAL NUMBER [YYYYMMDD]: ");
            socialNumber = sc.nextLine();
            
            String temp1 = socialNumber.substring(0, 4);
            String temp2 = socialNumber.substring(4, 6);
            String temp3 = socialNumber.substring(6, 8);

            
                year = Integer.parseInt(temp1);
                month = Integer.parseInt(temp2);
                day =  Integer.parseInt(temp3);
            
            }catch(Exception e){
                System.out.println("INVALID INPUT SOCIAL NUMBER");
                continue;
            }

            if(month > 12 ){
                System.out.println("INVALID INPUT SOCIAL NUMBER");
                continue;
            }else if(day > 31){
                System.out.println("INVALID INPUT SOCIAL NUMBER");
                continue;
            }else if(year > 2023){
                System.out.println("INVALID INPUT SOCIAL NUMBER");
                continue;
            }
            int convert = Integer.parseInt(socialNumber);
            return convert;
        }
    }
    
    static void ageVerification(){
        int ageVerification = 2023;
            
    }
       
}
