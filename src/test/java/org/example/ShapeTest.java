package org.example;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {

    @Test
    void newColor() {
        Color positive_red_Color = new Color(255, 0, 0, 2755);
    }

    @Test
    void newColor_2() {
        Color negative_red_Color = new Color(255, 0, 0, -255);
    }

    @Test
    void newColor_3() {
        Color too_high_red_Color = new Color(257, 0, 0, 255);
    }

    @Test
    void newColor_4() {
        Color too_low_red_Color = new Color(-55, 0, 0, 255);
    }

    @Test
    void newColor_5() {
        Color too_high_green_Color = new Color(0, 1000, 0, 255);
    }

    @Test
    void newColor_6() {
        Color too_low_green_Color = new Color(0, -1000, 0, 255);
    }

    @Test
    void newColor_7() {
        Color too_high_blue_Color = new Color(0, 0, 1234, 255);
    }

    @Test
    void newColor_8() {
        Color too_low_blue_Color = new Color(0, 0, -1234, 255);
    }

    @Test
    void testRectangleArea() {
        Rectangle rectangle = new Rectangle(20, 10, new Color(0, 255, 10, 200));
        assertEquals(200, rectangle.getArea(), "Pole tego prostokata powinno sie rownac 200.");
    }

    @Test
    void testRectanglePerimeter() {
        Rectangle rectangle = new Rectangle(20, 10, new Color(255, 0, 50, 255));
        assertEquals(60, rectangle.getPerimeter(), "Obwod tego prostokata powinien sie rownac 60");
    }

    @Test
    void testTriangleArea() {
        Triangle triangle = new Triangle(10, 10, 10, new Color(0, 0, 255));
        assertEquals(43.3, triangle.getArea(), 0.01, "Pole tego trojkata powinno sie rownac okolo 43.3");
    }

    @Test
    void testTrianglePerimeter() {
        Triangle triangle = new Triangle(10, 10, 10, new Color(0, 255, 255));
        assertEquals(30, triangle.getPerimeter(), "Obwod tego trojkata powinien sie rownac 30");
    }

    @Test
    void testDescribeShape() {
        Triangle triangle = new Triangle(10, 10, 10, new Color(0, 255, 255));
        Rectangle rectangle = new Rectangle(10, 10, new Color(255, 255, 255, 255));
        ShapeDescriber describer = new ShapeDescriber();

        describer.describe(triangle);
        describer.describe(rectangle);

        assertNotNull(triangle, "Obiekt trojkąt powinien istnieć.");
        assertNotNull(rectangle, "Obiekt prostokat powinien istnieć.");
    }
}