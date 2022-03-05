package com.biscuit.commands.epic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class AddWikiTest {

        public void AddWikinotNull() {

            try {
                assertNotNull("Hello", AddWiki.setTitle());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}