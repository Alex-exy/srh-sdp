package de.srh.library.dto;

public final class Global {

    public static boolean isAdmin = false;

    public static Long loggedInUserId = 0L;

    public static void userLogin(Long loggedInUserId){
        isAdmin = false;
        loggedInUserId = loggedInUserId;
    }
    public static void adminLogin(Boolean isAdmin){
        isAdmin = true;
    }

    public static void userLogOut(){
        isAdmin = false;
        loggedInUserId = 0L;
    }


}
