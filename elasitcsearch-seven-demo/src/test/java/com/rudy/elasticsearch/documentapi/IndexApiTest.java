package com.rudy.elasticsearch.documentapi;

import com.rudy.ElasitcsearchSevenDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IndexApiTest extends ElasitcsearchSevenDemoApplicationTests {

    @Autowired
    private IndexApi indexApi;

    @Test
    void createIndex() {
        indexApi.createIndex();
    }

    @Test
    void deleteIndex() {
        indexApi.deleteIndex();
    }

    @Test
    void addDocument() {
        indexApi.addDocument();
    }

    @Test
    void addDocument2() throws IOException {
        indexApi.addDocument2();
    }

    @Test
    void getDocument() {
        indexApi.getDocument();
    }

    @Test
    void updateDocument() {
        indexApi.updateDocument();
    }

    @Test
    void deleteDocument() {
        indexApi.deleteIndex();
    }
}