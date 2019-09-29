/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author rodri
 */
public class DaoGenerico {

    public static DaoGenerico daoGenerico = null;
    private SessionFactory factory = HibernateUtil.getSessionFactory();
    private Session sessao = null;

    private DaoGenerico() {
        factory = HibernateUtil.getSessionFactory();
    }

    public void getSessao() {
        try {
            if (sessao == null) {
                sessao = factory.openSession();
            } else if (!sessao.isOpen() || !sessao.isConnected()) {
                sessao = factory.openSession();
            }
            sessao.setFlushMode(FlushMode.COMMIT);
        } catch (Exception e) {
        }
    }

    public static DaoGenerico getInstance() {
        if (daoGenerico == null) {
            daoGenerico = new DaoGenerico();
        }
        return daoGenerico;
    }

    public Object ultObj;

    public boolean inserir(Object o) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            ultObj = sessao.merge(o);
            transacao.commit();
            return true;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sessao.close();
        }
    }

    public boolean Alterar(Object objeto) {
        //abre sessao
        getSessao();
        //inicia a transacao
        Transaction transacao = sessao.beginTransaction();
        try {
            //Salva o objeto
            sessao.saveOrUpdate(objeto);
            //grava no banco que jogou
            transacao.commit();
            //retorna que salvou
            return true;
        } catch (Exception e) {
            //se deu erro cancela tudo
            transacao.rollback();
            // imprime o pilha de erros
            e.printStackTrace();
            //retorna que nao salvou
            return false;
        } finally {
            //sempre fecha
            sessao.close();
        }

    }

    //metodo excluir
    public boolean Deletar(Object objeto) {
        //abre sessao
        getSessao();
        //inicia a transacao
        Transaction transacao = sessao.beginTransaction();
        try {
            //Salva o objeto
            sessao.delete(objeto);
            //grava no banco que jogou
            transacao.commit();
            //retorna que salvou
            return true;
        } catch (Exception e) {
            //se deu erro cancela tudo
            transacao.rollback();
            // imprime o pilha de erros
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir\n" + e.getMessage());
            //retorna que nao salvou
            return false;
        } finally {
            //sempre fecha
            sessao.close();
        }

    }

    public List ListarTodos(Class objeto) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            List retorno = sessao.createCriteria(objeto).list();
            transacao.commit();
            return retorno;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List ListarTodosLike(Class objeto, String campo, Object valor) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria criteria = sessao.createCriteria(objeto);
            criteria.setFetchMode(campo, FetchMode.JOIN);
            criteria.add(Restrictions.ilike(campo, valor.toString(), MatchMode.ANYWHERE));
            List retorno = criteria.list();
            transacao.commit();
            return retorno;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Object BuscarUnicoWhere(Class objeto, String campo, Object valor) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria criteria = sessao.createCriteria(objeto);
            criteria.add(Restrictions.eq(campo, valor));
            Object retorno = criteria.uniqueResult();
            transacao.commit();
            return retorno;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcao para buscar um registro no banco com varios filtros, varios
     * registros no where
     *
     * @param objeto A classe relativa a tabela a ser buscada
     * @param filtro Um HashMap com o primeiro paramentro o campo e o segundo o
     * valor a ser buscado
     * @return retorna o registro do banco de dados
     */
    public Object buscarUnicoWhereAnd(Class objeto, Map<String, Object> filtro) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria criteria = sessao.createCriteria(objeto);
            for (Map.Entry<String, Object> fil : filtro.entrySet()) {
                criteria.add(Restrictions.eq(fil.getKey(), fil.getValue()));
            }
            Object retorno = criteria.uniqueResult();
            transacao.commit();
            return retorno;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Object BuscarTodosWhere(Class objeto, String campo, Object valor) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria criteria = sessao.createCriteria(objeto);
            criteria.setFetchMode(campo, FetchMode.JOIN);
            criteria.add(Restrictions.eq(campo, valor));
            List retorno = criteria.list();
            transacao.commit();
            return retorno;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    public List ListarTodosOrderByDesc(Class objeto, String campo) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria criteria = sessao.createCriteria(objeto);
            List retorno = criteria.addOrder(Order.desc(campo)).list();
            transacao.commit();
            return retorno;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    public Object BuscarTodosWhereOrderByDesc(Class objeto, String order, String campo, Object valor) {
        getSessao();
        Transaction transacao = sessao.beginTransaction();
        try {
            Criteria criteria = sessao.createCriteria(objeto);
            criteria.setFetchMode(campo, FetchMode.JOIN);
            criteria.add(Restrictions.eq(campo, valor));
            List retorno = criteria.addOrder(Order.desc(order)).list();
            transacao.commit();
            return retorno;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            return null;
        }
    }

}
