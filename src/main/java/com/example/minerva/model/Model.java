package com.example.minerva.model;
/**
 * Interface base para todas as entidades do sistema.
 *
 * Define um contrato mínimo para que as classes de modelo forneçam
 * um identificador único por meio do método {@link #getId()}. */
public interface Model {
    Object getId();
}
