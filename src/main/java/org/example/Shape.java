package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Color color;

    protected Shape() {
        // Konstruktor bezargumentowy wymagany przez JPA
    }

    public Shape(Color color) {
        this.color = color;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public String getColorDescription(){
        return "Red: " + color.red() +
                ", Green: " + color.green() +
                ", Blue: " + color.blue() +
                ", Alpha: " + color.alpha();
    }
}

@Entity
@Table(name = "rectangles")
class Rectangle extends Shape {

    private double width;
    private double height;

    protected Rectangle() {

    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Rectangle (double width, double height, Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * width + 2 * height;
    }
}

@Entity
@Table(name = "triangles")
class Triangle extends Shape {

    private double a, b, c;

    protected Triangle() {

    }

    public Triangle (double a, double b, double c, Color color){
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

}

class ShapeDescriber {

    private static final Logger logger = LoggerFactory.getLogger(ShapeDescriber.class);

    public void describe (Shape shape){
        logger.info("Kolor kształtu: " + shape.getColorDescription());
        logger.info("Pole kształtu: " + shape.getArea());
        logger.info("Obwód kształtu: " + shape.getPerimeter());
    }
}
