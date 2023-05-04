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
        //Variabler
        //Choices är hur många val man har i denna situation och det skickas till inputControl
        //Då vet man att man kan inte stoppa in ettt nummer mer än 4
        int choices = 4;
        int input;
        /*
        Detta är min main metod som är centrum till min booknings system, andvändaren får val att välja mellan och inmatningen de anger
        Skickas till en annan metod som checkar allting är korrekt. Därefter så använder jag en switch case som anropar en annan metod
        som väljaren har vallt. Det är i en while loop så att om något fel händer med inputen så skickas meddelandet om menu igen och varning.
        */
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
        //Samma koncept som min main metod.
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
        //Samma koncept igen som mimn main metod, det gör allting mer organiserad än att göra allting fullt i en main.
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
            
            //Dock i denna så har vi en bit kod i profit metoden. om man vill kolla profit så är den rekursion så den skickar allting tillbaks.
            //Vi lägger då lite kod för metoden som inte kunna vara i själv metoen.
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
        //Variabler
        int input = 0;
        int choices = 2;
        int socialNumber = 0;
        int foundSeat = 0;
        boolean nameCheck = false;
        String firstName = "";
        String lastName = "";
        String gender = "";
        /* 
            Här bokar man sin plats med sitt id, namn etc. 
            Då frågar mitt program för det och checkar om allting stämmer med det.
            Det kollar det inom en annan metod.
        */
        while(true){
            System.out.print("\nENTER YOUR SOCIAL NUMBER [YYYYMMDD]: ");
            userInput = sc.nextLine();
            socialNumber = socialInformationControl(userInput, nameCheck);
            if(socialNumber == -1){
                continue;
            }else{  
                break;
            }
        }
        while(true){
            System.out.print("FIRST NAME: ");
            firstName = sc.nextLine();
            if(firstName.length() == 0){
                System.out.println("\nGIVE FULL NAME\n");
                continue;
            }  
            break;
        }
        while(true){
            System.out.print("LAST NAME: ");
            lastName = sc.nextLine();
            if(lastName.length() == 0){
                System.out.println("\nGIVE FULL LAST NAME\n");
                continue;
            }
            break;
        }
        
        System.out.print("KÖN [M | F | A]: ");
        gender = sc.nextLine();
        
        //Här så får mn välja mellan normal random sätte eller just fönster plats.
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
        
        //Här är de små koderna spom bestämmer beroene på ditt val.
        switch(input){
            case 1:
                /*
                Vi går igenom min plats array som håller koll på natal platser, och kollr om innehållet av den indexen
                Är lika med 0, då är den tom.
                Då när den är tom så lägger vi till allt information från användaren till den indexen till varje array.
                Och på det sättet är alla arrays i sync och kan användas för att hitta och skriva ut ikorrekt form.
                */
                for(int i = 1;i < seatPlaces.length;i++){
                    if(seatPlaces[i] == 0){
                        foundSeat++;
                        seatPlaces[i] = socialNumber;
                        firstNameList[i] = firstName;
                        lastNameList[i] = lastName;
                        System.out.println("YOUR SEAT NUMBER IS: "+i);
                        break;
                    }
                }
                //OM inget sätte hittas så skickar vi ett meddelande
                if(foundSeat == 0){
                    System.out.println("THERE IS NO AVAILABLE SEATS");
                }
                break;
            case 2:
                //Om man väljer fönsterplats så skickas allt info till den metoden.
                bookWindowSeat(socialNumber, firstName, lastName);     
        }
        System.out.print("PRESS ENTER TO RETURN");
        sc.nextLine();
    }
    
    static void bookWindowSeat(int socialNumber, String firstName, String lastName){
        int seat = 0;
        int takenWindowSeats = 0;
        int foundSeat = 0;
        /*
            I denna metod hittar min algorithm en fönster plats som var vald av johan. 
            Så hur jag gjorde va genom funktioner. Vi har ju platserna som är ojämna som går up med plus 4.
            Och sedan den andra som går upp vara med 4. Då börjar jag med att kolla på 1 platsen genom funktionen och sedan 4 platsen
            genom den andra funktioner.
            Då om någon av dessa fönsterplatser är lika med 0 så är den tom och då överför vi infon till den indexen i alla arrays.
        */
        for(int i = 1; i < 6; i++){
            takenWindowSeats++;
            seat = 4*i - 3;
            if(seatPlaces[seat] == 0){
                foundSeat++;
                seatPlaces[seat] = socialNumber;
                firstNameList[seat] = firstName;
                lastNameList[seat] = lastName;
                System.out.println("YOUR SEAT NUMBER IS: "+seat);
                break;
            }
            
            seat = 4*i;
            //Skippar 20 platsen för att den är inte en fönster plats
            if(seat == 20){   
            }else{
                if(seatPlaces[seat] == 0){
                    foundSeat++;
                    seatPlaces[seat] = socialNumber;
                    firstNameList[seat] = firstName;
                    lastNameList[seat] = lastName;
                    System.out.println("YOUR SEAT NUMBER IS: "+seat);
                    break;
                }
            }
        }
        if(foundSeat == 0){
            System.out.println("THERE IS NO AVAILABLE SEATS");
        }
    }
    
    static void findBooking(){
        int foundSeat = 0;
        boolean nameCheck = true;
        //Hitta boknings metoden med id elle namn.
        System.out.print("\nENTER NAME OR SOCIAL NUMBER OF THE BOOKING: ");
        userInput = sc.nextLine();

        //Skickar inmatningen från denna metod till en annan och om den skickar tillbaks -1.
        int socialNumber = socialInformationControl(userInput, nameCheck);

        //Så vet vi att användaren skrev ett namn elle text, och då kollar vi om texten är matchad till någon plats med hjälp av vårn namn array.
        if(socialNumber == -1){
            for(int i = 1; i < firstNameList.length;i++){
                if(userInput.equalsIgnoreCase(firstNameList[i])){
                    foundSeat++;
                    System.out.println("YOUR SEAT NUMBER: "+i);
                }
            } 
        }else{
            //Om vi får tillbaks ett personnummer så så kollar vi om den finns i plats arrayn. 
            for(int i = 1; i < seatPlaces.length;i++){
                if(seatPlaces[i] == socialNumber){
                    foundSeat++;
                    System.out.println("YOUR SEAT NUMBER: "+i);
                }
            }  
        }
        //OM inget hittas så skickar vi ett fel meddelande.
        if(foundSeat == 0){
            System.out.println("NO SEAT IS ASSOCIATED WITH GIVEN SOCIAL INFORMTION");
        }
        
        System.out.print("\nPRESS ENTER TO RETURN");
        sc.nextLine();
    }
    
    static void cancelBooking(){
        //Variabler
        int foundSeat = 0;
        int convert = 0;
        int seatPosition = 0;
        boolean nameCheck = true;
        
        //Vi får in namn elle id av personen.
        System.out.print("\nENTER NAME OR SOCIAL NUMBER OF THE BOOKING: ");
        userInput = sc.nextLine();
        //Först kollar vi om inputen var en nummer som då är ett personnummer.
        int socialNumber = socialInformationControl(userInput, nameCheck);
        //Om id matchar en i arrayn så lägger vi till +1 i foundSeat.
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == socialNumber){
                foundSeat++;
                seatPosition = i;
            }
        }

        //Om det är istället en text input kollar vi om det mtchar i min namn array.
        for(int i = 1; i < firstNameList.length;i++){
            if(userInput.equalsIgnoreCase(firstNameList[i])){
                foundSeat++;
                seatPosition = i;
            }
        }
        
        //Om vi då har hittat en plats som matchade till inputen så frågar vi om konfirmation av avbokningen.
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
                        //Om du ccepterar så gör vi värden på den plats index = 0 & ""som betyder tomt.
                        System.out.println("YOUR SEAT HAS BEEN SUCCESFULLY CANCELD");
                        seatPlaces[seatPosition] = 0;
                        firstNameList[seatPosition] = "";
                        break;
                    case 2:
                        //Om inte gör vi inget.
                        System.out.println("YOUR BOOKING IS KEEPT");
                        break;
                    default:
                        System.out.println("\nINAVLID INPUT");
                        continue;
                }
                break;
            }
        //Om inget hittas ju så var inte det kopplat till något.
        }else if(foundSeat == 0){
            System.out.println("NO SEAT IS ASSOCIATED WITH GIVEN SOCIALS");
        }
        
        System.out.print("\nPRESS ENTER TO RETURN");
        sc.nextLine(); 
    }
 
    static void availableSeat(){
        int availableSeats = 0;
        //Väldigt simpel metod vi går igenom arrayn för pltser om vi hittar toma platser (0) så lägger vi +1 till min variabel.
        for(int i = 1;i < seatPlaces.length;i++){
            if(seatPlaces[i] == 0){
                availableSeats++;
            }
        }
        //Om det blir 0 så var alla platser bokade.
        if(availableSeats == 0){
            System.out.println("\nTHE BUSS IS FULL");
        }else{
            //Om det är inet fullt skriver vi ut antalet av lediga platser.
            System.out.println("\nAVAILABLE SEATS: "+availableSeats);
        }
        System.out.print("PRESS ENTER TO RETURN");
        sc.nextLine();
    }
    
    static double profit(int index){
        final double YOUNGPRICE = 149.90;
        final double ADULTPRICE = 299.90;
        final double SENIORPRICE = 199.90;
        double profit = 1;
        /*
            Denna metod är en rekurssion så varje gång vi anropar metoden så går vi +1 med varje anrop.
            Så det första vi börjar med är att index = 0.
            Vi kollar då om värdet på platsen i plts arrayn är lika med en riktigt år.
        */
        //m vi kommer till index 21 så måste vi avsluta metoden och retrunera allt.
        if(index == 21){
            return 0;
        }
        
        int age = seatPlaces[index];
        if(age == 0){
            return 0 + profit(index + 1);
        }
        
        //Om det är ett år så tar vi ut året av id och gör det minus 2023 för att få åldern
        String tempYear = Integer.toString(age).substring(0, 4);
        int year = Integer.parseInt(tempYear);
        year = 2023 - year;

        //Beronde på åldern lägger vi ett specikft pris till dem.
        if(year < 18){
           profit =  profit * YOUNGPRICE;
        }else if(year > 17 && year < 65 ){
            profit =  profit * ADULTPRICE;
        }else if(year < 64){
             profit =  profit * SENIORPRICE;
        }
        
        return profit + profit(index + 1);
    }
    
    static int inputControl(int choices){
        //Detta är en generel metod som kan anropas om igen till val av specikta nummer.
        //Vi får in chioices som är antalet vl man får göra i denna metod.
        int input;
        int convertVariable = -1;
        /*
        Vi retrunerar inputen som behövs vi kollar då om användaren stoppade in rätt sak.
        Vi tar in det och ändrar det till en int om det kraschar så vr et en ogiltig input och kör om.
        Där efter så kollar vi om nummret är under choices som är beroende på situationer.
        */
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
    
    static int socialInformationControl(String input, boolean nameCheck){
        int year;
        int month;
        int day;
        int convertVariable = -1;
        
        //Här är en till generl metod som används för att kolla id av personer.
        while(true){
            //Vi drar ut varje nummer allstå år, datum och dag.
            try{
            String temp1 = input.substring(0, 4);
            String temp2 = input.substring(4, 6);
            String temp3 = input.substring(6, 8);

            //Konverterar till nummer
            year = Integer.parseInt(temp1);
            month = Integer.parseInt(temp2);
            day =  Integer.parseInt(temp3);
            
            }catch(Exception e){
                if(nameCheck == false){
                    System.out.println("INVALID INPUT");
                }
                return convertVariable;
            }

            
            //Här checkar vi om det stämmer i korrekt format, datumt kan ju inte gå över 12.
            if(month > 12 ){
               if(nameCheck == false){
                    System.out.println("INVALID INPUT");
                }
                return convertVariable;
            }else if(day > 31){
                if(nameCheck == false){
                    System.out.println("INVALID INPUT");
                }
                return convertVariable;
            }else if(year > 2023){
               if(nameCheck == false){
                    System.out.println("INVALID INPUT");
                }
                return convertVariable;     
            }
            convertVariable = Integer.parseInt(input);
            return convertVariable;
        }
    }
    
    static void list(){
        // Skapa två temporära listor för ålder och sittplatsnummer.
        int[] tempAgeList = new int [22];
        int[] tempSeatList = new int [22];
        // En variabel för att hålla reda på antalet hittade passagerare.
        int found = 0;
        
        // Kopiera sittplatsnumren från huvudlistan till ålderslistan.
        for(int i = 1; i < seatPlaces.length; i++){
            tempAgeList[i] = seatPlaces[i];
        }
        
        // Fyllier i en sekventiell lista med siffror från 1 till 21 för att skapa en lista med sittplatsnummer.
        for(int i = 1; i < tempAgeList.length; i++){
            tempSeatList[i] = i;
        }
        
        // Bubble sort-algoritmen för att sortera ålderslistan och samtidigt byta plats på motsvarande sittplatsnummer i den andra listan.
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
        // Skriv ut alla passagerare över 18 år.
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
        
         // Skriv ut ett meddelande om det inte finns några passagerare som matchar kriterierna.
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
         // Skriv ut ett meddelande om det inte finns några passagerare som matchar kriterierna.
        if(found == 0){
            System.out.println("NO DATA");
        }
         
        System.out.print("\nPRESS ENTER TO RETURN");
        sc.nextLine(); 
    }
    
    static void sortedList(){
        // Skapa nya int-arrayer för temporära platser, åldrar och två nya String-arrayer för temporära förnamn och efternamn. Alla av storleken 22.
        int[] tempSeatList = new int [22];
        int[] tempAgeList = new int [22];
        String [] tempFirstNameList = new String [22];
        String [] tempLastNameList = new String [22];

        // Variabeln found används för att hålla koll på antalet personer som finns i listan.
        int found = 0;

        // Kopiera innehållet i seatPlaces, firstNameList och lastNameList till de temporära arrayerna.
        for(int i = 1; i < seatPlaces.length; i++){
            tempAgeList[i] = seatPlaces[i];
            tempFirstNameList[i] = firstNameList[i];
            tempLastNameList[i] = lastNameList[i];
        }

        // Fyll i tempSeatList med sittplatser från 1 till längden av tempAgeList-arrayen.
        for(int i = 1; i < tempAgeList.length; i++){
            tempSeatList[i] = i;
        }

        // Bubble sort-algoritmen används för att sortera tempAgeList-arrayen tillsammans med de andra tillhörande arrayerna.
        for (int i = 1; i < tempAgeList.length - 1; i++) {
            for (int j = 0; j < tempAgeList.length - i - 1; j++) {
                if (tempAgeList[j] > tempAgeList[j + 1]) {
                    // Byt plats på elementen i tempAgeList.
                    int temp = tempAgeList[j];
                    tempAgeList[j] = tempAgeList[j + 1];
                    tempAgeList[j + 1] = temp;

                    // Byt plats på elementen i tempSeatList.
                    temp = tempSeatList[j];
                    tempSeatList[j] = tempSeatList[j + 1];
                    tempSeatList[j + 1] = temp;

                    // Byt plats på elementen i tempFirstNameList.
                    String textTemp = tempFirstNameList[j];
                    tempFirstNameList[j] = tempFirstNameList[j + 1];
                    tempFirstNameList[j + 1] = textTemp;

                    // Byt plats på elementen i tempLastNameList.
                    textTemp = tempLastNameList[j];
                    tempLastNameList[j] = tempLastNameList[j + 1];
                    tempLastNameList[j + 1] = textTemp;
                }
            }
        }

        // Skriv ut sorterad lista.
        System.out.println("\nSORTED LIST:");
        for(int i = 1; i < 22; i++){
            if(tempAgeList[i] != 0){
                found++;
                System.out.println(tempFirstNameList[i]+ " "+ tempLastNameList[i]+ " "+tempAgeList[i]+ " Seat: "+ tempSeatList[i]);
            }
        }

        // Om inga poster i listan hittas, skriv ut "NO DATA".
        if(found == 0){
            System.out.println("NO DATA");
        }

        // Be användaren trycka på Enter för att återgå.
        System.out.print("\nPRESS ENTER TO RETURN");
        sc.nextLine();
    }
}
 