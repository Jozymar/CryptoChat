/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.cryptochat.utilitarios;

import com.ifpb.cryptochat.interfaces.ProtectablePassword;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordProtected implements ProtectablePassword {

    @Override
    public byte[] getMD5Password(String key) {
        byte messageDigest[] = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            messageDigest = algorithm.digest(key.getBytes("UTF-8"));
            return messageDigest;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordProtected.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageDigest;
    }

    @Override
    public String getHexadecimalOfBytes(byte[] messageDigest) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    @Override
    public String getPasswordProtected(String key) {
        return getHexadecimalOfBytes(getMD5Password(key));
    }

}
