package de.srh.library.dto;

public final class Global {

    public static boolean isAdmin = false;

    public static Long loggedInUserId = 0L;

    public static void userLogin(Boolean isAdmin, Long loggedInUserId){
        isAdmin = isAdmin;
        loggedInUserId = loggedInUserId;
    }

    public static void userLogOut(){
        isAdmin = false;
        loggedInUserId = 0L;
    }


}
