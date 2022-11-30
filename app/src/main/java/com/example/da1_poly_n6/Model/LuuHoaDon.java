package com.example.da1_poly_n6.Model;

public class LuuHoaDon {
    int maLuu;
    int maHoaDon;
    String NameUser;
    String NameKH;
    int maSanPham;
    int soLuong;
    double donGia;
    String size;
    String ngayLap;

    public LuuHoaDon(int maLuu, int maHoaDon, String nameUser, String nameKH, int maSanPham, int soLuong, double donGia, String size, String ngayLap) {
        this.maLuu = maLuu;
        this.maHoaDon = maHoaDon;
        NameUser = nameUser;
        NameKH = nameKH;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.size = size;
        this.ngayLap = ngayLap;
    }

    public LuuHoaDon(int maHoaDon, String nameUser, String nameKH, int maSanPham, int soLuong, double donGia, String size, String ngayLap) {
        this.maHoaDon = maHoaDon;
        NameUser = nameUser;
        NameKH = nameKH;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.size = size;
        this.ngayLap = ngayLap;
    }

    public int getMaLuu() {
        return maLuu;
    }

    public void setMaLuu(int maLuu) {
        this.maLuu = maLuu;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }
}
