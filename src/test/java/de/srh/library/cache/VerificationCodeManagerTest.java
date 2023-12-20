package de.srh.library.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class VerificationCodeManagerTest {


    @Test
    void generateAndCacheVerificationCode() {
        String email = "test@test.coom";
        String verificationCode = VerificationCodeManager.generateAndCacheVerificationCode(email);
        assertEquals(verificationCode, VerificationCodeManager.getVerificationCode(email));
      }

    @Test
    void getVerificationCode() {
        String email = "test@test.coom";
        String verificationCode = VerificationCodeManager.generateAndCacheVerificationCode(email);
        assertEquals(verificationCode, VerificationCodeManager.getVerificationCode(email));
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNull(VerificationCodeManager.getVerificationCode(email));
      }
}