package controller;


import entity.Product;
import entity.ProductForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ProductService;

@Controller
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/addProduct")
    public String addProduct() {
        log.info("addProduct 被调用！");
        return "addProduct";
    }

    @RequestMapping(value = "saveProduct",method = RequestMethod.POST)
    public String productSave(ProductForm productForm, RedirectAttributes redirectAttributes) {
        log.info("productSave 被调用！");
        log.info("productService is null: " + (productService == null));
        log.info("productForm: name=" + productForm.getName() + ", description=" + productForm.getDescription() + ", price=" + productForm.getPrice());
        Product product=new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        Product addProduct = productService.addProduct(product);
        log.info("addProduct = " + addProduct);
        redirectAttributes.addFlashAttribute("message","产品添加成功，ID："+addProduct.getId());
        return "redirect:/viewProduct/"+addProduct.getId();
    }

    @RequestMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product",product);
        return "viewProduct";
    }
//TODO 修改xml进行分类
    @RequestMapping("retrieveProduct")
    public String retrieveProduct(@RequestParam Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("products",product);
        return "retrieveProduct";
    }
}
