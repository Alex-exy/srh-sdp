package de.srh.library.dao;
import de.srh.library.entity.Genre;
import de.srh.library.entity.School;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.genre.GenreMapper;
import de.srh.library.mapper.schools.SchoolMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Integer> getAllGenres(){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            GenreMapper mapper = session.getMapper(GenreMapper.class);
            List<Genre> genres = mapper.getAllGenres();
            Map<String, Integer> genreMap = new HashMap<>();
            for(Genre genre : genres){
                genreMap.put(genre.getGenreName(),genre.getGenreId());
            }
            return genreMap;
        }
    }


}
