package dao;

import modelo.Produto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto p) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();
            System.out.println("✓ Produto cadastrado com sucesso!");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("✗ Erro ao salvar produto: " + e.getMessage());
        }
    }

    public List<Produto> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produto> query = session.createQuery("FROM Produto", Produto.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("✗ Erro ao listar produtos: " + e.getMessage());
            return List.of();
        }
    }
    
    public Produto buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produto.class, id);
        } catch (Exception e) {
            System.out.println("✗ Erro ao buscar produto: " + e.getMessage());
            return null;
        }
    }
}
