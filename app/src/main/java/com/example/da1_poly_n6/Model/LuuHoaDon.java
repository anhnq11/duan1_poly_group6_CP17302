package com.example.da1_poly_n6.Model;

public class LuuHoaDon {
    int maHoaDon;
    String NameUser;
    String NameKH;
    int SDT;
    int maSanPham;
    int soLuong;
    double donGia;

    public LuuHoaDon(int maHoaDon, String nameUser, String nameKH, int SDT, int maSanPham, int soLuong, double donGia) {
        this.maHoaDon = maHoaDon;
        NameUser = nameUser;
        NameKH = nameKH;
        this.SDT = SDT;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public LuuHoaDon() {
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String nameUser) {
        NameUser = nameUser;
    }

    public String getNameKH() {
        return NameKH;
    }

    public void setNameKH(String nameKH) {
        NameKH = nameKH;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
