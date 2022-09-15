import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
    Database database = new Database();
    int menuvalg;

    public void start() {
        velkomst();
        menu();
    }

    public void velkomst() {
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
        menuvalg = scan.nextInt();
        scan.nextLine(); // Fixer Scanner-bug

        if (menuvalg == 1) {
            createSuperHero();
        } else if (menuvalg == 2) {
            printSuperHero();
        } else if (menuvalg == 3) {
            searchSuperHero();
        } else if (menuvalg == 4) {
            editSuperHero();
        } else if (menuvalg == 9) {
            System.exit(1); // afslutter programmet
        }
    }

    public void createSuperHero() {
        System.out.println("Opret en superhelt.");

        System.out.print("Indtast superheltens heltenavn: ");
        String heroName = scan.nextLine();

        System.out.println("Indtast superheltens rigtige navn: ");
        String realName = scan.nextLine();

        System.out.print("Hvornår blev superhelten skabt? ");
        int creationYear = scan.nextInt();
        scan.nextLine();

        System.out.print("Hvilke superkræfter besidder superhelten? ");
        String superPower = scan.nextLine();

        System.out.println("Er superhelten et menneske? (j / n)");
        boolean isHuman = scan.nextLine().substring(0, 1).equalsIgnoreCase("j");

        System.out.println("Hvor stor en kræft har helten (hvis 1.0 er for et menneske) ");
        double power = scan.nextDouble();

        database.createSuperhero(realName, heroName, creationYear, superPower, isHuman, power);
        menu();
    }

    public void printSuperHero() {
        System.out.println("\u001b[1mListe af superhelte\u001b[0m");
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        for (Superhero superhero : database.getAllSuperheroes()){
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
            if (!newRealName.isEmpty()) {superhero.setRealName(newRealName);}
            System.out.println("\u001b[1mNyt navn: \u001b[0m" + superhero.getRealName() + "\n");

            System.out.println("Rediger superkraft: " + superhero.getSuperPower());
            String newSuperPower = scan.nextLine();
            if (!newSuperPower.isEmpty()) {superhero.setSuperPower(newSuperPower);}

            System.out.println("Rediger oprindelsesår: " + superhero.getCreationYear());
            String newCreationYear = scan.nextLine();
            if (!newCreationYear.isEmpty()) {superhero.setCreationYear(newCreationYear);}

            System.out.println("Rediger menneskestatus (Ja / Nej): " + superhero.isHuman());
            String newIsHuman = scan.nextLine();
            if (!newIsHuman.isEmpty()) {superhero.setIsHuman(newIsHuman);}

            System.out.println("Rediger styrkeniveau (1.0 er et menneske): " + superhero.getPower());
            String newPower = scan.nextLine();
            if (!newPower.isEmpty()) {superhero.setPower(newPower);}
        }
        menu();
    }
}
