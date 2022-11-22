package com.example.da1_poly_n6.Model;

public class SanPham {

    int MaSanPham;
    String MaLoai;
    String TenSanPham;
    String MaSize;
    double DonGia;
    int SoLuongBan;
    int AnhSanPham;
    String MoTa;

    public SanPham(int maSanPham, String maLoai, String tenSanPham, String maSize, double donGia, int soLuongBan, int anhSanPham, String moTa) {
        MaSanPham = maSanPham;
        MaLoai = maLoai;
        TenSanPham = tenSanPham;
        MaSize = maSize;
        DonGia = donGia;
        SoLuongBan = soLuongBan;
        AnhSanPham = anhSanPham;
        MoTa = moTa;
    }

    public SanPham() {
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }

    public String getMaSize() {
        return MaSize;
    }

    public void setMaSize(String maSize) {
        MaSize = maSize;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    public int getSoLuongBan() {
        return SoLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        SoLuongBan = soLuongBan;
    }

    public int getAnhSanPham() {
        return AnhSanPham;
    }

    public void setAnhSanPham(int anhSanPham) {
        AnhSanPham = anhSanPham;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }
}
