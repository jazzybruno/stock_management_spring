package rw.jazzybruno.istock.v1.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hash {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String hashPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
}
