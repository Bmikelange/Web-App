package fr.univlyon1.multimif_2019.projetgrp3.model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryptor {

    final static String SALT = "tuyf85765464DGESRoerg,!!ou?BXZ---/";

    /**
     * Encrypt the password thanks to sha's algorithm.
     * TODO à mettre en protected
     * @param password String that is not hashed
     * @return String that is encrypted
     * @throws NoSuchAlgorithmException if messageDigest do not find the algorithm
     */
    public static String encrypt_sha(String password) throws NoSuchAlgorithmException {
        //TODO traduire: ajoute le salt pour éviter de se référencer à des dictionnaires
        //TODO si possible, faire un salt dynamique pour une meilleure sécurité
        password += SALT;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //TODO traduire
        //convertir le tableau de bits en une format hexadécimal
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
