package com.omnixys.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractSchemaRegistryTest {

    @Test
    void shouldRegisterAndRetrieveSchema() {
        var registry = new ContractSchemaRegistry();
        var schema = new ContractSchema("test", "1.0", "{}");
        registry.register(schema);

        assertTrue(registry.hasSchema("test", "1.0"));
        assertEquals(schema, registry.get("test", "1.0").orElseThrow());
        assertEquals(1, registry.size());
    }

    @Test
    void shouldReturnEmptyForUnknownSchema() {
        var registry = new ContractSchemaRegistry();
        assertFalse(registry.get("unknown", "1.0").isPresent());
    }

    @Test
    void shouldAllowMultipleVersions() {
        var registry = new ContractSchemaRegistry();
        registry.register(new ContractSchema("test", "1.0", "{}"));
        registry.register(new ContractSchema("test", "2.0", "{}"));

        assertEquals(2, registry.size());
        assertTrue(registry.get("test", "1.0").isPresent());
        assertTrue(registry.get("test", "2.0").isPresent());
    }
}
