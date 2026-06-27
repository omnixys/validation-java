package com.omnixys.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.omnixys.commons.model.ValidationError;

import java.util.List;
import java.util.Set;

public class JsonSchemaValidator implements SchemaValidator {

    private final ObjectMapper objectMapper;
    private final JsonSchemaFactory schemaFactory;

    public JsonSchemaValidator() {
        this(new ObjectMapper());
    }

    public JsonSchemaValidator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
    }

    @Override
    public List<ValidationError> validate(Object payload, ContractSchema schema) {
        try {
            JsonNode jsonNode = objectMapper.valueToTree(payload);
            var jsonSchema = schemaFactory.getSchema(schema.jsonSchema());
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
            if (errors.isEmpty()) {
                return List.of();
            }
            return errors.stream()
                    .map(e -> new ValidationError(
                            e.getInstanceLocation() != null ? e.getInstanceLocation().toString() : null,
                            e.getMessage(),
                            e.getCode() != null ? e.getCode() : "schema.validation"
                    ))
                    .toList();
        } catch (Exception e) {
            return List.of(new ValidationError(null, "Schema validation error: " + e.getMessage(), "schema.error"));
        }
    }
}
