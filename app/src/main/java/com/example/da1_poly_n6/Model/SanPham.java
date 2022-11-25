package com.example.da1_poly_n6.Model;

public class SanPham {
    int image;
    String TenSanPham;
    Double Price;
    String Size;
    int MaLoai;
    String Mota;

    public SanPham(int image, String tenSanPham, Double price, String size, int maLoai, String mota) {
        this.image = image;
        TenSanPham = tenSanPham;
        Price = price;
        Size = size;
        MaLoai = maLoai;
        Mota = mota;
    }

    public SanPham() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
