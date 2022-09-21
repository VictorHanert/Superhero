import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Database database;

    @BeforeEach
    public void setup() {
        database = new Database();
    }

    @Test
    void createOneSuperHero() {
        //Arrange
        database.createSuperhero("Bruce Wayne", "Batman", 1939, "None", true, 2);
        int expected = 1;

        //act
        int actual = database.getAllSuperheroes().size();

        //assert
        assertEquals(expected,actual);
    }

    @Test
    void createMultipleSuperheroes(){
        //Arange
        database.createSuperhero("Bruce Wayne", "Batman", 1939, "None", true, 2);
        database.createSuperhero("Clark Kent", "Superman", 1938, "Superhuman strength", true, 4);
        database.createSuperhero("Peter Parker", "Spider-Man", 1962, "Spider-Sense", true, 3);
        database.createSuperhero("Diana Prince", "Wonder Woman", 1941, "Flight", true, 2.5);

        int expected = 4;

        //Act
        int actual = database.getAllSuperheroes().size();

        //assert
        assertEquals(expected,actual);
    }

    @Test
    void searchForZeroSuperhero() {

        //Arrange
        ArrayList<Superhero> expected = new ArrayList<>(1);

        //act
        ArrayList<Superhero> actual = database.searchForSuperhero("man");

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void searchForOneSuperhero() {
        //Arrange
        Superhero s1 = database.createSuperhero("Bruce Wayne", "Batman", 1939, "None", true, 2);

        ArrayList<Superhero> expected = new ArrayList<>(1);

        expected.add(s1);

        //act
        ArrayList<Superhero> actual = database.searchForSuperhero("man");

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void searchForMultipleSuperheroes() {
        //Arrange
        Superhero s1 = database.createSuperhero("Bruce Wayne", "Batman", 1939, "None", true, 2);
        Superhero s2 = database.createSuperhero("Clark Kent", "Superman", 1938, "Superhuman strength", true, 4);
        Superhero s3 = database.createSuperhero("Peter Parker", "Spider-Man", 1962, "Spider-Sense", true, 3);
        ArrayList<Superhero> expected = new ArrayList<>(3);

        expected.add(s1);
        expected.add(s2);
        expected.add(s3);

        //act
        ArrayList<Superhero> actual = database.searchForSuperhero("man");

        //Assert
        assertEquals(expected,actual);
    }
}