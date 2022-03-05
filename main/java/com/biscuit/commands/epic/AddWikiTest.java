package com.biscuit.commands.epic;

import java.io.IOException;

import static org.junit.Assert.*;

class WikiTest {

    public void AddWikinotNull() {

        try {
            assertNotNull("Hello", AddWiki.setTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}