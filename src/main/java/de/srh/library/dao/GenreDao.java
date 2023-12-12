package de.srh.library.dao;
import de.srh.library.entity.Genre;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.genre.GenreMapper;
import org.apache.ibatis.session.SqlSession;

public class GenreDao {
    public Genre getGenreById(Long genreId) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            GenreMapper mapper = session.getMapper(GenreMapper.class);
            return mapper.getGenreById(genreId);
        }
    }
    public Genre getGenreByName(String genreName) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            GenreMapper mapper = session.getMapper(GenreMapper.class);
            return mapper.getGenreByName(genreName);
        }
    }

    public int insertGenre(Genre genre) {
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession(true)) {
            GenreMapper mapper = session.getMapper(GenreMapper.class);
            return mapper.insertGenre(genre);
        }
    }


}
