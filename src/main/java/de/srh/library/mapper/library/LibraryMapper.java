package de.srh.library.mapper.library;

import de.srh.library.entity.Library;

import java.util.List;

public interface LibraryMapper {
    Library getLibraryById(Long libraryId);
    Library getLibraryByName(String libraryName);
    int insertLibrary(Library library);
    List<Library> getAllLibraries();
}
