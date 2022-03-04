package com.biscuit.commands.epic;

import java.io.IOException;

import static org.junit.Assert.*;

class WikiTest {
    public void AddWikiExecuteTest() {


    }
    public void AddWikinotNull() {

        try {
            assertNotNull("hi", AddWiki.setTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}