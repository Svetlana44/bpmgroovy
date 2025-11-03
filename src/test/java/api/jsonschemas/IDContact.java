package api.jsonschemas;

public class IDContact {

    public static String IDContactSchema = """
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
