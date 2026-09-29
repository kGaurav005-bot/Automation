package org.practice.framework.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Set;

public final class SensitiveDataMasker {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper();

    private static final Set<String> SENSITIVE_FIELDS = Set.of(
            "password",
            "passwd",
            "token",
            "access_token",
            "refresh_token",
            "authorization",
            "client_secret",
            "secret",
            "ssn",
            "socialSecurityNumber"
    );

    private static final String MASK = "********";

    private SensitiveDataMasker() {
    }

    public static String maskJson(String json) {

        if (json == null || json.isBlank()) {
            return json;
        }

        try {

            JsonNode root =
                    OBJECT_MAPPER.readTree(json);

            maskNode(root);

            return OBJECT_MAPPER
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(root);

        } catch (Exception e) {

            // Do not expose the original payload
            return "[Unable to safely parse payload]";
        }
    }

    private static void maskNode(JsonNode node) {

        if (node.isObject()) {

            ObjectNode objectNode =
                    (ObjectNode) node;

            objectNode.fieldNames()
                    .forEachRemaining(fieldName -> {

                        JsonNode value =
                                objectNode.get(fieldName);

                        if (isSensitive(fieldName)) {

                            objectNode.put(
                                    fieldName,
                                    MASK
                            );

                        } else {

                            maskNode(value);
                        }
                    });
        }

        if (node.isArray()) {

            for (JsonNode child : node) {
                maskNode(child);
            }
        }
    }

    private static boolean isSensitive(
            String fieldName) {

        return SENSITIVE_FIELDS
                .stream()
                .anyMatch(field ->
                        field.equalsIgnoreCase(
                                fieldName
                        ));
    }
}