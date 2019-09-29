/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author rodri
 */

@Entity
@Table(name = "endereco")

public class Endereco implements Serializable {
    
    @Id
    @SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "seq_endereco", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "ENDERECO_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "end_cod")
    private Long endCod;
    
    @Column(name = "end_bairro", length = 100)
    private String endBairro;
    
    @Column(name = "end_log", length = 100)
    private String endLog;
    
    @Column(name = "end_num")
    private Integer endNum;
    
    @Column(name = "end_comp", length = 200)
    private String endComp;
    
    @Column(name = "end_cid", length = 150)
    private String endCid;
    
    @OneToMany(mappedBy = "usuEnd", targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public Endereco() {
    }

    public Long getEndCod() {
        return endCod;
    }

    public void setEndCod(Long endCod) {
        this.endCod = endCod;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndLog() {
        return endLog;
    }

    public void setEndLog(String endLog) {
        this.endLog = endLog;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    public String getEndComp() {
        return endComp;
    }

    public void setEndComp(String endComp) {
        this.endComp = endComp;
    }

    public String getEndCid() {
        return endCid;
    }

    public void setEndCid(String endCid) {
        this.endCid = endCid;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.endCod);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.endCod, other.endCod)) {
            return false;
        }
        return true;
    }
    
}
