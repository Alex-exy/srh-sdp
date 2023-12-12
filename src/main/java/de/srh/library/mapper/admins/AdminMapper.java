package de.srh.library.mapper.admins;

public interface AdminMapper {

    String getAdminPasswordHash(String adminUserName);
}