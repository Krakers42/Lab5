package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DataBaseTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("=== Test: Tworzenie tabeli i zapis ===");

            em.getTransaction().begin();
            Color color1 = new Color(255, 0, 0, 128);
            Color color2 = new Color(0, 255, 0, 128);
            Rectangle rectangle1 = new Rectangle(10.0, 20.0, color1);
            Rectangle rectangle2 = new Rectangle(5.0, 15.0, color2);
            em.persist(rectangle1);
            em.persist(rectangle2);
            em.getTransaction().commit();
            System.out.println("Zapisano prostokąty do bazy danych.");

            System.out.println("=== Test: Odczyt wszystkich rekordów ===");
            TypedQuery<Rectangle> query = em.createQuery("SELECT r FROM Rectangle r", Rectangle.class);
            List<Rectangle> rectangles = query.getResultList();
            for (Rectangle rect : rectangles) {
                System.out.println("Prostokąt - ID: " + rect.hashCode() + ", Kolor: " + rect.getColorDescription());
            }

            System.out.println("=== Test: Aktualizacja rekordu ===");
            if (!rectangles.isEmpty()) {
                em.getTransaction().begin();
                Rectangle rectangleToUpdate = rectangles.get(0);
                rectangleToUpdate.setHeight(123);
                em.merge(rectangleToUpdate);
                em.getTransaction().commit();
                System.out.println("Zaktualizowano prostokąt o ID: " + rectangleToUpdate.hashCode() + ", Nowy kolor: " + rectangleToUpdate.getColorDescription());
            }

            System.out.println("=== Test: Odczyt po aktualizacji ===");
            List<Rectangle> updatedRectangles = query.getResultList();
            for (Rectangle rect : updatedRectangles) {
                System.out.println("Zaktualizowany prostokąt - ID: " + rect.hashCode() + ", Kolor: " + rect.getColorDescription());
            }
            System.out.println("=== Test: Usuwanie rekordu ===");
            if (!rectangles.isEmpty()) {
                em.getTransaction().begin();
                Rectangle rectangleToDelete = rectangles.get(0);
                rectangleToDelete = em.merge(rectangleToDelete);
                em.remove(rectangleToDelete);
                em.getTransaction().commit();
                System.out.println("Usunięto prostokąt o ID: " + rectangleToDelete.hashCode());
            }

            System.out.println("=== Test: Odczyt po usunięciu ===");
            List<Rectangle> remainingRectangles = query.getResultList();
            for (Rectangle rect : remainingRectangles) {
                System.out.println("Pozostały prostokąt - ID: " + rect.hashCode() + ", Kolor: " + rect.getColorDescription());
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
