package version_1_3.api.jsonschemas;

public class IDContactType {

    public static String IDContactTypeSchema = """
                        {
                        "$schema": "http://json-schema.org/draft-07/schema#",
                        "type": "object",
                        "properties": {
                    "@odata.context": {
                        "type": "string"
                    },
                    "value": {
                        "type": "array",
                                "items": {
                            "type": "object",
                                    "properties": {
                                "Id": {
                                    "type": "string"
                                }
                            },
                            "additionalProperties": false,
                                    "required": [
                            "Id"
                            ]
                        }
                    }
                },
                        "additionalProperties": false,
                        "required": [
                        "@odata.context",
                        "value"
                        ]
            }""";
}
