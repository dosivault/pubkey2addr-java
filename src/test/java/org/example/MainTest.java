package org.example;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    static final String EXPECTED_ADDRESS = "link1486vw3rzfsufrpza64xjdwmq4qn59jz59uaxpt";
    @Test
    void base64PubKey2address() {
        String base64Encoded = "Aq5w+ach08/Rv6q1wSY0DU9fzJyKcvVloP5ZnzUXVrF2";
        byte[] pubKey = Base64.getDecoder().decode(base64Encoded);
        String address = Main.pubKey2address(pubKey);
        assertEquals(EXPECTED_ADDRESS, address);
    }
    @Test
    void hexPubKey2address() {
        String pubKeyFromVault = "02ae70f9a721d3cfd1bfaab5c126340d4f5fcc9c8a72f565a0fe599f351756b176";
        assertEquals(EXPECTED_ADDRESS, Main.hexPubKey2address(pubKeyFromVault));
    }
}