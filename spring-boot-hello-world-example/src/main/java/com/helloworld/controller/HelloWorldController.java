package com.helloworld.controller;

//import com.helloworld.repository.ProdutoRepository;
//import com.helloworld.repository.EstoqueRepository;
//import com.helloworld.repository.CompraRepository;
//import com.helloworld.repository.DadosDaCompraRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

//    @Autowired
//    private ProdutoRepository produtoRepository;
//
//    @Autowired
//    private EstoqueRepository estoqueRepository;
//
//    @Autowired
//    private CompraRepository compraRepository;
//
//    @Autowired
//    private DadosDaCompraRepository dadosDaCompraRepository;

    @RequestMapping("/")
    public String hello() {
        StringBuilder response = new StringBuilder("<h1> Me mama Glaycon seu lixo. </h1>");

//        response.append("<h2>Produtos:</h2>");
//        produtoRepository.findAll().forEach(produto -> {
//            response.append("<p>").append(produto.getNome()).append(" - ").append(produto.getDescricao()).append("</p>");
//        });
//
//        response.append("<h2>Estoque:</h2>");
//        estoqueRepository.findAll().forEach(estoque -> {
//            response.append(" | Valor: ").append(estoque.getValor())
//                    .append(" | Quantidade: ").append(estoque.getQuantidade()).append("</p>");
//        });
//
//        response.append("<h2>Compras:</h2>");
//        compraRepository.findAll().forEach(compra -> {
//            response.append(" | Status: ").append(compra.getStatus())
//                    .append(" | Data da Compra: ").append(compra.getDataCompra()).append("</p>");
//        });
//
//        // Recupera todos os dados de DadosDaCompra
//        response.append("<h2>Dados da Compra:</h2>");
//        dadosDaCompraRepository.findAll().forEach(dadosDaCompra -> {
//            response.append(" | Forma de Pagamento: ").append(dadosDaCompra.getFormaPagamento())
//                    .append(" | Data de Entrega: ").append(dadosDaCompra.getDataEntrega()).append("</p>");
//        });
//
        return response.toString();
    }
}
