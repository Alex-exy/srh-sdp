package de.srh.library.dto;

public final class Global {

    public static boolean isAdmin = false;

    public static Long loggedInUserId = 0L;

    public static void userLogin(Long userId){
        isAdmin = false;
        loggedInUserId = userId;
    }
    public static void adminLogin(){
        isAdmin = true;
    }

    public static void userLogOut(){
        isAdmin = false;
        loggedInUserId = 0L;
    }


}
