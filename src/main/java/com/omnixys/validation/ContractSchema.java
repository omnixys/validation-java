package com.omnixys.validation;

public record ContractSchema(
        String name,
        String version,
        String jsonSchema
) {
    public ContractSchema {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (version == null || version.isBlank()) {
            throw new IllegalArgumentException("version must not be blank");
        }
    }
}
