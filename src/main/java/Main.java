import java.util.Arrays;
import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);
    Database database = new Database();

    public static void main(String[] arg) {
        Main program = new Main();

        program.start();
    }

    public void start(){
        udskrivVelkomst();
    }

    public void udskrivVelkomst() {
        createSuperHero();
    }

    public void createSuperHero() {
        if (database.getSuperHeroesAmount() < database.getSuperheroes().length) {
            System.out.println("Velkommen til Superhelte-database.");

            System.out.println("Indtast superheltens rigtige navn: ");
            String realName = scan.next();

            System.out.print("Indtast superheltens heltenavn: ");
            String heroName = scan.next();

            System.out.print("Hvornår blev superhelten skabt? ");
            int creationYear = scan.nextInt();

            System.out.print("Hvilke superkræfter besidder superhelten? ");
            String superPower = scan.next();

            boolean isHuman = false;
            char humanStatus;

            database.createSuperhero(realName, heroName, creationYear, superPower, isHuman);
            System.out.printf("%s %s %d %s \n",realName, heroName, creationYear, superPower);
            start();
        }

        else {
            System.out.println("Kun plads til 5 superhelte");

        }

    }
}
