package org.example;

public class Main {
    public static void main(String[] args) {
        Color red_Color = new Color(255, 0, 0, 255);
        Color green_Color = new Color(0, 255, 0);

        Rectangle rectangle = new Rectangle(5, 10, red_Color);
        Triangle triangle = new Triangle(3, 4, 5, green_Color);

        ShapeDescriber describer = new ShapeDescriber();
        describer.describe(rectangle);
        describer.describe(triangle);

        ShapeDAO.save(rectangle);

        System.out.println("Wszystkie figury: " + ShapeDAO.findAll());

        Shape foundShape = ShapeDAO.findById(1L);
        if (foundShape != null) {
            System.out.println("Znaleziono figurę: " + foundShape.getClass().getSimpleName());
        }

        ShapeDAO.deleteById(1L);

        ShapeDAO.close();
    }
}

// W linii 5 i 6 tworzone są kolory, które będą użyte w liniach 8 i 9 podczas tworzenia figur.
// Następnie w liniach 11-13 jest kod odpowiedzialny za opisywanie kształtów.


