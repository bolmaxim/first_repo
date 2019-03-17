package ru.stqa.pft.addressbook.model;

public class SimData {
    private String simPropertyNew;
    private String simValueNew;

    public SimData withSimPropertyNew(String simPropertyNew) {
        this.simPropertyNew = simPropertyNew;
        return this;
    }

    public SimData withSimValueNew(String simValueNew) {
        this.simValueNew = simValueNew;
        return this;
    }

    public String getSimPropertyNew() {
        return simPropertyNew;
    }


    public String getsimValueNew() {
        return simValueNew;
    }

    private String simPropertyOld;
    private String simValueOld;

    public SimData withSimPropertyOld(String simPropertyOld) {
        this.simPropertyOld = simPropertyOld;
        return this;
    }

    public SimData withSimValueOld(String simValueOld) {
        this.simValueOld = simValueOld;
        return this;
    }

    public String getSimPropertyOld() {
        return simPropertyOld;
    }


    public String getsimValueOld() {
        return simValueOld;
    }


}
