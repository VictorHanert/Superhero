public class Superhero {
    private String realName;
    private String heroName;
    private int creationYear;
    private String superPower;
    private boolean isHuman;
    private double power;

    public Superhero (String realName, String heroName, int creationYear, String superPower, boolean isHuman, double power) {
        this.realName = realName;
        this.heroName = heroName;
        this.creationYear = creationYear;
        this.superPower = superPower;
        this.isHuman = isHuman;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Rigtig navn: " + realName +
                "\n Heltenavn: " + heroName +
                "\n År hvor helten blev til: " + creationYear +
                "\n Superkræft: " + superPower +
                "\n Er helten menneske?: " + isHuman +
                "\n Styrke: " + power;
    }
}
