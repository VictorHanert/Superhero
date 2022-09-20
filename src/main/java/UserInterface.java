import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    public Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
    public Database database = new Database();

    public void start() {
        velkomst();
        menu();
    }

    public void velkomst() {
        //database.createTestData(); // NOTE: Test Data, fjern når færdig
        System.out.println("\u001b[1mVelkommen til Superhelte-databasen!\u001b[0m");
        System.out.println("Her kan du oprette dine yndlings superhelte og holde overblik og sammenligne dem.\n");
    }

    public void menu() {
        System.out.println("\u001b[1mMenu\u001b[0m");
        System.out.println("Tryk 1) for at oprette");
        System.out.println("Tryk 2) for at at se liste med superhelte");
        System.out.println("Tryk 3) for at at søge efter bestemt superhelt");
        System.out.println("Tryk 4) for at at redigere data til en superhelt");
        System.out.println("Tryk 9) for at afslutte");

        int menuInput = 0;
        boolean inputError;
        while (menuInput != 9) {
            do {
                try {
                    menuInput = scan.nextInt();
                    scan.nextLine(); // Fixer Scanner-bug
                    handleMenuInput(menuInput);
                    inputError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Ugyldig input prøv venligst igen!");
                    inputError = true;
                    scan.nextLine();
                }
            } while (inputError == true);
        }
    }

    public void handleMenuInput(int menuInput) {
        if (menuInput == 1) {
            createSuperHero();
        } else if (menuInput == 2) {
            printSuperHero();
        } else if (menuInput == 3) {
            searchSuperHero();
        } else if (menuInput == 4) {
            editSuperHero();
        } else if (menuInput == 9) {
            System.exit(1); // afslutter programmet
        }
    }

    public void createSuperHero() {
        System.out.println("Opret en superhelt.");

        System.out.print("Indtast superheltens heltenavn: ");
        String heroName = scan.nextLine();

        System.out.println("Indtast superheltens rigtige navn: ");
        String realName = scan.nextLine();

        /*System.out.print("Hvornår blev superhelten skabt? ");
        int creationYear = scan.nextInt();
        scan.nextLine();
         */
        int creationYear = 0;
        boolean creationYearInputError;
        do {
            try{
                System.out.println("Hvornår blev superhelten skabt?");
                creationYear = Integer.parseInt(scan.nextLine()); // Inputting creation year of superhero
                creationYearInputError = false;
            }
            catch (NumberFormatException e){
                System.out.println("Ugyldigt input, prøv igen");
                creationYearInputError = true;
            }
        }while(creationYearInputError == true);

        System.out.print("Hvilke superkræfter besidder superhelten? ");
        String superPower = scan.nextLine();

        System.out.println("Er superhelten et menneske? (j / n)");
        boolean isHuman = scan.nextLine().substring(0, 1).equalsIgnoreCase("j");

        /*System.out.println("Hvor stor en kræft har helten (hvis 1.0 er for et menneske) ");
        double power = scan.nextDouble();

         */
        double power = 0;
        boolean powerInputError;
        do {
            try{
                System.out.println("Hvor stor en kræft har helten (hvis 1 er for et menneske) ");
                power = Double.parseDouble(scan.nextLine()); // Inputting power of superhero
                powerInputError = false;
            }
            catch (NumberFormatException e){
                System.out.println("Ugyldigt input, prøv igen");
                powerInputError = true;
            }
        }while(powerInputError == true);

        database.createSuperhero(realName, heroName, creationYear, superPower, isHuman, power);
        System.out.println("\u001b[1mSuperhelt er nu oprettet. \u001b[0m\n");
        menu();
    }

    public void printSuperHero() {
        System.out.println("\u001b[1mListe af superhelte\u001b[0m");
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        for (Superhero superhero : database.getAllSuperheroes()) {
            System.out.println("Superhelte navn: \u001b[1m" + superhero.getHeroName() + "\u001b[0m");
            System.out.println("Virkeligt navn: " + superhero.getRealName());
            System.out.println("Superkraft: " + superhero.getSuperPower());
            System.out.println("Oprindelsesår: " + superhero.getCreationYear());
            System.out.println("Er menneske: " + superhero.isHuman());
            System.out.println("Styrkeniveau: " + superhero.getPower());
            System.out.println("\u001b[1m-\u001b[0m".repeat(20));

        }
        System.out.println(" \n");
        menu();
    }

    public void searchSuperHero() {
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        System.out.println("Indtast søgeord: ");
        String searchTerm = scan.nextLine();

        // tilføjet "searchTerm" fra input til database til søgningen
        Superhero superhero = database.searchForSuperhero(searchTerm);

        // hvis der ikke er fundet en helt, print fejl, og søg igen
        if (superhero == null) {
            System.out.println("Superhelt ikke fundet");
            System.out.println("------------------------------------");
            searchSuperHero();
        } else {  // hvis fundet, print heltens info
            System.out.println("Superhelte navn: \u001b[1m" + superhero.getHeroName() + "\u001b[0m");
            System.out.println("Virkeligt navn: " + superhero.getRealName());
            System.out.println("Superkraft: " + superhero.getSuperPower());
            System.out.println("Oprindelsesår: " + superhero.getCreationYear());
            System.out.println("Er menneske: " + superhero.isHuman());
            System.out.println("Styrkeniveau: " + superhero.getPower());
            System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        }
        menu();
    }

    public void editSuperHero() {
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        System.out.println("Indtast heltenavn for den helt der skal redigeres: ");
        String searchTerm = scan.nextLine();
        Superhero superhero = database.searchForSuperhero(searchTerm);
        if (superhero == null) {
            System.out.println("Superhelt ikke fundet");
            System.out.println("------------------------------------");
            editSuperHero();
        } else {  // hvis fundet, print heltens info
            System.out.println("Rediger data for superhelten: \u001b[1m" + superhero.getHeroName() + "\u001b[0m");
            System.out.println("\u001b[1m-\u001b[0m".repeat(20));

            System.out.println("Rediger data og tryk derefter ENTER, hvis data \u001b[1mikke\u001b[0m skal redigeres tryk blot ENTER.");

            System.out.println("Rediger navn: " + superhero.getRealName());
            String newRealName = scan.nextLine();
            if (!newRealName.isEmpty()) { //hvis nyt-navn ikke er tomt:
                superhero.setRealName(newRealName);
                System.out.println("\u001b[1mNyt navn: \u001b[0m" + superhero.getRealName() + "\n");
            }


            System.out.println("Rediger superkraft: " + superhero.getSuperPower());
            String newSuperPower = scan.nextLine();
            if (!newSuperPower.isEmpty()) {
                superhero.setSuperPower(newSuperPower);
                System.out.println("\u001b[1mSuperkraft opdateret til: \u001b[0m" + superhero.getSuperPower() + "\n");
            }

            System.out.println("Rediger oprindelsesår: " + superhero.getCreationYear());
            String newCreationYear = scan.nextLine();
            if (!newCreationYear.isEmpty()) {
                superhero.setCreationYear(newCreationYear);
                System.out.println("\u001b[1mOprindelsesår opdateret til: \u001b[0m" + superhero.getCreationYear() + "\n");
            }

            System.out.println("Rediger menneskestatus (Ja / Nej): " + superhero.isHuman());
            String newIsHuman = scan.nextLine();
            if (!newIsHuman.isEmpty()) {
                superhero.setIsHuman(newIsHuman);
                System.out.println("\u001b[1mMenneskestatus opdateret til: \u001b[0m" + superhero.isHuman() + "\n");
            }

            System.out.println("Rediger styrkeniveau (1.0 er et menneske): " + superhero.getPower());
            String newPower = scan.nextLine();
            if (!newPower.isEmpty()) {
                superhero.setPower(newPower);
                System.out.println("\u001b[1mStyrkeniveau opdateret til: \u001b[0m" + superhero.getPower() + "\n");
            }
        }
        System.out.println("Helten blev opdateret og gemt.");
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        menu();
    }
}
