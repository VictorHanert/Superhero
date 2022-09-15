import java.util.ArrayList;

public class Database {

    ArrayList<Superhero> superhero = new ArrayList<>();

    public void createSuperhero(String realName,String heroName,int creationYear,String superPower,boolean isHuman, double power) {
        Superhero hero = new Superhero(realName,heroName,creationYear,superPower,isHuman, power);
        superhero.add(hero);
    }

    // getter til superhero arrayet
    public ArrayList<Superhero> getAllSuperheroes(){
        return superhero;
    }

    public Superhero searchForSuperhero(String searchTerm){
        for (Superhero superhero : superhero){
            String name = superhero.getHeroName().toLowerCase();
            if (name.contains(searchTerm.toLowerCase())){
                return superhero;
            }
        }
        //hvis ingen fundet
        return null;
    }
}
