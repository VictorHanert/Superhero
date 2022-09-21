import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private Database database;

    @BeforeEach
    public void setup() {
        database = new Database();
        database.createSuperhero("Victor Hanert", "Actionman", 2001, "Lækker", true,999);
        database.createSuperhero("Bruce Wayne", "Batman", 1965, "Rig", true,2);
        database.createSuperhero("Clark Kent", "Superman", 1970, "Super-evner", true,3);
        database.createSuperhero("Tony Stark", "Iron Man", 1998, "Flyve", true,1.2);
        database.createSuperhero("", "Black Panther", 1973, "Sort", true,5.5);
        database.createSuperhero("Bruce Banner", "Hulk", 1967, "Stærk", false,4);

    }

    @Test
    void createOneSuperHero() {
        //Arrange
        int expected = database.getAllSuperheroes().size() + 1;

        //Act
        database.createSuperhero("Peter Parker", "Spider-man", 1949, "Edderkop", true, 2);
        int actual = database.getAllSuperheroes().size();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void createMultipleSuperheroes(){
        //Arrange
        int expected = database.getAllSuperheroes().size() + 3;

        //Act
        database.createSuperhero("Clark Kent", "Superman", 1938, "Superhuman strength", true, 4);
        database.createSuperhero("Peter Parker", "Spider-Man", 1962, "Spider-Sense", true, 3);
        database.createSuperhero("Diana Prince", "Wonder Woman", 1941, "Flight", true, 2.5);

        int actual = database.getAllSuperheroes().size();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void searchForZeroSuperhero() {

        //Arrange
        ArrayList<Superhero> expected = new ArrayList<>(1);

        //Act
        ArrayList<Superhero> actual = database.searchForSuperhero("volapyk");

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void searchForOneSuperhero() {
        //Arrange
        Superhero s1 = database.createSuperhero("Olga", "Cat-woman", 1990, "None", true, 1.2);

        ArrayList<Superhero> expected = new ArrayList<>(1);

        expected.add(s1);

        //Act
        ArrayList<Superhero> actual = database.searchForSuperhero("Cat");

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void searchForMultipleSuperheroes() {
        //Arrange
        int expectedSearch = 4;

        //Act
        int actualSearch = database.searchForSuperhero("man").size();

        //Assert
        assertEquals(expectedSearch,actualSearch);
    }

    @Test
    void deleteSuperhero() {
        //Arrange
        ArrayList<Superhero> results = database.getAllSuperheroes();
        Superhero superhero = database.getAllSuperheroes().get(0);

        int expectedSize = results.size()-1;
        boolean expectedResult = true;

        //Act
        ArrayList<Superhero> resultAfterDelete = database.getAllSuperheroes();

        boolean actualResult = database.deleteSuperhero(superhero);
        int actualSize = resultAfterDelete.size();

        //Assert
        assertEquals(expectedResult, actualResult);
        assertEquals(expectedSize, actualSize);
    }
}
