package com.ensat.controllers;

import com.ensat.entities.Product;
import com.ensat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Product controller.
 */
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning rpoducts:");
        return "products";
    }

    /**
     * View a specific product by its id.
     *
     * @param id1
     * @param model
     * @return
     */
    @RequestMapping("product/{auth_user_id}")
    public String showProduct(@PathVariable Integer auth_user_id, Model model) {
        model.addAttribute("product", productService.getProductById(auth_user_id));
        return "productshow";
    }

    // Afficher le formulaire de modification du Product
    @RequestMapping("product/edit/{auth_user_id}")
    public String edit(@PathVariable Integer auth_user_id, Model model) {
        model.addAttribute("product", productService.getProductById(auth_user_id));
        return "productform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }

    /**
     * Save product to database.
     *
     * @param product1
     * @return
     */
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }

    /**
     * Delete product by its id.
     *
     * @param id1
     * @return
     */
    @RequestMapping("product/delete/{auth_user_id}")
    public String delete(@PathVariable Integer auth_user_id) {
        productService.deleteProduct(auth_user_id);
        return "redirect:/products";
    }

}