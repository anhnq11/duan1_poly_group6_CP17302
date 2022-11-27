package com.example.da1_poly_n6.Model;

public class GioHang {
    int maSanPham;
    int soLuong;
    String size;
    double donGia;

    public GioHang(int maSanPham, int soLuong, String size, double donGia) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.size = size;
        this.donGia = donGia;
    }

    public GioHang() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
