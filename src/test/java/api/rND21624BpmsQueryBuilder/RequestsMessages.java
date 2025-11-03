package api.rND21624BpmsQueryBuilder;

public class RequestsMessages {
    public static final String expectedGetTablesNameErrorJson = """
              {
                   "GetTablesNameResult": {
                       "tableNames": null,
                       "responseStatus": null,
                       "queryId": null,
                       "rowsAffected": 0,
                       "nextPrcElReady": false,
                       "success": false,
                       "errorInfo": {
                           "errorCode": "OperationRightsException",
                           "message": "Недостаточно прав для чтения данных через Конструктор Запросов. Обратитесь к администратору системы.",
                           "stackTrace": "   at BPMSoft.Configuration.BpmsOperationChecker.OperationChecker.CheckContainsOneOperation(String errorText, String[] operationCodes)\\r\\n   at BPMSoft.Configuration.BpmsQueryBuilderService.CheckCanUseMethodByOperation()\\r\\n   at BPMSoft.Configuration.BpmsQueryBuilderService.GetTablesName()"
                       }
                   }
               }
            """;
    public static final String erroreMessageGetTablesName = "Недостаточно прав для чтения данных через Конструктор Запросов. Обратитесь к администратору системы.";
    public static final String expectedGetTableCaptionsErrorJson = """
            {
                "GetTableCaptionsResult": {
                    "tableCaptions": null,
                    "responseStatus": null,
                    "queryId": null,
                    "rowsAffected": -1,
                    "nextPrcElReady": false,
                    "success": false,
                    "errorInfo": {
                        "errorCode": "OperationRightsException",
                        "message": "Недостаточно прав для чтения данных через Конструктор Запросов. Обратитесь к администратору системы.",
                        "stackTrace": "${ignore}"
                    }
                }
            }
            """;
    public static final String getExpectedGetTableCaptionsErrorJsonShema = """
                {
                  "$schema": "http://json-schema.org/draft-07/schema#",
                  "type": "object",
                  "properties": {
                    "GetTableCaptionsResult": {
                      "type": "object",
                      "properties": {
                        "tableCaptions": { "type": ["null", "array"] },
                        "responseStatus": { "type": ["null", "object"] },
                        "queryId": { "type": ["null", "string"] },
                        "rowsAffected": { "type": "integer" },
                        "nextPrcElReady": { "type": "boolean" },
                        "success": { "type": "boolean" },
                        "errorInfo": {
                          "type": "object",
                          "properties": {
                            "errorCode": { "type": "string" },
                            "message": { "type": "string" },
                            "stackTrace": { "type": "string" }
                          },
                          "required": ["errorCode", "message"]
                        }
                      },
                      "required": ["rowsAffected", "success", "errorInfo"]
                    }
                  },
                  "required": ["GetTableCaptionsResult"]
                }
            """;

    /*тело ответа проверяется в запросе
                .body("Code", equalTo(0))
                .body("Message", equalTo(""))
                .body("Exception", nullValue())
                .body("PasswordChangeUrl", nullValue())
                .body("RedirectUrl", nullValue())
                * */
    public static final String expectedAuthBodyOk = """
            {
                "Code": 0,
                "Message": "",
                "Exception": null,
                "PasswordChangeUrl": null,
                "RedirectUrl": null
            }
            """;

}
