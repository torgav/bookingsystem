package bookingsystem;

import java.util.Scanner;

public class Bookingsystem {
    //GLOBAL VARIABLES
       static Scanner sc = new Scanner(System.in);
       static String userInput = "";
       static int[] seatPlaces = new int[22];
       static String [] firstNameList = new String [22];
       static String [] lastNameList = new String [22];
       
    public static void main(String[] args) {
        int choices = 4;
        int input;
        while(true){
            System.out.println("\nMENU:");
            System.out.println("{1] BOOKING");
            System.out.println("[2] AVAILABLE SEATS");
            System.out.println("[3] FUNCTIONS");
            System.out.println("[4] END PROGRAM");
            input = inputControl(choices);
            if(input == -1){
                continue;
            }
            
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
            if(input == -1){
                continue;
            }
            
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
        int choices = 4;
        while(true){
            System.out.println("\nFUNCTIONS:");
            System.out.println("[1] PROFIT");
            System.out.println("[2] AGE LIST");
            System.out.println("[3] SORTED LIST");
            System.out.println("[4] RETURN TO MENU");
            int input = inputControl(choices);
            if(input == -1){
                continue;
            }
            
            switch(input){
                case 1:
                    System.out.println("\nPROFIT: "+profit(0)+" KR");
                    System.out.print("PRESS ENTER TO RETURN");
                    sc.nextLine();
                    break;
               
                case 2:
                    list(); 
                    break;
                case 3:
                    sortedList();
                    break;
            }
            break;
        }
    }
    
    static void bookSeat(){
        int input = 0;
        int choices = 2;
        int socialNumber = 0;
        while(true){
            System.out.print("\nENTER YOUR SOCIAL NUMBER [YYYYMMDD]: ");
            userInput = sc.nextLine();
            socialNumber = socialInformationControl(userInput);
            if(socialNumber == -1){
                continue;
            }else{  
                break;
            }
        }
        System.out.print("FIRST NAME: ");
        String firstName = sc.nextLine();
        System.out.print("LAST NAME: ");
        String lastName = sc.nextLine();
        System.out.print("KÖN: ");
        sc.nextLine();
        
        while(true){
            System.out.println("\nBOOKING OPTIONS:");
            System.out.println("[1] BOOK ANY AVAILABLE SEAT");
            System.out.println("[2] BOOK A WINDOW SEAT");
            input = inputControl(choices);
            if(input == -1){
                continue;
            }
            break;
        }
        
        switch(input){
            case 1:
                for(int i = 1;i < seatPlaces.length;i++){
                    if(seatPlaces[i] == 0){
                        seatPlaces[i] = socialNumber;
                        firstNameList[i] = firstName;
                        lastNameList[i] = lastName;
                        System.out.println("YOUR SEAT NUMBER IS: "+i);
                        break;
                    }
                }
                break;
            case 2:
                bookWindowSeat(socialNumber, firstName, lastName);     
        }
        System.out.print("PRESS ENTER TO RETURN");
        sc.nextLine();
    }
    
    static void bookWindowSeat(int socialNumber, String firstName, String lastName){
        int seat = 0;
        int takenWindowSeats = 0;
        
        for(int i = 1; i < 6; i++){
            takenWindowSeats++;
            seat = 4*i - 3;
            if(seatPlaces[seat] == 0){
                seatPlaces[seat] = socialNumber;
                firstNameList[seat] = firstName;
                lastNameList[seat] = lastName;
                System.out.println("YOUR SEAT NUMBER IS: "+seat);
                break;
            }
            
            seat = 4*i;
            if(seatPlaces[seat] == 0){
                seatPlaces[seat] = socialNumber;
                firstNameList[seat] = firstName;
                lastNameList[seat] = lastName;
                System.out.println("YOUR SEAT NUMBER IS: "+seat);
                break;
            }
        }
    }
    
    static void findBooking(){
        int foundSeat = 0;
        while(true){
            try{
                System.out.print("\nENTER NAME OR SOCIAL NUMBER OF THE BOOKING: ");
                userInput = sc.nextLine();
                int socialNumber = socialInformationControl(userInput);
                for(int i = 1; i < seatPlaces.length;i++){
                    if(seatPlaces[i] == socialNumber){
                        foundSeat++;
                        System.out.println("YOUR SEAT NUMBER: "+i);
                    }
                }
            }catch(Exception e){
                for(int i = 1; i < firstNameList.length;i++){
                    if(userInput.equalsIgnoreCase(firstNameList[i])){
                        foundSeat++;
                        System.out.println("YOUR SEAT NUMBER: "+i);
                    }
                }
            }
            if(foundSeat == 0){
                System.out.println("NO SEAT IS ASSOCIATED WITH GIVEN SOCIAL INFORMTION");
                break;
            }
        }
        System.out.print("\nPRESS ENTER TO RETURN");
        sc.nextLine();
    }
    
    static void cancelBooking(){
        int foundSeat = 0;
        int convert = 0;
        int seatPosition = 0;
        
        while(true){
            try{
                System.out.print("\nENTER NAME OR SOCIAL NUMBER OF THE BOOKING: ");
                userInput = sc.nextLine();
                int socialNumber = socialInformationControl(userInput);
                for(int i = 1;i < seatPlaces.length;i++){
                    if(seatPlaces[i] == socialNumber){
                        foundSeat++;
                        seatPosition = i;
                    }
                }

            }catch(Exception e){
                for(int i = 1; i < firstNameList.length;i++){
                    if(userInput.equalsIgnoreCase(firstNameList[i])){
                        foundSeat++;
                        seatPosition = i;
                    }
                }
            }
            break;
        }
        
        if(foundSeat == 1){
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
                        seatPlaces[seatPosition] = 0;
                        firstNameList[seatPosition] = "";
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
        }else if(foundSeat == 0){
            System.out.println("NO SEAT IS ASSOCIATED WITH GIVEN SOCIALS");
        }
        
        System.out.print("\nPRESS ENTER TO RETURN");
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
    
    static double profit(int index){
        final double youngProfit = 149.90;
        final double adultProfit = 299.90;
        final double seniorProfit = 199.90;
        double profit = 1;
        
        if(index == 21){
            return 0;
        }
        
        int age = seatPlaces[index];
        if(age == 0){
            return 0 + profit(index + 1);
        }
        
        String tempYear = Integer.toString(age).substring(0, 4);
        int year = Integer.parseInt(tempYear);
        year = 2023 - year;

        if(year < 18){
           profit =  profit * youngProfit;
        }else if(year > 17 && year < 65 ){
            profit =  profit * adultProfit;
        }else if(year < 64){
             profit =  profit * seniorProfit;
        }
        
        return profit + profit(index + 1);
    }
    
    static int inputControl(int choices){
        int input;
        int convertVariable = -1;
        while(true){
            try{
                System.out.print("INPUT: ");
                userInput = sc.nextLine();
                convertVariable = Integer.parseInt(userInput);
            }catch(NumberFormatException e){
                System.out.println("INVALID INPUT");
                return convertVariable;
            }
            if(convertVariable > choices){
                System.out.println("INVALID INPUT");
                return convertVariable;
            }
            input = convertVariable;
            return input;
        }
    }
    
    static int socialInformationControl(String input){
        int year;
        int month;
        int day;
        int convertVariable = -1;
        
        while(true){
            try{
            String temp1 = input.substring(0, 4);
            String temp2 = input.substring(4, 6);
            String temp3 = input.substring(6, 8);

            
            year = Integer.parseInt(temp1);
            month = Integer.parseInt(temp2);
            day =  Integer.parseInt(temp3);
            
            }catch(Exception e){
                System.out.println("INVALID INPUT");
                return convertVariable;
            }

            if(month > 12 ){
                System.out.println("INVALID INPUT");
                return convertVariable;
            }else if(day > 31){
                System.out.println("INVALID INPUT");
                return convertVariable;
            }else if(year > 2023){
                System.out.println("INVALID INPUT");
                return convertVariable;            
            }
            convertVariable = Integer.parseInt(input);
            return convertVariable;
        }
    }
    
    static void list(){
        int[] tempAgeList = new int [22];
        int[] tempSeatList = new int [22];
        int found = 0;
        
        for(int i = 1; i < seatPlaces.length; i++){
            tempAgeList[i] = seatPlaces[i];
        }
        
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
    
    static void sortedList(){
        int[] tempSeatList = new int [22];
        int[] tempAgeList = new int [22];
        String [] tempFirstNameList = new String [22];
        String [] tempLastNameList = new String [22];
        int found = 0;
        
        for(int i = 1; i < seatPlaces.length; i++){
            tempAgeList[i] = seatPlaces[i];
            tempFirstNameList[i] = firstNameList[i];
            tempLastNameList[i] = lastNameList[i];
        }
        
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
                    
                    String textTemp = tempFirstNameList[j];
                    tempFirstNameList[j] = tempFirstNameList[j + 1];
                    tempFirstNameList[j + 1] = textTemp;
                    
                    textTemp = tempLastNameList[j];
                    tempLastNameList[j] = tempLastNameList[j + 1];
                    tempLastNameList[j + 1] = textTemp;
                }
            }
        }
        System.out.println("\nSORTED LIST:");
        for(int i = 1; i < 22; i++){
            if(tempAgeList[i] != 0){
                found++;
                System.out.println(tempFirstNameList[i]+ " "+ tempLastNameList[i]+ " "+tempAgeList[i]+ " Seat: "+ tempSeatList[i]);
            }
        }
        if(found == 0){
            System.out.println("NO DATA");
        }
         
        System.out.print("\nPRESS ENTER TO RETURN");
        sc.nextLine(); 
    }
}
 