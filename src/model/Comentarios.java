/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rodri
 */

@Entity
@Table(name = "comentarios")

public class Comentarios implements Serializable {
    
    @Id
    @SequenceGenerator(name = "COMENTARIOS_SEQ", sequenceName = "seq_comentarios", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "COMENTARIOS_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "com_cod")
    private Long comCod;
    
    @ManyToOne
    @JoinColumn(name = "com_post")
    private Postagem comPost;
    
    @Column(name = "com_text", length = 100)
    private String comText;
    
    @Column(name = "com_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar comData;
    
    @ManyToOne
    @JoinColumn(name = "com_usu")
    private Usuario comUsu;

    public Comentarios() {
    }

    public Long getComCod() {
        return comCod;
    }

    public void setComCod(Long comCod) {
        this.comCod = comCod;
    }

    public Postagem getComPost() {
        return comPost;
    }

    public void setComPost(Postagem comPost) {
        this.comPost = comPost;
    }

    public String getComText() {
        return comText;
    }

    public void setComText(String comText) {
        this.comText = comText;
    }

    public Calendar getComData() {
        return comData;
    }

    public void setComData(Calendar comData) {
        this.comData = comData;
    }

    public Usuario getComUsu() {
        return comUsu;
    }

    public void setComUsu(Usuario comUsu) {
        this.comUsu = comUsu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.comCod);
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
        final Comentarios other = (Comentarios) obj;
        if (!Objects.equals(this.comCod, other.comCod)) {
            return false;
        }
        return true;
    }
    
}
