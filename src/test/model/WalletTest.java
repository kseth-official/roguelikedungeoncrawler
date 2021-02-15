package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Wallet Class
public class WalletTest {
    Wallet wallet;

    @BeforeEach
    public void setup() {
        wallet = new Wallet(2000);
    }

    @Test
    public void testConstructor() {
        assertEquals(wallet.getBalance(),2000);
    }

    @Test
    public void testAddAmount() {
        wallet.addAmount(2000);
        assertEquals(wallet.getBalance(),4000);
    }
}