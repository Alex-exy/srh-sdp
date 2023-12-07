package de.srh.library.mapper.users;

import de.srh.library.entity.User;

public interface UserMapper {
    User getUserByEmail(String email);

    int insertUser(User user);
}