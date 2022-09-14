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
        createSuperHero();
        printSuperHero();
    }

    public void createSuperHero() {
        do {
            System.out.println("Velkommen til Superhelte-database.");
            System.out.println("Tryk 1) for at oprette");
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
                start();

            } else if (menuvalg == 9) {
                System.out.println("Program afsluttes!");
            }
        } while (menuvalg != 1 && menuvalg != 9);
    }

    public void printSuperHero() {
        System.out.println("Liste af superhelte");
        System.out.println("-".repeat(20));
        for (Object hero : database.superhero) {
            System.out.println(hero);
            System.out.println("-".repeat(20));
            start();
        }
    }
}