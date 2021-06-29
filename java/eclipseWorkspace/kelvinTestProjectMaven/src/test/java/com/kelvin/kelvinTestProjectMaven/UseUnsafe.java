package com.kelvin.kelvinTestProjectMaven;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UseUnsafe {

    public int i = 0;
    public int j = 0;

    public static void main(String[] args) throws NoSuchFieldException {
        try {
            //get unsafe class instance by reflection.
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe u = (Unsafe) field.get(null);

            u = sun.misc.Unsafe.getUnsafe();

            //get property's offset of class
            long addressI = u.objectFieldOffset(UseUnsafe.class.getField("i"));
            System.out.println("address of i is: " + addressI);
            long addressJ = u.objectFieldOffset(UseUnsafe.class.getField("j"));
            System.out.println("address of j is: " + addressJ);

            //compareAndSwapInt: get value at offset addressI of object test, if value is 0, set it to 18
            UseUnsafe test = new UseUnsafe();
            u.compareAndSwapInt(test,addressI,0,18);
            System.out.println(test.i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
