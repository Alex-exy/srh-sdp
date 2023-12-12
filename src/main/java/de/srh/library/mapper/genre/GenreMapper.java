package de.srh.library.mapper.genre;
import de.srh.library.entity.Genre;
public interface GenreMapper {
    Genre getGenreById(Long genreId);
    Genre getGenreByName(String genreName);
    int insertGenre(Genre genre);
}
