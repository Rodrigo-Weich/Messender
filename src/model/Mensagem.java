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
@Table(name = "mensagem")

public class Mensagem implements Serializable {
    
    @Id
    @SequenceGenerator(name = "MENSAGEM_SEQ", sequenceName = "seq_mensagem", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "MENSAGEM_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "men_cod")
    private Long menCod;
    
    @ManyToOne
    @JoinColumn(name = "men_ori")
    private Usuario menOri;
    
    @ManyToOne
    @JoinColumn(name = "men_des")
    private Usuario menDes;
    
    @Column(name = "men_text", length = 255)
    private String menText;

    @Column(name = "men_denv")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar menDenv;
    
    @Column(name = "men_drec")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar menDrec;
    
    @Column(name = "men_dlid")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar menDlid;

    public Mensagem() {
    }

    public Long getMenCod() {
        return menCod;
    }

    public void setMenCod(Long menCod) {
        this.menCod = menCod;
    }

    public Usuario getMenOri() {
        return menOri;
    }

    public void setMenOri(Usuario menOri) {
        this.menOri = menOri;
    }

    public Usuario getMenDes() {
        return menDes;
    }

    public void setMenDes(Usuario menDes) {
        this.menDes = menDes;
    }

    public String getMenText() {
        return menText;
    }

    public void setMenText(String menText) {
        this.menText = menText;
    }

    public Calendar getMenDenv() {
        return menDenv;
    }

    public void setMenDenv(Calendar menDenv) {
        this.menDenv = menDenv;
    }

    public Calendar getMenDrec() {
        return menDrec;
    }

    public void setMenDrec(Calendar menDrec) {
        this.menDrec = menDrec;
    }

    public Calendar getMenDlid() {
        return menDlid;
    }

    public void setMenDlid(Calendar menDlid) {
        this.menDlid = menDlid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.menCod);
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.menCod, other.menCod)) {
            return false;
        }
        return true;
    }


}
