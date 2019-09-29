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
@Table(name = "usuario")

public class Usuario implements Serializable {
    
    @Id
    @SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "USUARIO_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "usu_cod")
    private Long usuCod;
    
    @Column(name = "usu_nome", length = 45)
    private String usuNome;
    
    @Column(name = "usu_nasc")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar usuNasc;
    
    @Column(name = "usu_login", length = 45, nullable = false)
    private String usuLogin;
    
    @Column(name = "usu_senha", length = 45, nullable = false)
    private String usuSenha;
    
    @Column(name = "usu_cad")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar usuCad;
    
    @Column(name = "usu_frase", length = 75)
    private String usuFrase;
    
    @Column(name = "usu_resp", length = 30)
    private String usuResp;
    
    @Column(name = "usu_email", length = 45)
    private String usuEmail;
    
    @ManyToOne
    @JoinColumn(name = "usu_end")
    private Endereco usuEnd;
    
    @OneToMany(mappedBy = "menOri", targetEntity = Mensagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mensagem> mensOri;
    
    @OneToMany(mappedBy = "menDes", targetEntity = Mensagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mensagem> mensDes;
    
    @OneToMany(mappedBy = "postUsu", targetEntity = Postagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Postagem> postsUsu;
    
    @OneToMany(mappedBy = "comUsu", targetEntity = Comentarios.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comentarios> comeUsu;
    
    @OneToMany(mappedBy = "postUsu", targetEntity = Postagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Curtidas> curtUsu;

    public Usuario() {
    }

    public Long getUsuCod() {
        return usuCod;
    }

    public void setUsuCod(Long usuCod) {
        this.usuCod = usuCod;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public Calendar getUsuNasc() {
        return usuNasc;
    }

    public void setUsuNasc(Calendar usuNasc) {
        this.usuNasc = usuNasc;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuSenha() {
        return usuSenha;
    }

    public void setUsuSenha(String usuSenha) {
        this.usuSenha = usuSenha;
    }

    public Calendar getUsuCad() {
        return usuCad;
    }

    public void setUsuCad(Calendar usuCad) {
        this.usuCad = usuCad;
    }

    public String getUsuFrase() {
        return usuFrase;
    }

    public void setUsuFrase(String usuFrase) {
        this.usuFrase = usuFrase;
    }

    public String getUsuResp() {
        return usuResp;
    }

    public void setUsuResp(String usuResp) {
        this.usuResp = usuResp;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public Endereco getUsuEnd() {
        return usuEnd;
    }

    public void setUsuEnd(Endereco usuEnd) {
        this.usuEnd = usuEnd;
    }

    public List<Mensagem> getMensOri() {
        return mensOri;
    }

    public void setMensOri(List<Mensagem> mensOri) {
        this.mensOri = mensOri;
    }

    public List<Mensagem> getMensDes() {
        return mensDes;
    }

    public void setMensDes(List<Mensagem> mensDes) {
        this.mensDes = mensDes;
    }

    public List<Postagem> getPostsUsu() {
        return postsUsu;
    }

    public void setPostsUsu(List<Postagem> postsUsu) {
        this.postsUsu = postsUsu;
    }

    public List<Comentarios> getComeUsu() {
        return comeUsu;
    }

    public void setComeUsu(List<Comentarios> comeUsu) {
        this.comeUsu = comeUsu;
    }

    public List<Curtidas> getCurtUsu() {
        return curtUsu;
    }

    public void setCurtUsu(List<Curtidas> curtUsu) {
        this.curtUsu = curtUsu;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.usuCod);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.usuCod, other.usuCod)) {
            return false;
        }
        return true;
    }
    
}