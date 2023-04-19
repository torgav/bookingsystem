package bookingsystem;

import java.util.Scanner;

public class Bookingsystem {
    //GLOBAL VARIABLES
       static Scanner sc = new Scanner(System.in);
       static String userInput = "";
       static int[] seatPlaces = new int[22];
       
    public static void main(String[] args) {
        int choices = 4;
        String [] firstNameList = new String [22];
        String [] lastNameList = new String [22];
        
        int input;
        while(true){
            System.out.println("\nMENU:");
            System.out.println("{1] BOOKING");
            System.out.println("[2] AVAILABLE SEATS");
            System.out.println("[3] FUNCTIONS");
            System.out.println("[4] END PROGRAM");
            input = inputControl(choices);
            
            switch(input){
                case 1:
                    booking();
                    continue;
                case 2:
                    availableSeat();
                    continue;
                case 3:
                    functions();
                    continue;
                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("\nINAVLID INPUT");
            }           
        }
    }
    
    static int[] booking(){
        int choices = 4;
        while(true){
            System.out.println("\nBOOKING:");
            System.out.println("[1] BOOK SEAT");
            System.out.println("[2] FIND BOOKING");
            System.out.println("[3] CANCEL BOOKING");
            System.out.println("[4] RETURN TO MENU");
            int input = inputControl(choices);
            
            switch(input){
                case 1 -> bookSeat();
                case 2 -> findBooking();
                case 3 -> cancelBooking();
                case 4 -> {
                }
            }
            return seatPlaces;
        }
    }
    
    static void functions(){
        int choices = 3;
        while(true){
            System.out.println("\nFUNCTIONS:");
            System.out.println("[1] PROFIT");
            System.out.println("[2] AGE LIST");
            System.out.println("[3] RETURN TO MENU");
            int input = inputControl(choices);
            
            switch(input){
                case 1 -> profit();
                case 2 -> list();   
                case 3 -> {
                }
            }
            break;
        }
    }
    
    static void bookSeat(){
        int input = 0;
        int choices = 2;
        
        int socialNumber = socialNumberControl();
        System.out.println("\nBOOKING OPTIONS:");
        System.out.println("[1] BOOK ANY AVAILABLE SEAT");
        System.out.println("[2] BOOK A WINDOW SEAT");
        sc.nextLine();
        input = inputControl(choices);
        
        switch(input){
            case 1:
                for(int i = 1;i < seatPlaces.length;i++){
                    if(seatPlaces[i] == 0){
                        seatPlaces[i] = socialNumber;
                        System.out.println("YOUR SEAT NUMBER IS: "+i);
                        break;
                    }
                }
                break;
            case 2:
                bookWindowSeat(socialNumber);
                
        }
        System.out.print("PRESS ENTER TO CONFIRM");
        sc.nextLine();
    }
    
    static void bookWindowSeat(int socialNumber ){
        int seat = 0;
        int takenWindowSeats = 0;
        
        for(int i = 1; i < 6; i++){
            takenWindowSeats++;
            seat = 4*i - 3;
            if(seatPlaces[seat] == 0){
                seatPlaces[seat] = socialNumber;
                System.out.println("YOUR SEAT NUMBER IS: "+seat);
                break;
            }
            
            seat = 4*i;
            if(seatPlaces[seat] == 0){
                seatPlaces[seat] = socialNumber;
                System.out.println("YOUR SEAT NUMBER IS: "+seat);
                break;
            }
        }
    }
    
    static void findBooking(){
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
        sc.nextLine();
    }
    
    static void cancelBooking(){
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
    }
 
