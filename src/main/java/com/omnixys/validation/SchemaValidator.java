package com.omnixys.validation;

import com.omnixys.commons.model.ValidationError;

import java.util.List;

@FunctionalInterface
public interface SchemaValidator {

    List<ValidationError> validate(Object payload, ContractSchema schema);
}
