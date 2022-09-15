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

    public String getHeroName() {
        if (heroName == ""){
            return "Intet superhelte navn";
        }else {
            return heroName;
        }
    }

    public String getRealName() {
        return realName;
    }

    public String getSuperPower() {
        return superPower;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public double getPower() {
        return power;
    }

    public String isHuman() {
        if (isHuman == true){
            return "Ja";
        }else {
            return "Nej";
        }
    }

    public void setRealName(String newRealName) {
        this.realName = newRealName;
    }
    public void setSuperPower(String newSuperPower) {
        this.superPower = newSuperPower;
    }
    public void setCreationYear(String newCreationYear) {
        this.creationYear = Integer.parseInt(newCreationYear);
    }
    public void setIsHuman(String newIsHuman) {
        this.isHuman = Boolean.parseBoolean(newIsHuman);
    }
    public void setPower(String newPower) {
        this.power = Double.parseDouble(newPower);
    }
}
