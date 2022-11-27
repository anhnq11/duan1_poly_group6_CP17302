package com.example.da1_poly_n6.Model;

import java.util.Date;

public class HoaDon {
    int maUser;
    String tenKhachHang;
    String SDT;
    Date NgayLapHD;
    int maGiohang;

    public HoaDon(int maUser, String tenKhachHang, String SDT, Date ngayLapHD, int maGiohang) {
        this.maUser = maUser;
        this.tenKhachHang = tenKhachHang;
        this.SDT = SDT;
        NgayLapHD = ngayLapHD;
        this.maGiohang = maGiohang;
    }

    public HoaDon() {
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        NgayLapHD = ngayLapHD;
    }

    public int getMaGiohang() {
        return maGiohang;
    }

    public void setMaGiohang(int maGiohang) {
        this.maGiohang = maGiohang;
    }
}
