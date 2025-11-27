package dao;

import modelo.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente c) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(c);
            transaction.commit();
            System.out.println("✓ Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            // Verifica se é erro de CPF duplicado (SQLite)
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("✗ Erro: CPF já cadastrado no sistema!");
            } else {
                System.out.println("✗ Erro ao salvar cliente: " + e.getMessage());
            }
        }
    }

    public List<Cliente> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cliente> query = session.createQuery("FROM Cliente", Cliente.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("✗ Erro ao listar clientes: " + e.getMessage());
            return List.of();
        }
    }
    
    public Cliente buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cliente.class, id);
        } catch (Exception e) {
            System.out.println("✗ Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }
}
