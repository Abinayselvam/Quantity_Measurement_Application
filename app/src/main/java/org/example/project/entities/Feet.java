package org.example.project.entities;

public class Feet {
    private final double value;
    public Feet(double feet) {
        this.value = feet;
    }

    @Override
    public boolean equals(Object obj)
    {
        //same reference
        if (obj == this)
            return true;

        //null check
        if (obj == null)
            return false;

        //check different type safe
        if (getClass() != obj.getClass())
            return false;

        Feet feet1 = (Feet) obj;
        return Double.compare(feet1.value,this.value)==0;
    }
}
