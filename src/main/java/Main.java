import java.util.Arrays;
import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);
    Database database = new Database();
    int menuvalg;

    public static void main(String[] arg) {
        Main program = new Main();

        program.start();
    }

    public void start() {
        createSuperHero();
    }

    public void createSuperHero() {
        do {
            System.out.println("1) for at oprette");
            System.out.println("9) for at afslutte");
            menuvalg = scan.nextInt();
            scan.nextLine(); // Fixer Scanner-bug

            if (menuvalg == 1) {
                System.out.println("Velkommen til Superhelte-database.");

                System.out.println("Indtast superheltens rigtige navn: ");
                String realName = scan.nextLine();

                System.out.print("Indtast superheltens heltenavn: ");
                String heroName = scan.nextLine();

                System.out.print("Hvornår blev superhelten skabt? ");
                int creationYear = scan.nextInt();

                System.out.print("Hvilke superkræfter besidder superhelten? ");
                String superPower = scan.nextLine();

                boolean isHuman = scan.nextLine().substring(0, 1).equalsIgnoreCase("j");

                database.createSuperhero(realName, heroName, creationYear, superPower, isHuman);

                for (Object hero : database.superhero) {
                    System.out.println(hero);
                }

                start();

            } else if (menuvalg == 9) {
                System.out.println("Program afsluttes!");
            }
        } while (menuvalg != 1 && menuvalg != 9);
    }
}