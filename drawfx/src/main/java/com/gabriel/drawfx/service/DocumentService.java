package com.gabriel.drawfx.service;

public interface DocumentService {
    void save();
    void saveAs(String filename);
    void open(String filename);
}