    static void availableSeat(){
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
    
    static void profit(){
        final double youngPrice = 149.90;
        final double normalPrice = 299.90;
        final double seniorPrice = 199.90;
        double totalProfit = 0;
        
        int [] passengersAge  = new int [3];
        passengersAge = passengersAgeControl(seatPlaces);
        double youngTotalProfit = (double) (youngPrice * passengersAge[0]);
        double normalTotalProfit = (double) (normalPrice * passengersAge[1]);
        double seniorTotalProfit = (double) (seniorPrice * passengersAge[2]);
        totalProfit = youngTotalProfit+normalTotalProfit+seniorTotalProfit;
        totalProfit = Math.round(totalProfit * 100);
        totalProfit /= 100;
        
        System.out.println("\nTOTAL PROFIT: "+totalProfit+"kr");
        System.out.print("PRESS ENTER TO PROCCED");
        sc.nextLine();
    }
    
    static int inputControl(int choices){
        int input;
        int convertVariable;
        while(true){
            try{
                System.out.print("INPUT: ");
                userInput = sc.nextLine();
                convertVariable = Integer.parseInt(userInput);
            }catch(NumberFormatException e){
                System.out.println("\nINVALID INPUT");
                continue;
            }
            if(convertVariable > choices){
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
        int input = 0;
        String socialNumber = "";
        
        while(true){
            try{
            System.out.print("\nENTER YOUR SOCIAL NUMBER [YYYYMMDD]: ");
            input = sc.nextInt();
            socialNumber = Integer.toString(input);
            
            String temp1 = socialNumber.substring(0, 4);
            String temp2 = socialNumber.substring(4, 6);
            String temp3 = socialNumber.substring(6, 8);

            
            year = Integer.parseInt(temp1);
            month = Integer.parseInt(temp2);
            day =  Integer.parseInt(temp3);
            
            }catch(Exception e){
                System.out.println("INVALID INPUT SOCIAL NUMBER");
                sc.nextLine();
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
    
    static int []passengersAgeControl(int [] seatPlaces){
        //[0] = young people
        //[1] = Adult people
        //[2] = Old people
        int[] ageArray = new int [3];
        
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] != 0){
                String socialNumber = Integer.toString(seatPlaces[i]);
                String temp1 = socialNumber.substring(0, 4);
                int birth = Integer.parseInt(temp1);
                int age = 2023-birth;
                if(age < 18){
                    ageArray[0]++;
                }else if(age >= 18 && age < 64){
                    ageArray[1]++;
                }else if(age >= 64){
                    ageArray[2]++;
                }
            }
        }
        return ageArray;
    }       
    
    static void list(){
        int[] tempAgeList = new int [22];
        int found = 0;
        
        for(int i = 1; i < seatPlaces.length; i++){
            tempAgeList[i] = seatPlaces[i];
        }
        
        int[] tempSeatList = new int [22];
        for(int i = 1; i < tempAgeList.length; i++){
            tempSeatList[i] = i;
        }
        
        for (int i = 1; i < tempAgeList.length - 1; i++) {
            for (int j = 0; j < tempAgeList.length - i - 1; j++) {
                if (tempAgeList[j] > tempAgeList[j + 1]) {
                    
                    int temp = tempAgeList[j];
                    tempAgeList[j] = tempAgeList[j + 1];
                    tempAgeList[j + 1] = temp;
                    
                    temp = tempSeatList[j];
                    tempSeatList[j] = tempSeatList[j + 1];
                    tempSeatList[j + 1] = temp;
                }
            }
        }
        
        //KOLLAR OM DE ÄR ÖVER 18
        System.out.println("\nOVER 18:");
        for(int i = 1; i < tempAgeList.length; i++){
            if(tempAgeList[i] != 0){
                found++;
                String socialNumber = Integer.toString(tempAgeList[i]);
                String temp1 = socialNumber.substring(0, 4);
                int birth = Integer.parseInt(temp1);
                int age = 2023-birth;
                if(age >= 18){
                    System.out.println("PRSNR: "+tempAgeList[i]+" SEAT: "+ tempSeatList[i]);
                }
            }
        }
        if(found == 0){
            System.out.println("NO DATA");
        }
        
        //KOLLA OM DE ÄR UNDER 18
        found = 0;
        System.out.println("\nUNDER 18:");
        
        for(int i = 1; i < tempAgeList.length; i++){
            if(tempAgeList[i] != 0){
                found++;
               String socialNumber = Integer.toString(tempAgeList[i]);
               String temp1 = socialNumber.substring(0, 4);
               int birth = Integer.parseInt(temp1);
               int age = 2023-birth;
               if(age < 18){
                   System.out.println("PRSNR: "+tempAgeList[i]+" SEAT: "+ tempSeatList[i]);
               }
            }
        }
        if(found == 0){
            System.out.println("NO DATA");
        }
         
        System.out.print("\nPRESS ENTER TO RETURN");
        sc.nextLine(); 
    }
    /*
    static void socialInformation(){
        System.out.println("FIRST NAME:");
        String firstName = sc.nextLine();
        System.out.println("LAST NAME:");
        String lastName = sc.nextLine();
        System.out.println("GENDER:");
        String gender = sc.nextLine();
    }
    */
        
}
 