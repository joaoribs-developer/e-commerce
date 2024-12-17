package com.helloworld;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;


@Configuration
public class AppConfig{

//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://webapp-db.c1ccmqm66qr5.us-east-1.rds.amazonaws.com:5432/webapp-db");
		config.setUsername("postgres");
		config.setPassword("123456789");
		return new HikariDataSource(config);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@PostConstruct
	public void postConstruct() {
		JdbcTemplate jdbcTemplate = jdbcTemplate(dataSource());
		String createTablesSQL =
				"CREATE TABLE IF NOT EXISTS Produto (" +
						"id SERIAL PRIMARY KEY, " +
						"nome VARCHAR(255) NOT NULL, " +
						"descricao TEXT, " +
						"tamanho_volume VARCHAR(100)" +
						"); " +
						"CREATE TABLE IF NOT EXISTS Estoque (" +
						"id SERIAL PRIMARY KEY, " +
						"productId INT NOT NULL REFERENCES Produto(id) ON DELETE CASCADE, " +
						"valor DECIMAL(10, 2) NOT NULL, " +
						"quantidade INT NOT NULL" +
						"); " +
						"CREATE TABLE IF NOT EXISTS Compra (" +
						"id SERIAL PRIMARY KEY, " +
						"userId INT NOT NULL, " +
						"estoqueId INT NOT NULL REFERENCES Estoque(id) ON DELETE CASCADE, " +
						"status VARCHAR(50) NOT NULL, " +
						"data_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
						"); " +
						"CREATE TABLE IF NOT EXISTS Dados_da_Compra (" +
						"id SERIAL PRIMARY KEY, " +
						"compraId INT NOT NULL REFERENCES Compra(id) ON DELETE CASCADE, " +
						"formaPagamento VARCHAR(50) NOT NULL, " +
						"data_entrega TIMESTAMP NOT NULL, " +
						"enderecoId INT NOT NULL" +
						");";

		// Inserção de dados iniciais
		String insertDataSQL =
				"INSERT INTO Produto (nome, descricao, tamanho_volume) VALUES " +
						"('Produto A', 'Descrição do Produto A', '1kg') " +
						"ON CONFLICT (nome) DO NOTHING; " +
						"INSERT INTO Estoque (productId, valor, quantidade) VALUES " +
						"(1, 19.99, 100) " +
						"ON CONFLICT (productId) DO NOTHING; " +
						"INSERT INTO Compra (userId, estoqueId, status, data_compra) VALUES " +
						"(101, 1, 'Finalizado', '2024-12-16 10:00:00') " +
						"ON CONFLICT (userId, estoqueId, data_compra) DO NOTHING; " +
						"INSERT INTO Dados_da_Compra (compraId, formaPagamento, data_entrega, enderecoId) VALUES " +
						"(1, 'Cartão de Crédito', '2024-12-18 10:00:00', 5001) " +
						"ON CONFLICT (compraId) DO NOTHING;";

		// Executando os comandos SQL
		jdbcTemplate.execute(createTablesSQL);
		jdbcTemplate.execute(insertDataSQL);
	}
}