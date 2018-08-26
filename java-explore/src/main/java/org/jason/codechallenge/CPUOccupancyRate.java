package org.jason.codechallenge;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.TimeUnit;

public class CPUOccupancyRate {
  public static void main(String[] args) throws Exception {
    System.out.println(getProcessCpuLoad());
  }

  public static void simpleSolution() {
    System.out.println("Started");
    while (true) {

    }
  }

  private static void work(int milisec) {
    for (int i = 0; i < 900_000_000; ++i);
  }

  private static void sleep(int milisec) {
    try {
      TimeUnit.MILLISECONDS.sleep(milisec);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static double getProcessCpuLoad() throws Exception {
    OperatingSystemMXBean xBean = ManagementFactory.getOperatingSystemMXBean();
    return xBean.getSystemLoadAverage();
  }
}
