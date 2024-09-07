package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("En marcha");
        try {
            //Persistir una nueva entidad
            entityManager.getTransaction().begin();

            //Factura
            Factura f1 = Factura.builder()
                    .numero(12)
                    .fecha("25/02/2000")
                    .total(120)
                    .build();

            Domicilio d1 = Domicilio.builder()
                    .nombreCalle("San Martin")
                    .numero(1222)
                    .build();

            Cliente c1 = Cliente.builder()
                    .nombre("Micalea")
                    .apellido("Torrez")
                    .dni(45123652)
                    .build();

            Categoria cat1 = Categoria.builder()
                    .denominacion("Perecederos")
                    .build();

            Categoria cat2 = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();

            Categoria cat3 = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Yogur de frutilla")
                    .precio(20)
                    .build();

            Articulo art2 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Detergente Magistral")
                    .precio(80)
                    .build();

            DetalleFactura detalle1 = DetalleFactura.builder()
                    .subtotal(40)
                    .cantidad(2)
                    .build();


            //Relaciones
            c1.setDomicilio(d1);
            d1.setCliente(c1);
            f1.setCliente(c1);
            f1.getDetalles().add(detalle1);
            detalle1.setFactura(f1);
            art1.getCategorias().add(cat1);
            art1.getCategorias().add(cat2);
            art2.getCategorias().add(cat3);
            cat2.getArticulos().add(art1);
            cat1.getArticulos().add(art1);
            cat3.getArticulos().add(art2);
            detalle1.setArticulo(art1);
            art1.getDetalle().add(detalle1);

            //persistencia de los datos
            entityManager.persist(f1);
            entityManager.persist(c1);
            entityManager.persist(cat1);
            entityManager.persist(cat2);
            entityManager.persist(cat3);
            entityManager.persist(d1);
            entityManager.persist(detalle1);
            entityManager.persist(art1);
            entityManager.persist(art2);


            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("// No se ha podido guardar a la persona //");
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}

