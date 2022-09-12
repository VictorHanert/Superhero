public class Database {

    private Superhero[] superheroes;

    public Database() {
        superheroes = new Superhero[5];
    }

    public void createSuperhero(String realName, String heroName, int creationYear, String superPower, boolean isHuman) {
        Superhero newSuperhero = new Superhero(realName,heroName,creationYear,superPower,isHuman);
    }

    public Superhero[] getSuperheroes() {
        return superheroes;
    }

}
