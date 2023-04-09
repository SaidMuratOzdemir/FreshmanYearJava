//@author S. Murat ÖZDEMİR
//@since 30.03.2023 - 22:54

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ex4_20210808006 {
    public static void main(String[] args) {
        CPU cpu = new CPU("Intel Core i7", 2.6);
        RAM ram = new RAM("DDR4", 8);
        String[] peripherals = {"Mouse", "Keyboard", "Monitor"};
        Desktop desktop = new Desktop(cpu, ram, peripherals);
        System.out.println(desktop);
        desktop.run();
        System.out.println(desktop);
        System.out.println(desktop.plugOut());
        System.out.println(desktop.plugOut(0));
        System.out.println(desktop);
        System.out.println();

        Laptop laptop = new Laptop(cpu, ram, 10000);
        System.out.println("laptop");
        System.out.println(laptop);
        laptop.run();
        System.out.println("laptop after run");
        System.out.println(laptop);
        laptop.run();
        System.out.println("laptop 2nd after run");
        for (int i = 0; i < 10; i++) {
            System.out.println(laptop +" - " + (i+1) + ". run");
            laptop.run();
        }
        System.out.println("laptop 10th after run");
        System.out.println(laptop);
    }
}

class Computer {
    protected CPU cpu;
    protected RAM ram;

    public Computer(CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public void run() {
        for (int i = 0; i < ram.getCapacity(); i++) {
            ram.setValue(0, 0, cpu.compute(ram.getValue(i, i), ram.getValue(0, 0)));
        }
    }

    @Override
    public String toString() {
        return "Computer: " + cpu + " " + ram;
    }

}

class Laptop extends Computer {
    private int milliAmp;
    private int battery;

    public Laptop(CPU cpu, RAM ram, int milliAmp) {
        super(cpu, ram);
        this.milliAmp = milliAmp;
        this.battery = (int) (milliAmp * 0.3);
    }

    public int batteryPercentage() {
        return (int) ((double)battery / (milliAmp) * 100);
    }

    public void charge() {
        while (batteryPercentage() < 90) {
            battery += 2 * (milliAmp / 100);
        }
    }

    @Override
    public void run() {
        if (batteryPercentage() > 5) {
            super.run();
            battery = (int) ((batteryPercentage() - 3) / 100.0 * milliAmp);
        } else {
            charge();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + battery;
    }
}

class Desktop extends Computer {
    private ArrayList<String> peripherals;

    public Desktop(CPU cpu, RAM ram, String[] peripherals) {
        super(cpu, ram);
        this.peripherals = new ArrayList<>();
        this.peripherals.addAll(Arrays.asList(peripherals));
    }

    @Override
    public void run() {
        for (int i = 0; i < ram.getCapacity(); i++) {
            for (int j = 0; j < ram.getCapacity(); j++) {
                ram.setValue(0, 0, cpu.compute(ram.getValue(i, j), ram.getValue(0, 0)));
            }
        }
    }

    public void plugIn(String peripheral) {
        peripherals.add(peripheral);
    }

    public String plugOut() {
        String lastElement = peripherals.get(peripherals.size() - 1);
        peripherals.remove(peripherals.size() - 1);
        return lastElement;
    }

    public String plugOut(int index) {
        String element = peripherals.get(index);
        peripherals.remove(index);
        return element;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.join(" ", peripherals);
    }
}

class CPU {
    private String name;
    private double clock;

    public CPU(String name, double clock) {
        this.name = name;
        this.clock = clock;
    }

    public String getName() {
        return name;
    }

    public double getClock() {
        return clock;
    }

    public int compute(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "CPU: " + name + " " + clock + " GHz";
    }
}

class RAM {
    private String type;
    private int capacity;
    private int[][] memory;

    public RAM(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        initMemory();
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    private void initMemory() {
        memory = new int[capacity][capacity];
        Random random = new Random();
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                memory[i][j] = random.nextInt(11);
            }
        }
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < capacity && j >= 0 && j < capacity;
    }

    public int getValue(int i, int j) {
        return check(i, j) ? memory[i][j] : -1;
    }

    public void setValue(int i, int j, int value) {
        if (check(i, j)) {
            memory[i][j] = value;
        }
    }

    @Override
    public String toString() {
        return "RAM: " + type + " " + capacity + "GB";
    }
}