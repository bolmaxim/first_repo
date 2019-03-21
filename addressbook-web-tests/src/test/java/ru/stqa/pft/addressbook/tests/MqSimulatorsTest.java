package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.SimData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MqSimulatorsTest extends SimData{

    @DataProvider
    public Iterator<Object[]> propertyListNew() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/simmqNew.txt")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split("=");
            list.add(new Object[] {new SimData().withSimPropertyNew(split[0]).withSimValueNew(split[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> propertyListOld() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/simmqOld.txt")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split("=");
            list.add(new Object[] {new SimData().withSimPropertyOld(split[0]).withSimValueOld(split[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }


    @Test(enabled = false)
    public void readSimData(SimData simData) {
        System.out.println(simData.getSimPropertyNew());
        System.out.println(simData.getsimValueNew());
        System.out.println(simData.getSimPropertyOld());
        System.out.println(simData.getsimValueOld());

    }




}
