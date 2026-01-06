package org.jobrunr.examples;

import org.junit.jupiter.api.Test;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import static org.junit.jupiter.api.Assertions.*;

class JobRunrProviderTest {

    @Test
    public void createJsonB() {
        final Jsonb jsonb = JsonbBuilder.create();
        System.out.println(jsonb);
    }

}