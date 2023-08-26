package br.com.fatec.FatecProjectRestAPI.service;

import br.com.fatec.FatecProjectRestAPI.entity.Product;
import br.com.fatec.FatecProjectRestAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getInfoProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        if (validateProduct(product)) {
            return productRepository.saveAndFlush(product);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O preço de custo e o a quantidade devem ser maior que 0!");
        }
    }

    public HashMap<String, Object> deleteProduct(Long idProduct){
        Optional<Product> product = Optional.ofNullable(productRepository.findById(idProduct)
                .orElseThrow()
        )
    }

    public Boolean validateProduct(Product product) {
        if (product.getCostPriceProduct() != null &&
                product.getCostPriceProduct().compareTo(BigDecimal.valueOf(0)) > 0 &&
                product.getAmountProduct() != null &&
                product.getAmountProduct().compareTo(BigDecimal.valueOf(0)) > 0
        ) {
            return true;
        } else {
            return false;
        }
    }
}
