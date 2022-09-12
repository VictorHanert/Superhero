import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

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

    }
}
