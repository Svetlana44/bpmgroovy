package errorparser;

public enum StatementsSQL {
    /* наличие ошибки LastError  */
    ssmsSELECTerrors("SELECT DISTINCT sysPack.Name  PackName,"
            + " sysPackSchData.LastError , "
            + " sysPackSchData.NeedInstall , "
            + " sysPackSchData.Name"
            + " FROM  [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackage sysPack "
            + " INNER JOIN [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackageSchemaData sysPackSchData "
            + "  ON sysPack.Id = sysPackSchData.SysPackageId "
            + "  WHERE "
            + " sysPackSchData.LastError != '' "
            + "  UNION "
            + " SELECT DISTINCT sysPack.Name  PackName , "
            + " SysPackageSqlScript.LastError , "
            + " SysPackageSqlScript.NeedInstall , "
            + "  SysPackageSqlScript.Name "
            + " FROM  [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackage sysPack "
            + " INNER JOIN [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackageSqlScript SysPackageSqlScript "
            + " ON SysPackageSqlScript.SysPackageId = sysPack.Id"
            + "  WHERE "
            + " SysPackageSqlScript.LastError != '';"),
    /* При наличии ошибки LastError, поле NeedInstall 0. Такого не должно быть */
    ssmsSELECTerrorsNotNeedInstall("SELECT DISTINCT sysPack.Name  PackName,"
            + " sysPackSchData.LastError , "
            + " sysPackSchData.NeedInstall , "
            + " sysPackSchData.Name"
            + " FROM  [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackage sysPack "
            + " INNER JOIN [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackageSchemaData sysPackSchData "
            + "  ON sysPack.Id = sysPackSchData.SysPackageId "
            + "  WHERE "
            + " sysPackSchData.LastError != '' AND sysPackSchData.NeedInstall !=1 "
            + "  UNION "
            + " SELECT DISTINCT sysPack.Name  PackName , "
            + " SysPackageSqlScript.LastError , "
            + " SysPackageSqlScript.NeedInstall , "
            + "  SysPackageSqlScript.Name "
            + " FROM  [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackage sysPack "
            + " INNER JOIN [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackageSqlScript SysPackageSqlScript "
            + " ON SysPackageSqlScript.SysPackageId = sysPack.Id"
            + "  WHERE "
            + " SysPackageSqlScript.LastError != '' AND SysPackageSqlScript.NeedInstall !=1;");

    private final String statement;

    StatementsSQL(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}

/*       public String statementSQL = "SELECT DISTINCT sysPack.Name  PackName,"
            + " sysPackSchData.LastError , "
            + " sysPackSchData.NeedInstall , "
            + " sysPackSchData.Name"
            + " FROM  [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackage sysPack "
            + " INNER JOIN [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackageSchemaData sysPackSchData "
            + "  ON sysPack.Id = sysPackSchData.SysPackageId "
            + "  WHERE "
            + " sysPackSchData.LastError != '' "
            + "  UNION "
            + " SELECT DISTINCT sysPack.Name  PackName , "
            + " SysPackageSqlScript.LastError , "
            + " SysPackageSqlScript.NeedInstall , "
            + "  SysPackageSqlScript.Name "
            + " FROM  [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackage sysPack "
            + " INNER JOIN [4468_SVET4_ServiceFull_1.4.0.3084_Net_MsSQL].dbo.SysPackageSqlScript SysPackageSqlScript "
            + " ON SysPackageSqlScript.SysPackageId = sysPack.Id"
            + "  WHERE "
            + " SysPackageSqlScript.LastError != '';";
    */