package de.srh.library.dao;

import de.srh.library.entity.Library;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.library.LibraryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryDao {
    public Library getLibraryById(Long libraryId){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            LibraryMapper mapper = session.getMapper(LibraryMapper.class);
            return mapper.getLibraryById(libraryId);
        }
    }
    public Library getLibraryByName(String libraryName){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            LibraryMapper mapper = session.getMapper(LibraryMapper.class);
            return mapper.getLibraryByName(libraryName);
        }
    }
    public int insertLibrary(Library library){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            LibraryMapper mapper = session.getMapper(LibraryMapper.class);
            return mapper.insertLibrary(library);
        }
    }
    public Map<String,Integer> getAllLibraries(){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            LibraryMapper mapper = session.getMapper(LibraryMapper.class);
            List<Library> libraries = mapper.getAllLibraries();
            Map<String, Integer> libraryMap = new HashMap<>();
            for(Library library : libraries){
                libraryMap.put(library.getLibraryName(),library.getLibraryId());
            }
            return libraryMap;
        }
    }
}
