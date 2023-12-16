package de.srh.library.mapper.genre;
import de.srh.library.entity.Genre;

import java.util.List;

public interface GenreMapper {
    Genre getGenreById(Long genreId);
    Genre getGenreByName(String genreName);
    int insertGenre(Genre genre);
    List<Genre> getAllGenres();
}
