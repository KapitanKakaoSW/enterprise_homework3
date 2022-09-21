package com.hillel.enterprise_homework3.services;

import com.hillel.enterprise_homework3.dtos.ProductDTO;
import com.hillel.enterprise_homework3.exceptions.ProductNotFoundException;
import com.hillel.enterprise_homework3.models.ProductModel;
import com.hillel.enterprise_homework3.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        ProductModel product = new ProductModel(
                productDTO.getProductName(),
                productDTO.getProductDescription(),
                productDTO.getPrice());
        productRepository.getProducts().put(product.getProductId(), product);
    }

    @Override
    public Collection<ProductModel> getAllProducts() {
        return productRepository.getProducts().values();
    }

    @Override
    public ProductModel getProductById(Integer id) throws ProductNotFoundException {
        if (productRepository.getProducts().containsKey(id)) {
            return productRepository.getProducts().get(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public Collection<ProductModel> getProductByName(String name) throws ProductNotFoundException {
        Collection<ProductModel> products = productRepository.getProducts().values()
                .stream().filter(productModel -> productModel.getProductName().equals(name))
                .collect(Collectors.toList());

        if (products.isEmpty()) {
            throw new ProductNotFoundException(name);
        } else {
            return products;
        }
    }

    @Override
    public void updateProductById(Integer id, ProductDTO productDTO) throws ProductNotFoundException {
        if (productRepository.getProducts().containsKey(id)) {
            ProductModel product = productRepository.getProducts().get(id);
            product.setProductName(productDTO.getProductName());
            product.setProductDescription(productDTO.getProductDescription());
            product.setProductPrice(productDTO.getPrice());
        } else  {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public void removeProductById(Integer id) throws ProductNotFoundException {
        if (productRepository.getProducts().containsKey(id)){
            productRepository.getProducts().remove(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }
}
