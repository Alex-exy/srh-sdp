package de.srh.library.dao;

import de.srh.library.entity.School;
import de.srh.library.mapper.MyBatisSqlSessionFactory;
import de.srh.library.mapper.schools.SchoolMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolDao {

    public Map<String, Integer> getAllSchools(){
        try (SqlSession session = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            SchoolMapper mapper = session.getMapper(SchoolMapper.class);
            List<School> schools = mapper.getAllSchools();
            Map<String, Integer> schoolsMap = new HashMap<>();
            for(School school : schools){
                schoolsMap.put(school.getSchoolName(), school.getSchoolId());
            }
            return schoolsMap;
        }
    }
}
