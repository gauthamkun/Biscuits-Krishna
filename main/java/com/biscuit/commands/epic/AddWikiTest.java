package com.biscuit.commands.epic;

import com.biscuit.models.Project;
import com.biscuit.models.Wiki;
import jline.console.ConsoleReader;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

class WikiTest {
    public void AddWikiExecuteTest() {

        assertEquals();
    }
    public void AddWikinotNull() {

        try {
            assertNotNull("hi", AddWiki.execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}