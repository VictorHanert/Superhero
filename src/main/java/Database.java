import java.util.ArrayList;

public class Database {

    ArrayList<Superhero> superhero = new ArrayList<>();

    public void createSuperhero(String realName,String heroName,int creationYear,String superPower,boolean isHuman, double power) {
        Superhero hero = new Superhero(realName,heroName,creationYear,superPower,isHuman, power);
        superhero.add(hero);
    }
}
