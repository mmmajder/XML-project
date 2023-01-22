package com.example.zigbackend.utils;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class DBSetup {
    public static String setupDBConnection(AuthenticationUtilitiesDB.ConnectionProperties conn) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        String collectionId = "/db/XWS-PROJECT/zig";
        return collectionId;
    }





}
