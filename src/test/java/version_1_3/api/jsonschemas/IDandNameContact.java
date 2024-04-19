package version_1_3.api.jsonschemas;

public class IDandNameContact {
    public static String IDandNameJsonSchema = """
            {
                        "type": "object",
                        "properties": {
                            "@odata.context": {"type": "string"},
                            "value": {
                                "type": "array",
                                "items": {
                                    "type": "object",
                                    "properties": {
                                        "Id": {"type": "string"},
                                        "Name": {"type": "string"}
                                    },
                                    "required": ["Id", "Name"]
                                }
                            }
                        },
                        "required": ["@odata.context", "value"]
                    }""";
}
