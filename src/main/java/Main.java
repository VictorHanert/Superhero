import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
    Database database = new Database();
    int menuvalg;

    public static void main(String[] arg) {
        Main program = new Main();
        program.start();
    }

    public void start() {
        velkomst();
        createSuperHero();
    }

    public void velkomst() {
        System.out.println("\u001b[1mVelkommen til Superhelte-databasen!\u001b[0m");
        System.out.println("Her kan du oprette dine yndlings superhelte og holde overblik og sammenligne dem.\n");
    }

    public void createSuperHero() {
        do {
            System.out.println("\u001b[1mMenu\u001b[0m");
            System.out.println("Tryk 1) for at oprette");
            System.out.println("Tryk 2) for at at se liste med superhelte");
            System.out.println("Tryk 3) for at at søge efter superhelt");
            System.out.println("Tryk 9) for at afslutte");
            menuvalg = scan.nextInt();
            scan.nextLine(); // Fixer Scanner-bug

            if (menuvalg == 1) {
                System.out.println("Opret en superhelt.");

                System.out.println("Indtast superheltens rigtige navn: ");
                String realName = scan.nextLine();

                System.out.print("Indtast superheltens heltenavn: ");
                String heroName = scan.nextLine();

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
                createSuperHero();

            } else if (menuvalg == 2) {
                printSuperHero();
            } else if (menuvalg == 3) {
                searchSuperHero();
            } else if (menuvalg == 9) {
                System.exit(1); // afslutter programmet
            }
        } while (menuvalg != 1 && menuvalg != 2 && menuvalg != 9);
    }

    public void printSuperHero() {
        System.out.println("\u001b[1mListe af superhelte\u001b[0m");
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        for (Superhero superhero : database.getAllSuperheroes()){
            System.out.println("Superhelte navn: " + superhero.getHeroName());
            System.out.println("Virkeligt navn: " + superhero.getRealName());
            System.out.println("Superkraft: " + superhero.getSuperPower());
            System.out.println("Oprindelsesår: " + superhero.getCreationYear());
            System.out.println("Er menneske: " + superhero.isHuman());
            System.out.println("Styrkeniveau: " + superhero.getPower());
            System.out.println("\u001b[1m-\u001b[0m".repeat(20));

        }
        System.out.println(" \n");
        createSuperHero();
    }

    public void searchSuperHero() {
        System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        System.out.println("Indtast søgeord: ");
        String searchTerm = scan.nextLine();

        // tilføjet "searchTerm fra input til database til søgningen
        Superhero superhero = database.searchForSuperhero(searchTerm);

        // hvis der ikke er fundet en helt, print fejl, og søg igen
        if (superhero == null) {
            System.out.println("Superhelt ikke fundet");
            System.out.println("------------------------------------");
            searchSuperHero();
        } else {  // hvis fundet, print heltens info
            System.out.println("Superhelte navn: " + superhero.getHeroName());
            System.out.println("Virkeligt navn: " + superhero.getRealName());
            System.out.println("Superkraft: " + superhero.getSuperPower());
            System.out.println("Oprindelsesår: " + superhero.getCreationYear());
            System.out.println("Er menneske: " + superhero.isHuman());
            System.out.println("Styrkeniveau: " + superhero.getPower());
            System.out.println("\u001b[1m-\u001b[0m".repeat(20));
        }
    }
}
