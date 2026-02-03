package com.example.prueba.config;

import com.example.prueba.entity.ProductoFinanciero;
import com.example.prueba.entity.Usuario;
import com.example.prueba.repository.ProductoFinancieroRepository;
import com.example.prueba.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProductoFinancieroRepository productoRepository;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           ProductoFinancieroRepository productoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {

        // =========================
        // USUARIOS (5)
        // =========================
        if (usuarioRepository.count() == 0) {

            Usuario u1 = new Usuario();
            u1.setNombre("Juan Pérez");
            u1.setEmail("juan.perez@email.com");
            u1.setCapitalDisponible(new BigDecimal("5000.00"));

            Usuario u2 = new Usuario();
            u2.setNombre("María García");
            u2.setEmail("maria.garcia@email.com");
            u2.setCapitalDisponible(new BigDecimal("8000.00"));

            Usuario u3 = new Usuario();
            u3.setNombre("Carlos López");
            u3.setEmail("carlos.lopez@email.com");
            u3.setCapitalDisponible(new BigDecimal("1000.00"));

            Usuario u4 = new Usuario();
            u4.setNombre("Ana Torres");
            u4.setEmail("ana.torres@email.com");
            u4.setCapitalDisponible(new BigDecimal("4000.00"));

            Usuario u5 = new Usuario();
            u5.setNombre("Luis Mendoza");
            u5.setEmail("luis.mendoza@email.com");
            u5.setCapitalDisponible(new BigDecimal("2000.00"));

            usuarioRepository.save(u1);
            usuarioRepository.save(u2);
            usuarioRepository.save(u3);
            usuarioRepository.save(u4);
            usuarioRepository.save(u5);

            System.out.println("✔ Usuarios insertados");
        }

        // =========================
        // PRODUCTOS FINANCIEROS (8)
        // =========================
        if (productoRepository.count() == 0) {

            ProductoFinanciero p1 = new ProductoFinanciero();
            p1.setNombre("Fondo Acciones Tech");
            p1.setDescripcion("Fondo de inversión en acciones tecnológicas");
            p1.setCosto(new BigDecimal("1000.00"));
            p1.setPorcentajeRetorno(new BigDecimal("8.50"));
            p1.setActivo(true);

            ProductoFinanciero p2 = new ProductoFinanciero();
            p2.setNombre("Bonos Corporativos AAA");
            p2.setDescripcion("Bonos corporativos de alta calificación");
            p2.setCosto(new BigDecimal("500.00"));
            p2.setPorcentajeRetorno(new BigDecimal("5.25"));
            p2.setActivo(true);

            ProductoFinanciero p3 = new ProductoFinanciero();
            p3.setNombre("ETF Global");
            p3.setDescripcion("Fondo indexado de mercados internacionales");
            p3.setCosto(new BigDecimal("1500.00"));
            p3.setPorcentajeRetorno(new BigDecimal("12.00"));
            p3.setActivo(true);

            ProductoFinanciero p4 = new ProductoFinanciero();
            p4.setNombre("Fondo de Dividendos");
            p4.setDescripcion("Fondo enfocado en empresas con pago de dividendos");
            p4.setCosto(new BigDecimal("800.00"));
            p4.setPorcentajeRetorno(new BigDecimal("6.75"));
            p4.setActivo(true);

            ProductoFinanciero p5 = new ProductoFinanciero();
            p5.setNombre("Bonos del Tesoro");
            p5.setDescripcion("Bonos soberanos de bajo riesgo");
            p5.setCosto(new BigDecimal("1200.00"));
            p5.setPorcentajeRetorno(new BigDecimal("4.50"));
            p5.setActivo(true);

            ProductoFinanciero p6 = new ProductoFinanciero();
            p6.setNombre("Cuenta de Ahorro");
            p6.setDescripcion("Producto de ahorro con liquidez inmediata");
            p6.setCosto(new BigDecimal("0.00"));
            p6.setPorcentajeRetorno(new BigDecimal("1.50"));
            p6.setActivo(true);

            ProductoFinanciero p7 = new ProductoFinanciero();
            p7.setNombre("Fondo Conservador");
            p7.setDescripcion("Fondo de inversión de bajo riesgo");
            p7.setCosto(new BigDecimal("600.00"));
            p7.setPorcentajeRetorno(new BigDecimal("3.25"));
            p7.setActivo(true);

            ProductoFinanciero p8 = new ProductoFinanciero();
            p8.setNombre("Fondo Premium");
            p8.setDescripcion("Fondo de alto riesgo y alto retorno");
            p8.setCosto(new BigDecimal("3000.00"));
            p8.setPorcentajeRetorno(new BigDecimal("15.00"));
            p8.setActivo(true);

            productoRepository.save(p1);
            productoRepository.save(p2);
            productoRepository.save(p3);
            productoRepository.save(p4);
            productoRepository.save(p5);
            productoRepository.save(p6);
            productoRepository.save(p7);
            productoRepository.save(p8);

            System.out.println("✔ Productos financieros insertados");
        }
    }
}


