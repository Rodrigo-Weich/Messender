/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rodri
 */

@Entity
@Table(name = "postagem")

public class Postagem implements Serializable {
    
    @Id
    @SequenceGenerator(name = "POSTAGEM_SEQ", sequenceName = "seq_postagem", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "POSTAGEM_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "post_cod")
    private Long postCod;
    
    @ManyToOne
    @JoinColumn(name = "post_usu")
    private Usuario postUsu;
    
    @Column(name = "post_text", length = 140)
    private String postText;
    
    @Column(name = "post_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar postData;
    
    @OneToMany(mappedBy = "curPost", targetEntity = Curtidas.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Curtidas> postCur;
    
    @OneToMany(mappedBy = "comPost", targetEntity = Comentarios.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comentarios> postCom;

    public Postagem() {
    }

    public Long getPostCod() {
        return postCod;
    }

    public void setPostCod(Long postCod) {
        this.postCod = postCod;
    }

    public Usuario getPostUsu() {
        return postUsu;
    }

    public void setPostUsu(Usuario postUsu) {
        this.postUsu = postUsu;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Calendar getPostData() {
        return postData;
    }

    public void setPostData(Calendar postData) {
        this.postData = postData;
    }

    public List<Curtidas> getPostCur() {
        return postCur;
    }

    public void setPostCur(List<Curtidas> postCur) {
        this.postCur = postCur;
    }

    public List<Comentarios> getPostCom() {
        return postCom;
    }

    public void setPostCom(List<Comentarios> postCom) {
        this.postCom = postCom;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.postCod);
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
        final Postagem other = (Postagem) obj;
        if (!Objects.equals(this.postCod, other.postCod)) {
            return false;
        }
        return true;
    }

}
