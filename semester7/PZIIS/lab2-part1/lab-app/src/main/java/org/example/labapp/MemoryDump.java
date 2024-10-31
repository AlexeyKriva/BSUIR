package org.example.labapp;

import com.sun.management.HotSpotDiagnosticMXBean;

import java.lang.management.ManagementFactory;

public class MemoryDump {
    public static void createHeapDump(String filePath) {
        try {
            HotSpotDiagnosticMXBean mxBean = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
            mxBean.dumpHeap(filePath, true);
            System.out.println("Heap dump created at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}