package de.srh.library.mapper.users;

import de.srh.library.dto.UserDto;
import de.srh.library.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    UserDto getUserByEmail(String email);

    int insertUser(User user);

    UserDto getUserById(long userId);
    int userFoundId(long userId);
    int userFoundEmail(String email);
    int removeUser(long userId);
    long getIdByEmail(String email);
    void updateUserPassword(@Param("password") String password, @Param("email") String email);
    int updateUserInfo(User user);
}