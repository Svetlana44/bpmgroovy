package errorparser;

import java.util.Properties;

public class StatementsSQL {
    private final Properties properties;
    public String ssmsSELECTerrors;
    public String ssmsSELECTerrorsNotNeedInstall;

    StatementsSQL(ErrorDB errorDB) {
        this.properties = errorDB.getProperties();
        String databaseName = this.properties.getProperty("databaseName");

        /* наличие ошибки LastError  */
        this.ssmsSELECTerrors = "SELECT DISTINCT sysPack.Name  PackName,"
                + " sysPackSchData.LastError , "
                + " sysPackSchData.NeedInstall , "
                + " sysPackSchData.Name"
                + " FROM  [" + databaseName + "].dbo.SysPackage sysPack "
                + " INNER JOIN [" + databaseName + "].dbo.SysPackageSchemaData sysPackSchData "
                + "  ON sysPack.Id = sysPackSchData.SysPackageId "
                + "  WHERE "
                + " sysPackSchData.LastError != '' "
                + "  UNION "
                + " SELECT DISTINCT sysPack.Name  PackName , "
                + " SysPackageSqlScript.LastError , "
                + " SysPackageSqlScript.NeedInstall , "
                + "  SysPackageSqlScript.Name "
                + " FROM  [" + databaseName + "].dbo.SysPackage sysPack "
                + " INNER JOIN [" + databaseName + "].dbo.SysPackageSqlScript SysPackageSqlScript "
                + " ON SysPackageSqlScript.SysPackageId = sysPack.Id"
                + "  WHERE "
                + " SysPackageSqlScript.LastError != '';";

        /* При наличии ошибки LastError, поле NeedInstall 0. Такого не должно быть */
        this.ssmsSELECTerrorsNotNeedInstall = "SELECT DISTINCT sysPack.Name  PackName,"
                + " sysPackSchData.LastError , "
                + " sysPackSchData.NeedInstall , "
                + " sysPackSchData.Name"
                + " FROM  [" + databaseName + "].dbo.SysPackage sysPack "
                + " INNER JOIN [" + databaseName + "].dbo.SysPackageSchemaData sysPackSchData "
                + "  ON sysPack.Id = sysPackSchData.SysPackageId "
                + "  WHERE "
                + " sysPackSchData.LastError != '' AND sysPackSchData.NeedInstall !=1 "
                + "  UNION "
                + " SELECT DISTINCT sysPack.Name  PackName , "
                + " SysPackageSqlScript.LastError , "
                + " SysPackageSqlScript.NeedInstall , "
                + "  SysPackageSqlScript.Name "
                + " FROM  [" + databaseName + "].dbo.SysPackage sysPack "
                + " INNER JOIN [" + databaseName + "].dbo.SysPackageSqlScript SysPackageSqlScript "
                + " ON SysPackageSqlScript.SysPackageId = sysPack.Id"
                + "  WHERE "
                + " SysPackageSqlScript.LastError != '' AND SysPackageSqlScript.NeedInstall !=1;";
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