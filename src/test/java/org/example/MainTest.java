package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void hexPubKey2address() {
        String pubKeyFromVault = "02ae70f9a721d3cfd1bfaab5c126340d4f5fcc9c8a72f565a0fe599f351756b176";
        String expectation = "link1486vw3rzfsufrpza64xjdwmq4qn59jz59uaxpt";
        assertEquals(expectation, Main.hexPubKey2address(pubKeyFromVault));
    }
}