/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;


import java.util.Date;
/**
 *
 * @author vergo_000
 */
public class Prestamos {
    private int codigo;
    private Libros codigoL;
    private Usuarios codigoU;
    private Date fechaP;
    private Date fechaD;

    public Prestamos(int codigo, Libros codigoL, Usuarios codigoU, Date fechaP, Date fechaD) {
        this.codigo = codigo;
        this.codigoL = codigoL;
        this.codigoU = codigoU;
        this.fechaP = fechaP;
        this.fechaD = fechaD;
    }

    public Prestamos() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Libros getCodigoL() {
        return codigoL;
    }

    public void setCodigoL(Libros codigoL) {
        this.codigoL = codigoL;
    }

    public Usuarios getCodigoU() {
        return codigoU;
    }

    public void setCodigoU(Usuarios codigoU) {
        this.codigoU = codigoU;
    }

    public Date getFechaP() {
        return fechaP;
    }

    public void setFechaP(Date fechaP) {
        this.fechaP = fechaP;
    }

    public Date getFechaD() {
        return fechaD;
    }

    public void setFechaD(Date fechaD) {
        this.fechaD = fechaD;
    }
    
}
