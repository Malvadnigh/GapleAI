package com.gaplestrategist.model;

import java.util.Objects;

public class Domino {
    private int side1;
    private int side2;
    private boolean isDouble;
    private int totalValue;
    
    public Domino(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
        this.isDouble = side1 == side2;
        this.totalValue = side1 + side2;
    }
    
    public int getSide1() { return side1; }
    public int getSide2() { return side2; }
    public boolean isDouble() { return isDouble; }
    public int getTotalValue() { return totalValue; }
    
    public boolean canConnect(int value) {
        return side1 == value || side2 == value;
    }
    
    public int getOtherSide(int value) {
        if (side1 == value) return side2;
        if (side2 == value) return side1;
        return -1; // Cannot connect
    }
    
    public int getValue() {
        if (isDouble) {
            return totalValue; // Double tiles have special value
        }
        return totalValue;
    }
    
    // Gaple scoring - some dominoes have special point values
    public int getGaplePoints() {
        if (isDouble) {
            return totalValue * 2; // Double tiles worth more
        }
        return totalValue;
    }
    
    @Override
    public String toString() {
        return "[" + side1 + "|" + side2 + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Domino domino = (Domino) obj;
        return (side1 == domino.side1 && side2 == domino.side2) ||
               (side1 == domino.side2 && side2 == domino.side1);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(Math.min(side1, side2), Math.max(side1, side2));
    }
}