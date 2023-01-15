package com.example.tensorflow;

public class dbHandler {

   String disease;
   String prea;

    public dbHandler() {

    }

    public dbHandler(String disease, String prea) {
        this.disease = disease;
        this.prea = prea;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPrea() {
        return prea;
    }

    public void setPrea(String prea) {
        this.prea = prea;
    }
}
