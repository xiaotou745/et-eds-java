package com.edaisong.core.validator;


public class CommonValidator
{
    public static boolean validPhoneNumber(String phoneNumber)
    {
        if (phoneNumber==null||phoneNumber.isEmpty())
        {
            return false;
        }
        if (!phoneNumber.startsWith("1") || phoneNumber.length() != 11)
        {
            return false;
        }
        return true;
        //return Regex.IsMatch(phoneNumber, @"^(13|14|15|16|18|19)\d{9}$");
    }
}