import java.util.ArrayList;

public class Database {

    ArrayList<Superhero> superhero = new ArrayList<>();

    // TEST DATA
    public void createTestData(){
        createSuperhero("Victor Hanert", "Actionman", 2001, "Lækker", true,999);
        createSuperhero("Bruce Wayne", "Batman", 1965, "Rig", true,2);
        createSuperhero("Peter Parker", "Superman", 1970, "Edderkoppeevner", true,3);
        createSuperhero("Tony Stark", "Iron Man", 1998, "Flyve", true,1.2);
        createSuperhero("", "Black Panther", 1973, "Sort", true,5.5);
        createSuperhero("Bruce Banner", "Hulk", 1967, "Stærk", false,4);
    }

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
