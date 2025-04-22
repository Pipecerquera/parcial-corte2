package com.backend_parcial.backend_parcial.controller;

import com.backend_parcial.backend_parcial.model.Invoice;
import com.backend_parcial.backend_parcial.model.InvoiceItem;
import com.backend_parcial.backend_parcial.model.Product;
import com.backend_parcial.backend_parcial.repository.InvoiceItemRepository;
import com.backend_parcial.backend_parcial.repository.InvoiceRepository;
import com.backend_parcial.backend_parcial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8080"})
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @PostMapping
    public ResponseEntity<?> saveInvoice(@RequestBody Map<String, Object> payload) {
        try {
            String customer = (String) payload.get("customer");
            String paymentMethod = (String) payload.get("paymentMethod");
            double total = ((Number) payload.get("total")).doubleValue();
            List<Map<String, Object>> productList = (List<Map<String, Object>>) payload.get("products");

            Invoice invoice = new Invoice();
            invoice.setCustomer(customer);
            invoice.setPaymentMethod(paymentMethod);
            invoice.setTotal(total);

            invoice = invoiceRepository.save(invoice);

            List<InvoiceItem> items = new ArrayList<>();

            for (Map<String, Object> p : productList) {
                Product product;

                if (p.get("id") != null) {
                    Long productId = ((Number) p.get("id")).longValue();
                    Optional<Product> optionalProduct = productRepository.findById(productId);
                    if (optionalProduct.isPresent()) {
                        product = optionalProduct.get();
                    } else {
                        // Producto no existe, se crea uno nuevo
                        String name = (String) p.get("name");
                        double price = ((Number) p.get("price")).doubleValue();
                        product = new Product(name, price);
                        product = productRepository.save(product);
                    }
                } else {
                    // ID nulo, se crea producto nuevo
                    String name = (String) p.get("name");
                    double price = ((Number) p.get("price")).doubleValue();
                    product = new Product(name, price);
                    product = productRepository.save(product);
                }

                InvoiceItem item = new InvoiceItem();
                item.setInvoice(invoice);
                item.setProduct(product);
                item.setQuantity(1); // Puedes cambiar si tu frontend manda la cantidad

                invoiceItemRepository.save(item);
                items.add(item);
            }

            invoice.setItems(items);
            invoice = invoiceRepository.save(invoice);

            return ResponseEntity.ok(invoice);
        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error en consola
            return ResponseEntity.status(500).body("Error al guardar la factura: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
