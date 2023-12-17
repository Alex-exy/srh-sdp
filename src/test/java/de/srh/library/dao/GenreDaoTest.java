package de.srh.library.dao;
import static org.junit.jupiter.api.Assertions.*;
import de.srh.library.entity.Genre;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenreDaoTest {

    private static GenreDao genreDao;
    private static Genre initGenre;

    private static long genreId;

    @BeforeAll
    static void setup() {
        genreDao = new GenreDao();

        initGenre = new Genre();
        initGenre.setGenreName("Test");


    }


    @Test
    @Order(1)
    void insertGenre(){
//
        genreDao.insertGenre(initGenre);
    }
    @Test
    @Order(2)
    void getGenreById() {
        System.out.println(genreDao.getGenreById(21L));

    }

}
