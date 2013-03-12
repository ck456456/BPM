package tw.com.prodisc.util;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StandardBasicTypes;

public class UnicodeSQLServerDialect extends SQLServerDialect {
	public UnicodeSQLServerDialect() {
		super();          // Use Unicode Characters         
		registerColumnType(Types.VARCHAR, 255, "nvarchar($l)");
		registerColumnType(Types.CHAR, "nchar(1)");         
		registerColumnType(Types.CLOB, "nvarchar(max)");          // Microsoft SQL Server 2000 supports bigint and bit         
		registerColumnType(Types.BIGINT, "bigint");         
		registerColumnType(Types.BIT, "bit");
		
		registerHibernateType(Types.CHAR, 			StandardBasicTypes.STRING.getName()); 
		registerHibernateType(Types.NVARCHAR, 		StandardBasicTypes.STRING.getName());      
		registerHibernateType(Types.LONGNVARCHAR, 	StandardBasicTypes.STRING.getName());      
		registerHibernateType(Types.DECIMAL, 		StandardBasicTypes.DOUBLE.getName());
	} 
}
