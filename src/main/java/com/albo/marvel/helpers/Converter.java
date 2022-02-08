package com.albo.marvel.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class Converter {
    
    public static String MD5(String value) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        return DatatypeConverter.printHexBinary(md.digest());
    }
    
}
