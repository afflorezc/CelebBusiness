package com.afflorezc.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Encryption {

    public static String encryptWord(String word){
        return BCrypt.hashpw(word, BCrypt.gensalt());
    }

}
