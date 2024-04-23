package com.storage.springbootquiz;

import com.storage.springbootquiz.client.model.Client;
import com.storage.springbootquiz.client.repoistory.ClientRepository;
import com.storage.springbootquiz.product.model.Product;
import com.storage.springbootquiz.product.model.ProductDetails;
import com.storage.springbootquiz.product.repoistory.ProductDetailsRepository;
import com.storage.springbootquiz.product.repoistory.ProductRepository;
import com.storage.springbootquiz.product.service.ProductDetailsService;
import com.storage.springbootquiz.sales.model.Sales;
import com.storage.springbootquiz.sales.model.Seller;
import com.storage.springbootquiz.sales.model.Transaction;
import com.storage.springbootquiz.sales.repoistory.SalesRepository;
import com.storage.springbootquiz.sales.repoistory.SellerRepository;
import com.storage.springbootquiz.sales.repoistory.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class dummyGenerator {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public void generateDummyData(int numberOfSales, int numberOfProducts, int numberOfClients, int numberOfSellers) {
        generateDummyProducts(numberOfProducts);
        generateDummyClients(numberOfClients);
        generateDummySellers(numberOfSellers);
        generateDummySalesData(numberOfSales);
    }

    private void generateDummyProducts(int numberOfProducts) {
        Random random = new Random();
        for (int i = 0; i < numberOfProducts; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setDescription("Description of Product " + i);
            product.setCategory("Category " + (i % 3)); // Example category
            product.setUnitPrice(random.nextDouble() * 100); // Random price between 0 and 100
            product.setCreationDate(new Date());
            productRepository.save(product);
        }
    }

    private void generateDummyClients(int numberOfClients) {
        Random random = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            Client client = new Client();
            client.setName("Client " + i);
            client.setLastName("Last Name " + i);
            client.setMobile("123456789" + i); // Example mobile number
            clientRepository.save(client);
        }
    }

    private void generateDummySellers(int numberOfSellers) {
        Random random = new Random();
        for (int i = 0; i < numberOfSellers; i++) {
            Seller seller = new Seller();
            seller.setName("Seller " + i);
            seller.setLastName("Last Name " + i);
            seller.setMobile("987654321" + i); // Example mobile number
            sellerRepository.save(seller);
        }
    }

    private void generateDummyTransactions(int numberOfTransactions, Long salesId) {
        Random random = new Random();
        for (int i = 0; i < numberOfTransactions; i++) {
            Transaction transaction = new Transaction();
            transaction.setAmount(random.nextDouble() * 100);
            transaction.setSalesId(salesId);
            transactionRepository.save(transaction);
        }
    }

    private void generateDummySalesData(int numberOfSales) {
        Random random = new Random();

        List<Product> products = productRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        List<Seller> sellers = sellerRepository.findAll();

        for (int i = 0; i < numberOfSales; i++) {
            Sales sales = new Sales();
            sales.setCreationDate(new Date());
            sales.setClient(clients.get(random.nextInt(clients.size())));
            sales.setSeller(sellers.get(random.nextInt(sellers.size())));


            int numberOfProducts = random.nextInt(5) + 1; // Generate 1 to 5 products per sale
            double total = 0.0;

            List<ProductDetails> productDetailsList = new ArrayList<>();
            for (int j = 0; j < numberOfProducts; j++) {
                ProductDetails productDetails = new ProductDetails();
                Product product = products.get(random.nextInt(products.size()));
                productDetails.setQuantity(random.nextInt(10) + 1); // Generate quantity between 1 and 10
                productDetails.setSalesPrice(product.getUnitPrice() + 25);
                productDetails.setProductId(product.getId());
                total += productDetails.getQuantity() * productDetails.getSalesPrice();
                productDetailsList.add(productDetails);
            }

            sales.setProductDetails(productDetailsList);
            sales.setTotal(total);
            salesRepository.save(sales);
            generateDummyTransactions(3, sales.getId());

        }
    }

}
