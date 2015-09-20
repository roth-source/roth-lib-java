package roth.lib.java.db;

import java.sql.DatabaseMetaData;
import java.sql.RowIdLifetime;
import java.sql.SQLException;

public abstract class DbDatabaseMetaData implements DatabaseMetaData, DbWrapper
{
	protected DatabaseMetaData databaseMetaData;
	
	public DbDatabaseMetaData(DatabaseMetaData databaseMetaData)
	{
		this.databaseMetaData = databaseMetaData;
	}
	
	public <T> T unwrap(Class<T> iface) throws SQLException
	{
		return databaseMetaData.unwrap(iface);
	}
	
	public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		return databaseMetaData.isWrapperFor(iface);
	}
	
	public boolean allProceduresAreCallable() throws SQLException
	{
		return databaseMetaData.allProceduresAreCallable();
	}
	
	public boolean allTablesAreSelectable() throws SQLException
	{
		return databaseMetaData.allTablesAreSelectable();
	}
	
	public String getURL() throws SQLException
	{
		return databaseMetaData.getURL();
	}
	
	public String getUserName() throws SQLException
	{
		return databaseMetaData.getUserName();
	}
	
	public boolean isReadOnly() throws SQLException
	{
		return databaseMetaData.isReadOnly();
	}
	
	public boolean nullsAreSortedHigh() throws SQLException
	{
		return databaseMetaData.nullsAreSortedHigh();
	}
	
	public boolean nullsAreSortedLow() throws SQLException
	{
		return databaseMetaData.nullsAreSortedLow();
	}
	
	public boolean nullsAreSortedAtStart() throws SQLException
	{
		return databaseMetaData.nullsAreSortedAtStart();
	}
	
	public boolean nullsAreSortedAtEnd() throws SQLException
	{
		return databaseMetaData.nullsAreSortedAtEnd();
	}
	
	public String getDatabaseProductName() throws SQLException
	{
		return databaseMetaData.getDatabaseProductName();
	}
	
	public String getDatabaseProductVersion() throws SQLException
	{
		return databaseMetaData.getDatabaseProductVersion();
	}
	
	public String getDriverName() throws SQLException
	{
		return databaseMetaData.getDriverName();
	}
	
	public String getDriverVersion() throws SQLException
	{
		return databaseMetaData.getDriverVersion();
	}
	
	public int getDriverMajorVersion()
	{
		return databaseMetaData.getDriverMajorVersion();
	}
	
	public int getDriverMinorVersion()
	{
		return databaseMetaData.getDriverMinorVersion();
	}
	
	public boolean usesLocalFiles() throws SQLException
	{
		return databaseMetaData.usesLocalFiles();
	}
	
	public boolean usesLocalFilePerTable() throws SQLException
	{
		return databaseMetaData.usesLocalFilePerTable();
	}
	
	public boolean supportsMixedCaseIdentifiers() throws SQLException
	{
		return databaseMetaData.supportsMixedCaseIdentifiers();
	}
	
	public boolean storesUpperCaseIdentifiers() throws SQLException
	{
		return databaseMetaData.storesUpperCaseIdentifiers();
	}
	
	public boolean storesLowerCaseIdentifiers() throws SQLException
	{
		return databaseMetaData.storesLowerCaseIdentifiers();
	}
	
	public boolean storesMixedCaseIdentifiers() throws SQLException
	{
		return databaseMetaData.storesMixedCaseIdentifiers();
	}
	
	public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException
	{
		return databaseMetaData.supportsMixedCaseQuotedIdentifiers();
	}
	
	public boolean storesUpperCaseQuotedIdentifiers() throws SQLException
	{
		return databaseMetaData.storesUpperCaseQuotedIdentifiers();
	}
	
	public boolean storesLowerCaseQuotedIdentifiers() throws SQLException
	{
		return databaseMetaData.storesLowerCaseQuotedIdentifiers();
	}
	
	public boolean storesMixedCaseQuotedIdentifiers() throws SQLException
	{
		return databaseMetaData.storesMixedCaseQuotedIdentifiers();
	}
	
	public String getIdentifierQuoteString() throws SQLException
	{
		return databaseMetaData.getIdentifierQuoteString();
	}
	
	public String getSQLKeywords() throws SQLException
	{
		return databaseMetaData.getSQLKeywords();
	}
	
	public String getNumericFunctions() throws SQLException
	{
		return databaseMetaData.getNumericFunctions();
	}
	
	public String getStringFunctions() throws SQLException
	{
		return databaseMetaData.getStringFunctions();
	}
	
	public String getSystemFunctions() throws SQLException
	{
		return databaseMetaData.getSystemFunctions();
	}
	
	public String getTimeDateFunctions() throws SQLException
	{
		return databaseMetaData.getTimeDateFunctions();
	}
	
	public String getSearchStringEscape() throws SQLException
	{
		return databaseMetaData.getSearchStringEscape();
	}
	
	public String getExtraNameCharacters() throws SQLException
	{
		return databaseMetaData.getExtraNameCharacters();
	}
	
	public boolean supportsAlterTableWithAddColumn() throws SQLException
	{
		return databaseMetaData.supportsAlterTableWithAddColumn();
	}
	
	public boolean supportsAlterTableWithDropColumn() throws SQLException
	{
		return databaseMetaData.supportsAlterTableWithDropColumn();
	}
	
	public boolean supportsColumnAliasing() throws SQLException
	{
		return databaseMetaData.supportsColumnAliasing();
	}
	
	public boolean nullPlusNonNullIsNull() throws SQLException
	{
		return databaseMetaData.nullPlusNonNullIsNull();
	}
	
	public boolean supportsConvert() throws SQLException
	{
		return databaseMetaData.supportsConvert();
	}
	
	public boolean supportsConvert(int fromType, int toType) throws SQLException
	{
		return databaseMetaData.supportsConvert(fromType, toType);
	}
	
	public boolean supportsTableCorrelationNames() throws SQLException
	{
		return databaseMetaData.supportsTableCorrelationNames();
	}
	
	public boolean supportsDifferentTableCorrelationNames() throws SQLException
	{
		return databaseMetaData.supportsDifferentTableCorrelationNames();
	}
	
	public boolean supportsExpressionsInOrderBy() throws SQLException
	{
		return databaseMetaData.supportsExpressionsInOrderBy();
	}
	
	public boolean supportsOrderByUnrelated() throws SQLException
	{
		return databaseMetaData.supportsOrderByUnrelated();
	}
	
	public boolean supportsGroupBy() throws SQLException
	{
		return databaseMetaData.supportsGroupBy();
	}
	
	public boolean supportsGroupByUnrelated() throws SQLException
	{
		return databaseMetaData.supportsGroupByUnrelated();
	}
	
	public boolean supportsGroupByBeyondSelect() throws SQLException
	{
		return databaseMetaData.supportsGroupByBeyondSelect();
	}
	
	public boolean supportsLikeEscapeClause() throws SQLException
	{
		return databaseMetaData.supportsLikeEscapeClause();
	}
	
	public boolean supportsMultipleResultSets() throws SQLException
	{
		return databaseMetaData.supportsMultipleResultSets();
	}
	
	public boolean supportsMultipleTransactions() throws SQLException
	{
		return databaseMetaData.supportsMultipleTransactions();
	}
	
	public boolean supportsNonNullableColumns() throws SQLException
	{
		return databaseMetaData.supportsNonNullableColumns();
	}
	
	public boolean supportsMinimumSQLGrammar() throws SQLException
	{
		return databaseMetaData.supportsMinimumSQLGrammar();
	}
	
	public boolean supportsCoreSQLGrammar() throws SQLException
	{
		return databaseMetaData.supportsCoreSQLGrammar();
	}
	
	public boolean supportsExtendedSQLGrammar() throws SQLException
	{
		return databaseMetaData.supportsExtendedSQLGrammar();
	}
	
	public boolean supportsANSI92EntryLevelSQL() throws SQLException
	{
		return databaseMetaData.supportsANSI92EntryLevelSQL();
	}
	
	public boolean supportsANSI92IntermediateSQL() throws SQLException
	{
		return databaseMetaData.supportsANSI92IntermediateSQL();
	}
	
	public boolean supportsANSI92FullSQL() throws SQLException
	{
		return databaseMetaData.supportsANSI92FullSQL();
	}
	
	public boolean supportsIntegrityEnhancementFacility() throws SQLException
	{
		return databaseMetaData.supportsIntegrityEnhancementFacility();
	}
	
	public boolean supportsOuterJoins() throws SQLException
	{
		return databaseMetaData.supportsOuterJoins();
	}
	
	public boolean supportsFullOuterJoins() throws SQLException
	{
		return databaseMetaData.supportsFullOuterJoins();
	}
	
	public boolean supportsLimitedOuterJoins() throws SQLException
	{
		return databaseMetaData.supportsLimitedOuterJoins();
	}
	
	public String getSchemaTerm() throws SQLException
	{
		return databaseMetaData.getSchemaTerm();
	}
	
	public String getProcedureTerm() throws SQLException
	{
		return databaseMetaData.getProcedureTerm();
	}
	
	public String getCatalogTerm() throws SQLException
	{
		return databaseMetaData.getCatalogTerm();
	}
	
	public boolean isCatalogAtStart() throws SQLException
	{
		return databaseMetaData.isCatalogAtStart();
	}
	
	public String getCatalogSeparator() throws SQLException
	{
		return databaseMetaData.getCatalogSeparator();
	}
	
	public boolean supportsSchemasInDataManipulation() throws SQLException
	{
		return databaseMetaData.supportsSchemasInDataManipulation();
	}
	
	public boolean supportsSchemasInProcedureCalls() throws SQLException
	{
		return databaseMetaData.supportsSchemasInProcedureCalls();
	}
	
	public boolean supportsSchemasInTableDefinitions() throws SQLException
	{
		return databaseMetaData.supportsSchemasInTableDefinitions();
	}
	
	public boolean supportsSchemasInIndexDefinitions() throws SQLException
	{
		return databaseMetaData.supportsSchemasInIndexDefinitions();
	}
	
	public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException
	{
		return databaseMetaData.supportsSchemasInPrivilegeDefinitions();
	}
	
	public boolean supportsCatalogsInDataManipulation() throws SQLException
	{
		return databaseMetaData.supportsCatalogsInDataManipulation();
	}
	
	public boolean supportsCatalogsInProcedureCalls() throws SQLException
	{
		return databaseMetaData.supportsCatalogsInProcedureCalls();
	}
	
	public boolean supportsCatalogsInTableDefinitions() throws SQLException
	{
		return databaseMetaData.supportsCatalogsInTableDefinitions();
	}
	
	public boolean supportsCatalogsInIndexDefinitions() throws SQLException
	{
		return databaseMetaData.supportsCatalogsInIndexDefinitions();
	}
	
	public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException
	{
		return databaseMetaData.supportsCatalogsInPrivilegeDefinitions();
	}
	
	public boolean supportsPositionedDelete() throws SQLException
	{
		return databaseMetaData.supportsPositionedDelete();
	}
	
	public boolean supportsPositionedUpdate() throws SQLException
	{
		return databaseMetaData.supportsPositionedUpdate();
	}
	
	public boolean supportsSelectForUpdate() throws SQLException
	{
		return databaseMetaData.supportsSelectForUpdate();
	}
	
	public boolean supportsStoredProcedures() throws SQLException
	{
		return databaseMetaData.supportsStoredProcedures();
	}
	
	public boolean supportsSubqueriesInComparisons() throws SQLException
	{
		return databaseMetaData.supportsSubqueriesInComparisons();
	}
	
	public boolean supportsSubqueriesInExists() throws SQLException
	{
		return databaseMetaData.supportsSubqueriesInExists();
	}
	
	public boolean supportsSubqueriesInIns() throws SQLException
	{
		return databaseMetaData.supportsSubqueriesInIns();
	}
	
	public boolean supportsSubqueriesInQuantifieds() throws SQLException
	{
		return databaseMetaData.supportsSubqueriesInQuantifieds();
	}
	
	public boolean supportsCorrelatedSubqueries() throws SQLException
	{
		return databaseMetaData.supportsCorrelatedSubqueries();
	}
	
	public boolean supportsUnion() throws SQLException
	{
		return databaseMetaData.supportsUnion();
	}
	
	public boolean supportsUnionAll() throws SQLException
	{
		return databaseMetaData.supportsUnionAll();
	}
	
	public boolean supportsOpenCursorsAcrossCommit() throws SQLException
	{
		return databaseMetaData.supportsOpenCursorsAcrossCommit();
	}
	
	public boolean supportsOpenCursorsAcrossRollback() throws SQLException
	{
		return databaseMetaData.supportsOpenCursorsAcrossRollback();
	}
	
	public boolean supportsOpenStatementsAcrossCommit() throws SQLException
	{
		return databaseMetaData.supportsOpenStatementsAcrossCommit();
	}
	
	public boolean supportsOpenStatementsAcrossRollback() throws SQLException
	{
		return databaseMetaData.supportsOpenStatementsAcrossRollback();
	}
	
	public int getMaxBinaryLiteralLength() throws SQLException
	{
		return databaseMetaData.getMaxBinaryLiteralLength();
	}
	
	public int getMaxCharLiteralLength() throws SQLException
	{
		return databaseMetaData.getMaxCharLiteralLength();
	}
	
	public int getMaxColumnNameLength() throws SQLException
	{
		return databaseMetaData.getMaxColumnNameLength();
	}
	
	public int getMaxColumnsInGroupBy() throws SQLException
	{
		return databaseMetaData.getMaxColumnsInGroupBy();
	}
	
	public int getMaxColumnsInIndex() throws SQLException
	{
		return databaseMetaData.getMaxColumnsInIndex();
	}
	
	public int getMaxColumnsInOrderBy() throws SQLException
	{
		return databaseMetaData.getMaxColumnsInOrderBy();
	}
	
	public int getMaxColumnsInSelect() throws SQLException
	{
		return databaseMetaData.getMaxColumnsInSelect();
	}
	
	public int getMaxColumnsInTable() throws SQLException
	{
		return databaseMetaData.getMaxColumnsInTable();
	}
	
	public int getMaxConnections() throws SQLException
	{
		return databaseMetaData.getMaxConnections();
	}
	
	public int getMaxCursorNameLength() throws SQLException
	{
		return databaseMetaData.getMaxCursorNameLength();
	}
	
	public int getMaxIndexLength() throws SQLException
	{
		return databaseMetaData.getMaxIndexLength();
	}
	
	public int getMaxSchemaNameLength() throws SQLException
	{
		return databaseMetaData.getMaxSchemaNameLength();
	}
	
	public int getMaxProcedureNameLength() throws SQLException
	{
		return databaseMetaData.getMaxProcedureNameLength();
	}
	
	public int getMaxCatalogNameLength() throws SQLException
	{
		return databaseMetaData.getMaxCatalogNameLength();
	}
	
	public int getMaxRowSize() throws SQLException
	{
		return databaseMetaData.getMaxRowSize();
	}
	
	public boolean doesMaxRowSizeIncludeBlobs() throws SQLException
	{
		return databaseMetaData.doesMaxRowSizeIncludeBlobs();
	}
	
	public int getMaxStatementLength() throws SQLException
	{
		return databaseMetaData.getMaxStatementLength();
	}
	
	public int getMaxStatements() throws SQLException
	{
		return databaseMetaData.getMaxStatements();
	}
	
	public int getMaxTableNameLength() throws SQLException
	{
		return databaseMetaData.getMaxTableNameLength();
	}
	
	public int getMaxTablesInSelect() throws SQLException
	{
		return databaseMetaData.getMaxTablesInSelect();
	}
	
	public int getMaxUserNameLength() throws SQLException
	{
		return databaseMetaData.getMaxUserNameLength();
	}
	
	public int getDefaultTransactionIsolation() throws SQLException
	{
		return databaseMetaData.getDefaultTransactionIsolation();
	}
	
	public boolean supportsTransactions() throws SQLException
	{
		return databaseMetaData.supportsTransactions();
	}
	
	public boolean supportsTransactionIsolationLevel(int level) throws SQLException
	{
		return databaseMetaData.supportsTransactionIsolationLevel(level);
	}
	
	public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException
	{
		return databaseMetaData.supportsDataDefinitionAndDataManipulationTransactions();
	}
	
	public boolean supportsDataManipulationTransactionsOnly() throws SQLException
	{
		return databaseMetaData.supportsDataManipulationTransactionsOnly();
	}
	
	public boolean dataDefinitionCausesTransactionCommit() throws SQLException
	{
		return databaseMetaData.dataDefinitionCausesTransactionCommit();
	}
	
	public boolean dataDefinitionIgnoredInTransactions() throws SQLException
	{
		return databaseMetaData.dataDefinitionIgnoredInTransactions();
	}
	
	public DbResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getProcedures(catalog, schemaPattern, procedureNamePattern));
	}
	
	public DbResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getProcedureColumns(catalog, schemaPattern, procedureNamePattern, columnNamePattern));
	}
	
	public DbResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException
	{
		return wrap(databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types));
	}
	
	public DbResultSet getSchemas() throws SQLException
	{
		return wrap(databaseMetaData.getSchemas());
	}
	
	public DbResultSet getCatalogs() throws SQLException
	{
		return wrap(databaseMetaData.getCatalogs());
	}
	
	public DbResultSet getTableTypes() throws SQLException
	{
		return wrap(databaseMetaData.getTableTypes());
	}
	
	public DbResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern));
	}
	
	public DbResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getColumnPrivileges(catalog, schema, table, columnNamePattern));
	}
	
	public DbResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getTablePrivileges(catalog, schemaPattern, tableNamePattern));
	}
	
	public DbResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException
	{
		return wrap(databaseMetaData.getBestRowIdentifier(catalog, schema, table, scope, nullable));
	}
	
	public DbResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException
	{
		return wrap(databaseMetaData.getVersionColumns(catalog, schema, table));
	}
	
	public DbResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException
	{
		return wrap(databaseMetaData.getPrimaryKeys(catalog, schema, table));
	}
	
	public DbResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException
	{
		return wrap(databaseMetaData.getImportedKeys(catalog, schema, table));
	}
	
	public DbResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException
	{
		return wrap(databaseMetaData.getExportedKeys(catalog, schema, table));
	}
	
	public DbResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException
	{
		return wrap(databaseMetaData.getCrossReference(parentCatalog, parentSchema, parentTable, foreignCatalog, foreignSchema, foreignTable));
	}
	
	public DbResultSet getTypeInfo() throws SQLException
	{
		return wrap(databaseMetaData.getTypeInfo());
	}
	
	public DbResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException
	{
		return wrap(databaseMetaData.getIndexInfo(catalog, schema, table, unique, approximate));
	}
	
	public boolean supportsResultSetType(int type) throws SQLException
	{
		return databaseMetaData.supportsResultSetType(type);
	}
	
	public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException
	{
		return databaseMetaData.supportsResultSetConcurrency(type, concurrency);
	}
	
	public boolean ownUpdatesAreVisible(int type) throws SQLException
	{
		return databaseMetaData.ownUpdatesAreVisible(type);
	}
	
	public boolean ownDeletesAreVisible(int type) throws SQLException
	{
		return databaseMetaData.ownDeletesAreVisible(type);
	}
	
	public boolean ownInsertsAreVisible(int type) throws SQLException
	{
		return databaseMetaData.ownInsertsAreVisible(type);
	}
	
	public boolean othersUpdatesAreVisible(int type) throws SQLException
	{
		return databaseMetaData.othersUpdatesAreVisible(type);
	}
	
	public boolean othersDeletesAreVisible(int type) throws SQLException
	{
		return databaseMetaData.othersDeletesAreVisible(type);
	}
	
	public boolean othersInsertsAreVisible(int type) throws SQLException
	{
		return databaseMetaData.othersInsertsAreVisible(type);
	}
	
	public boolean updatesAreDetected(int type) throws SQLException
	{
		return databaseMetaData.updatesAreDetected(type);
	}
	
	public boolean deletesAreDetected(int type) throws SQLException
	{
		return databaseMetaData.deletesAreDetected(type);
	}
	
	public boolean insertsAreDetected(int type) throws SQLException
	{
		return databaseMetaData.insertsAreDetected(type);
	}
	
	public boolean supportsBatchUpdates() throws SQLException
	{
		return databaseMetaData.supportsBatchUpdates();
	}
	
	public DbResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException
	{
		return wrap(databaseMetaData.getUDTs(catalog, schemaPattern, typeNamePattern, types));
	}
	
	public DbConnection getConnection() throws SQLException
	{
		return wrap(databaseMetaData.getConnection());
	}
	
	public boolean supportsSavepoints() throws SQLException
	{
		return databaseMetaData.supportsSavepoints();
	}
	
	public boolean supportsNamedParameters() throws SQLException
	{
		return databaseMetaData.supportsNamedParameters();
	}
	
	public boolean supportsMultipleOpenResults() throws SQLException
	{
		return databaseMetaData.supportsMultipleOpenResults();
	}
	
	public boolean supportsGetGeneratedKeys() throws SQLException
	{
		return databaseMetaData.supportsGetGeneratedKeys();
	}
	
	public DbResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getSuperTypes(catalog, schemaPattern, typeNamePattern));
	}
	
	public DbResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getSuperTables(catalog, schemaPattern, tableNamePattern));
	}
	
	public DbResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getAttributes(catalog, schemaPattern, typeNamePattern, attributeNamePattern));
	}
	
	public boolean supportsResultSetHoldability(int holdability) throws SQLException
	{
		return databaseMetaData.supportsResultSetHoldability(holdability);
	}
	
	public int getResultSetHoldability() throws SQLException
	{
		return databaseMetaData.getResultSetHoldability();
	}
	
	public int getDatabaseMajorVersion() throws SQLException
	{
		return databaseMetaData.getDatabaseMajorVersion();
	}
	
	public int getDatabaseMinorVersion() throws SQLException
	{
		return databaseMetaData.getDatabaseMinorVersion();
	}
	
	public int getJDBCMajorVersion() throws SQLException
	{
		return databaseMetaData.getJDBCMajorVersion();
	}
	
	public int getJDBCMinorVersion() throws SQLException
	{
		return databaseMetaData.getJDBCMinorVersion();
	}
	
	public int getSQLStateType() throws SQLException
	{
		return databaseMetaData.getSQLStateType();
	}
	
	public boolean locatorsUpdateCopy() throws SQLException
	{
		return databaseMetaData.locatorsUpdateCopy();
	}
	
	public boolean supportsStatementPooling() throws SQLException
	{
		return databaseMetaData.supportsStatementPooling();
	}
	
	public RowIdLifetime getRowIdLifetime() throws SQLException
	{
		return databaseMetaData.getRowIdLifetime();
	}
	
	public DbResultSet getSchemas(String catalog, String schemaPattern) throws SQLException
	{
		return wrap(databaseMetaData.getSchemas(catalog, schemaPattern));
	}
	
	public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException
	{
		return databaseMetaData.supportsStoredFunctionsUsingCallSyntax();
	}
	
	public boolean autoCommitFailureClosesAllResultSets() throws SQLException
	{
		return databaseMetaData.autoCommitFailureClosesAllResultSets();
	}
	
	public DbResultSet getClientInfoProperties() throws SQLException
	{
		return wrap(databaseMetaData.getClientInfoProperties());
	}
	
	public DbResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getFunctions(catalog, schemaPattern, functionNamePattern));
	}
	
	public DbResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getFunctionColumns(catalog, schemaPattern, functionNamePattern, columnNamePattern));
	}
	
	public DbResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException
	{
		return wrap(databaseMetaData.getPseudoColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern));
	}
	
	public boolean generatedKeyAlwaysReturned() throws SQLException
	{
		return databaseMetaData.generatedKeyAlwaysReturned();
	}
	
}
