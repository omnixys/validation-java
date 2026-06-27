package com.omnixys.validation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ContractSchemaRegistry {

    private final Map<String, ContractSchema> schemas = new ConcurrentHashMap<>();

    public void register(ContractSchema schema) {
        if (schema == null) {
            throw new IllegalArgumentException("schema must not be null");
        }
        schemas.put(key(schema.name(), schema.version()), schema);
    }

    public Optional<ContractSchema> get(String name, String version) {
        return Optional.ofNullable(schemas.get(key(name, version)));
    }

    public boolean hasSchema(String name, String version) {
        return schemas.containsKey(key(name, version));
    }

    public int size() {
        return schemas.size();
    }

    public void clear() {
        schemas.clear();
    }

    private static String key(String name, String version) {
        return name + "@" + version;
    }
}
