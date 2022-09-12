import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);

        String heroName;
        String realName;
        String superPower;
        int creationYear;
        boolean human;


        System.out.println("Velkommen til Superhelte-database;");

        System.out.println("Hvilken superhelt?");
        heroName = scan.next();

        System.out.println("Hvad er " + heroName + "'s superkræft?");
        superPower = scan.next();

        System.out.println(superPower);

        Superhero superheroes = new Superhero();
    }
}
