package com.example.appbanhang;

public class GhiChuUpdate {
    public int IdGC;
    public String TenGC;

    public GhiChuUpdate(int idGC, String tenGC) {
        IdGC = idGC;
        TenGC = tenGC;
    }

    public int getIdGC() {
        return IdGC;
    }

    public void setIdGC(int idGC) {
        IdGC = idGC;
    }

    public String getTenGC() {
        return TenGC;
    }

    public void setTenGC(String tenGC) {
        TenGC = tenGC;
    }
}
