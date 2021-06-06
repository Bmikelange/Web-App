package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import fr.univlyon1.multimif_2019.projetgrp3.constante.ConstEncryptor;

/**
 * use to encrypt some strings.
 */
public interface Encryptor {

    /**
     * Encrypt the password using Sha's algorithm.
     *
     * @param password String that is not hashed
     *
     * @return password that is encrypted
     *
     * @throws NoSuchAlgorithmException if messageDigest do not find the algorithm
     */
    public static String encryptSha(String password) throws NoSuchAlgorithmException {
        password += ConstEncryptor.SALT;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte[] byteData = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
