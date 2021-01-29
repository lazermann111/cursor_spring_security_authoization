package com.cursor.hw17.utils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LaptopGenerator {
    private static final String[] brand = new String[]{"Samsung", "HP", "Dell", "MSI", "Acer"};
    private static final String[] model = new String[]{"A51", "1532", "1148", "Pavilion", "Aspire"};
    private static final String[] processor = new String[]{"AMD Ryzen 3", "AMD Ryzen 5", "Intel Pentium", "Intel Core i5"};
    private static final Integer[] ram = new Integer[]{8, 16, 32, 64};
    private static final boolean[] used = new boolean[]{true, false};
    private static final String[] caseType = new String[]{"slim", "ati", "mini tower", "midi tower", "big tower"};
    private static final Integer[] price = new Integer[]{800, 2000, 5000, 8000, 9000, 15000, 30000};


    public String Brand() {
        int rnd = new Random().nextInt(brand.length);
        return brand[rnd];
    }

    public String Model() {
        int rnd = new Random().nextInt(model.length);
        return model[rnd];
    }

    public String Processor() {
        int rnd = new Random().nextInt(processor.length);
        return processor[rnd];
    }

    public Integer Ram() {
        int rnd = new Random().nextInt(ram.length);
        return ram[rnd];
    }

    public Boolean Used() {
        int rnd = new Random().nextInt(used.length);
        return used[rnd];
    }

    public String CaseType() {
        int rnd = new Random().nextInt(caseType.length);
        return caseType[rnd];
    }

    public Integer Price() {
        int rnd = new Random().nextInt(price.length);
        return price[rnd];
    }

    public Date dateOfProduction() {
        Date startDate = new Date(1262296800000L);
        Date endDate = new Date(1609365600000L);
        long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
        return new Date(random);
    }
}



