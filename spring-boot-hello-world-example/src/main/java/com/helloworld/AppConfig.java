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

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void postConstruct() {
		String createTablesSQL =
				"CREATE TABLE IF NOT EXISTS Produto (" +
						"    id SERIAL PRIMARY KEY, " +
						"    nome VARCHAR(255) NOT NULL, " +
						"    descricao TEXT, " +
						"    tamanho_volume VARCHAR(100)" +
						"); " +
						"CREATE TABLE IF NOT EXISTS Estoque (" +
						"    id SERIAL PRIMARY KEY, " +
						"    productId INT NOT NULL, " +
						"    valor DECIMAL(10, 2) NOT NULL, " +
						"    quantidade INT NOT NULL" +
						"); " +
						"CREATE TABLE IF NOT EXISTS Compra (" +
						"    id SERIAL PRIMARY KEY, " +
						"    userId INT NOT NULL, " +
						"    estoqueId INT NOT NULL, " +
						"    status VARCHAR(50) NOT NULL, " +
						"    data_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
						"); " +
						"CREATE TABLE IF NOT EXISTS Dados_da_Compra (" +
						"    id SERIAL PRIMARY KEY, " +
						"    compraId INT NOT NULL, " +
						"    formaPagamento VARCHAR(50) NOT NULL, " +
						"    data_entrega TIMESTAMP NOT NULL, " +
						"    enderecoId INT NOT NULL" +
						");";


		String insertDataSQL =
				"INSERT INTO Produto (id, nome, descricao, tamanho_volume) VALUES " +
						"(1, 'Produto 1', 'Descrição do Produto 1', '500ml'), " +
						"(2, 'Produto 2', 'Descrição do Produto 2', '1L'); " +

						"INSERT INTO Estoque (id, productId, valor, quantidade) VALUES " +
						"(1, 1, 100.50, 50), " +
						"(2, 2, 200.75, 30); " +

						"INSERT INTO Compra (id, userId, estoqueId, status, data_compra) VALUES " +
						"(1, 1, 1, 'Pendente', '2024-12-17 10:00:00'), " +
						"(2, 2, 2, 'Concluída', '2024-12-17 11:00:00'); " +

						"INSERT INTO Dados_da_Compra (id, compraId, formaPagamento, data_entrega, enderecoId) VALUES " +
						"(1, 1, 'Cartão de Crédito', '2024-12-20 10:00:00', 1), " +
						"(2, 2, 'Boleto', '2024-12-22 12:00:00', 2);";


		// Executando os comandos SQL
		jdbcTemplate.execute(createTablesSQL);
//		jdbcTemplate.execute(insertDataSQL);
	}
}