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
@Table(name = "curtidas")

public class Curtidas implements Serializable {
    
    @Id
    @SequenceGenerator(name = "CURTIDAS_SEQ", sequenceName = "seq_curtidas", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "CURTIDAS_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "cur_cod")
    private Long curCod;

    @ManyToOne
    @JoinColumn(name = "cur_post")
    private Postagem curPost;
    
    @ManyToOne
    @JoinColumn(name = "cur_usu")
    private Usuario curUsu;
    
    @Column(name = "cur_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar curData;

    public Curtidas() {
    }

    public Long getCurCod() {
        return curCod;
    }

    public void setCurCod(Long curCod) {
        this.curCod = curCod;
    }

    public Postagem getCurPost() {
        return curPost;
    }

    public void setCurPost(Postagem curPost) {
        this.curPost = curPost;
    }

    public Usuario getCurUsu() {
        return curUsu;
    }

    public void setCurUsu(Usuario curUsu) {
        this.curUsu = curUsu;
    }

    public Calendar getCurData() {
        return curData;
    }

    public void setCurData(Calendar curData) {
        this.curData = curData;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.curCod);
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
        final Curtidas other = (Curtidas) obj;
        if (!Objects.equals(this.curCod, other.curCod)) {
            return false;
        }
        return true;
    }
    
}
