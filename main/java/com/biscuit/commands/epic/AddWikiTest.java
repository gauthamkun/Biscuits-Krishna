package com.biscuit.commands.epic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class AddWikiTest {
    public void AddWikiExecuteTest() {

        assertEquals();
    }
    public void AddWikinotNull() {

        try {
            assertNotNull("", AddWiki.execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}