import java.util.ArrayList;

public class Database {

    ArrayList<Superhero> superhero = new ArrayList<>();

    public void createSuperhero(String realName,String heroName,int creationYear,String superPower,boolean isHuman) {
        Superhero hero = new Superhero(realName,heroName,creationYear,superPower,isHuman);
        //heroList[index] = hero;
        //index++;
        superhero.add(hero);
    }
}
