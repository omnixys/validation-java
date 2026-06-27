# Omnixys Validation

Contract schema validation and schema registry for cross-service event contracts.

## Features

- ContractSchemaRegistry for registering and validating event schemas
- IdentitySchemas with canonical user/event/ticket schemas
- SchemaValidator for runtime payload validation
- ContractSchema model

## Installation

```xml
<dependency>
    <groupId>com.omnixys</groupId>
    <artifactId>validation</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

```java
@Autowired
private ContractSchemaRegistry registry;

registry.register(schema);
ValidationResult result = registry.validate("user.created", payload);
```
