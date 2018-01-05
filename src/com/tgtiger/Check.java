package com.tgtiger;

public class Check {  
    public static boolean checkreturn(String account,String password){  
     boolean checkbool = false;  
    if(("1".equals(account)&&"1".equals(password))||("0".equals(account)&&"0".equals(password))){  
       checkbool = true;  
    }  
    return checkbool;  
    }  
}