//import model.Client;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class Main {
//    public static void main(String[] args) {
//         Client client = Client.builder()
//                 .personalId(1l)
//                .firstName("Jan")
//                .lastName("Kowalski")
//                .clientType(ClientType.NORMAL)
//                .build();
//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(client);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
//    }
//}
