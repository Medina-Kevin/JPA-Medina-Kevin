package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "precio")
    private int precio;

    @OneToMany(mappedBy = "articulo",cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<DetalleFactura> detalle = new ArrayList<DetalleFactura>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "articulo_categoria",joinColumns = @JoinColumn(name = "articulo_id"),inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    @Builder.Default
    private List<Categoria> categorias = new ArrayList<Categoria>();
}
