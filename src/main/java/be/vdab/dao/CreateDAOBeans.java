package be.vdab.dao;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan("be.vdab.dao")//Je geeft aan dat Spring één bean moet maken per class met de annotation @Repository in de package be.vdab.dao
@PropertySource("classpath:/database.properties")
@EnableJpaRepositories(basePackageClasses={CreateDAOBeans.class})//Je geeft bij basePackageClasses een class mee. Spring data zoekt alle DAO interface in de package waartoe deze class behoort en maakt zelf implementatieclasses van deze interfaces.
public class CreateDAOBeans {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private Environment environment;

	/*
	@Bean(destroyMethod = "close")
	ComboPooledDataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(environment.getProperty("database.driverClass"));
			dataSource.setJdbcUrl(environment.getProperty("database.jdbcUrl"));
			dataSource.setUser(environment.getProperty("database.user"));
			dataSource.setPassword(environment.getProperty("database.password"));
			dataSource.setMaxIdleTime(environment.getProperty("database.maxIdleTime", Integer.class));
		} catch (Exception ex) {
			logger.severe("invalid C3P0 properties:" + ex.getMessage());
		}
		return dataSource;
	}*/
//	/*
	@Bean
	DataSource dataSource() {
		return new JndiDataSourceLookup().getDataSource("jdbc/personeel");//return new JndiDataSourceLookup().getDataSource("java:comp/env/jdbc/personeel"); 
	}
//	*/
	

//	/*
	@Bean 
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {   
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();   
		entityManagerFactoryBean.setDataSource(dataSource()); //(1) De bean dataSource bevat de connection pool naar de database.  Je injecteert deze bean in de huidige bean.  De huidige bean beheert de JPA EntityManagerFactory.  De huidige bean configureert deze EntityManagerFactory  zodat hij connecties haalt uit de connection pool van de bean dataSource. 
		entityManagerFactoryBean.setPackagesToScan(     
				"be.vdab.entities");//, "be.vdab.valueobjects"); //(2) Je moet in persistence.xml de entity classes en de value object classes  één per één vermelden. Je moet in de huidige bean enkel de packages vermelden die entities of value object classes bevatten. Dit is minder werk. Je vermeldt deze packages in een property packagesToScan.  
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();//(3)De class HibernateJpaVendorAdapter stelt de configuratie van één specifieke JPA implementatie (Hibernate) voor.
		vendorAdapter.setShowSql(     
				environment.getProperty("database.showSql", Boolean.class)); //(4)Je plaatst de Hibernate eigenschap showSQL op true (vanuit database.properties) Je ziet zo welke SQL statements Hibernate naar de database stuurt.   
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter); //(5)   
		return entityManagerFactoryBean;		
	}
	
	@Bean
	JpaTransactionManager transactionManager() {// (1)Je vervangt de class DataSourceTransactionManager (die bij JDBC hoort)  door de class JPATransactionManager (die bij JPA hoort). 
		return new JpaTransactionManager(entityManagerFactory().getObject());//Je verbindt de JPATransactionManager met je entityManager die je krijgt van de EntityManagerFactoryBean method getObject. 
	}
//	*/
}