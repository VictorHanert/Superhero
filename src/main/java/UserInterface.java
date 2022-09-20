import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    public Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
    public Database database = new Database();

    public void start() {
        welcome();
        menu();
    }

    public void welcome() {
        database.createTestData(); // NOTE: Test Data, fjern når færdig

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
                    scan.nextLine(); // Fixer Scanner-bug
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
        ArrayList<Superhero> allSuperheroes = database.getAllSuperheroes();

        // Checker om liste med superhelte er tom
        if (allSuperheroes.isEmpty()){
            System.out.println("\u001b[1mIngen superhelte fundet i databasen.");
            System.out.println("-\u001b[0m".repeat(20) + "\n");
        }
        else {
            System.out.println("\u001b[1mListe af superhelte\u001b[0m");
            System.out.println("Antal superhelte fundet: " + database.getAllSuperheroes().size());
            System.out.println("\u001b[1m-\u001b[0m".repeat(20));

            for (Superhero superhero : allSuperheroes) {
                System.out.println("Superhelte navn: \u001b[1m" + superhero.getHeroName() + "\u001b[0m");
                System.out.println("Virkeligt navn: " + superhero.getRealName());
                System.out.println("Superkraft: " + superhero.getSuperPower());
                System.out.println("Oprindelsesår: " + superhero.getCreationYear());
                System.out.println("Er menneske: " + superhero.isHuman());
                System.out.println("Styrkeniveau: " + superhero.getPower());
                System.out.println("\u001b[1m-\u001b[0m".repeat(20) + "\n");
            }
        }
        menu();
    }

    public void searchSuperHero() {
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        System.out.println("Søg efter superhelte ved at indtaste søgeord: ");
        String searchTerm = scan.nextLine();

        ArrayList<Superhero> searchResults = database.searchForSuperhero(searchTerm);

        int index = 1;
        if (searchResults.isEmpty()) { // hvis der ikke er fundet en helt, print fejl, og søg igen
            System.out.println("Ingen superhelte fundet med indtastet søgeord: " + searchTerm);
            System.out.println("------------------------------------");
            searchSuperHero();
        } else {
            for (Superhero searchResult : searchResults) {
                System.out.println(index++ + ": " + searchResult.getHeroName());
            }
            System.out.println("Vælg superhelten du vil have skrevet ud: ");
            int superheroChoice = 1;
            boolean inputError;

            do{
                try{
                    superheroChoice = Integer.parseInt(scan.nextLine());
                    inputError = false;
                    System.out.println("Superhelte navn: \u001b[1m" + searchResults.get(superheroChoice-1).getHeroName() + "\u001b[0m");
                    System.out.println("Superkraft: " + searchResults.get(superheroChoice-1).getSuperPower());
                    System.out.println("Virkeligt navn: " + searchResults.get(superheroChoice-1).getRealName());
                    System.out.println("Oprindelsesår: " + searchResults.get(superheroChoice-1).getCreationYear());
                    System.out.println("Er menneske: " + searchResults.get(superheroChoice-1).isHuman());
                    System.out.println("Styrke: " + searchResults.get(superheroChoice-1).getPower()+"\n");
                }
                catch (IndexOutOfBoundsException | NumberFormatException e){
                    System.out.println("Ugyldigt input, prøv igen");
                    inputError = true;
                }
            }while(inputError == true);

            menu();
        }
    }

    public void editSuperHero() {
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        System.out.println("Indtast heltenavn for den helt der skal redigeres: ");
        String searchTerm = scan.nextLine();
        ArrayList<Superhero> searchResults = database.searchForSuperhero(searchTerm);

        if (searchResults.isEmpty()){
            System.out.println("Ingen superhelte fundet, prøv igen");
            System.out.println("\u001b[1m-\u001b[0m".repeat(20));
            menu();
        }else {
            // Printing out all superheroes matching search term
            int index = 1;
            for (Superhero searchResult : searchResults) {
                System.out.println(index++ + ": " + searchResult.getHeroName());
            }

            System.out.println("Indtast nummeret på superhelten du vil redigere: ");
            int superheroChoice = 1;
            boolean inputError = false;
            do {
                try {
                    superheroChoice = Integer.parseInt(scan.nextLine()); // input of what superhero to update
                    Superhero editSuperhero = searchResults.get(superheroChoice - 1);
                    System.out.println("Redigere: " + editSuperhero.getHeroName());

                    System.out.println("\u001b[1m-\u001b[0m".repeat(20));

                    System.out.println("Indtast data der skal ændres og klik ENTER. Skal data ikke ændres, klik blot ENTER.");

                    System.out.println("Rediger navn: " + editSuperhero.getRealName());
                    String newRealName = scan.nextLine();
                    if (!newRealName.isEmpty()) { //hvis nyt-navn ikke er tomt:
                        editSuperhero.setRealName(newRealName);
                        System.out.println("\u001b[1mNyt navn: \u001b[0m" + editSuperhero.getRealName() + "\n");
                    }

                    System.out.println("Superkræft(er): " + editSuperhero.getSuperPower());
                    String newSuperPower = scan.nextLine();
                    if (!newSuperPower.isEmpty()) { // if the input is not empty, set new data
                        editSuperhero.setSuperPower(newSuperPower);
                        System.out.println("\u001b[1mSuperkraft opdateret til: \u001b[0m" + editSuperhero.getSuperPower() + "\n");
                    }

                    System.out.println("Oprindelsesår: " + editSuperhero.getCreationYear());
                    String newCreationYear = scan.nextLine();
                    if (!newCreationYear.isEmpty()) {
                        editSuperhero.setCreationYear(newCreationYear);
                        System.out.println("\u001b[1mOprindelsesår opdateret til: \u001b[0m" + editSuperhero.getCreationYear() + "\n");
                    }

                    System.out.println("Rediger menneskestatus (Ja / Nej): " + editSuperhero.isHuman());
                    String newIsHuman = scan.nextLine();
                    if (!newIsHuman.isEmpty()) {
                        editSuperhero.setIsHuman(newIsHuman);
                        System.out.println("\u001b[1mMenneskestatus opdateret til: \u001b[0m" + editSuperhero.isHuman() + "\n");
                    }

                    System.out.println("Styrke: " + editSuperhero.getPower());
                    String newPower = scan.nextLine();
                    if (!newPower.isEmpty()) {
                        editSuperhero.setPower(newPower);
                        System.out.println("\u001b[1mStyrkeniveau opdateret til: \u001b[0m" + editSuperhero.getPower() + "\n");
                    }

                    inputError = false;
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Ugyldigt input, prøv igen");
                    inputError = true;
                    System.out.println("\u001b[1m-\u001b[0m".repeat(20));
                }
            } while (inputError == true);

        }
        System.out.println("Helten blev opdateret og gemt.");
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        menu();
    }
}
