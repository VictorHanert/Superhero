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
                System.exit(1);
            }
        } while (menuvalg != 1 && menuvalg != 2 && menuvalg != 9);
    }

    public void printSuperHero() {
        System.out.println("Liste af superhelte");
        System.out.println("-".repeat(20));
        for (Object hero : database.superhero) {
            System.out.println(hero);
            System.out.println("-".repeat(20));
        }
        System.out.println(" \n");
        createSuperHero();
    }

    public void searchSuperHero() {

    }
}
