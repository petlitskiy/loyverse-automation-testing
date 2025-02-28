package com.backoffice.utilities;

import com.backoffice.pageobjects.ItemListImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

/**
 * @author Neha Kharbanda
 * <p>
 * Utility class to generate unique credentials such as email addresses and passwords.
 */
public class UniqueCredentialsGenerator {
    private Logger log = LogManager.getLogger(ItemListImpl.class);

    public String generateUniqueEmail() {
        int getHour = LocalDateTime.now().getHour();
        int getMinute = LocalDateTime.now().getMinute();
        int getNano = LocalDateTime.now().getNano();
        int getDay = LocalDateTime.now().getDayOfMonth();
        return "Auto" + getHour + getMinute + getNano + getDay + "@mailinator.com";
    }

    public String generateUniquePassword() {
        String upperCaseChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567";
        String specialCharacter = "@#$%^&*-_,<>/?";
        String pwd = RandomStringUtils.random(10, upperCaseChar + specialCharacter + upperCaseChar.toLowerCase());
        return pwd;
    }

}
