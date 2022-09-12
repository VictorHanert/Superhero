public class Database {

    private Superhero[] superheroes;
    private int heroAmount;

    public Database() {
        superheroes = new Superhero[5];
        heroAmount = 0;
    }

    public void createSuperhero(String realName, String heroName, int creationYear, String superPower, boolean isHuman) {
        Superhero newSuperhero = new Superhero(realName,heroName,creationYear,superPower,isHuman);
        newSuperhero = superheroes[heroAmount++];
    }

    public Superhero[] getSuperheroes() {
        return superheroes;
    }

    public int getSuperHeroesAmount() {
        return heroAmount;
    }
}
