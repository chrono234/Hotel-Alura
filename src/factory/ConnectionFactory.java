package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooled = new ComboPooledDataSource();
		comboPooled.setJdbcUrl("jdbc:mysql://localhost/hotel_alura_db?useTimeZone=true&serverTimeZone=UTC");
		comboPooled.setUser("root");
		comboPooled.setPassword("figoreti1");
		
		this.dataSource = comboPooled;
	}
	
	public Connection recuperarConexion() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
	