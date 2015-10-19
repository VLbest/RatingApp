package com.exia.lan.ratingapp.controller;

public class Question extends AbstractQuestion {

    private int _id;
    private int rating_A;
    private int rating_B;
    private int rating_C;
    private int rating_D;

    public Question() {
        this.rating_A = 0;
        this.rating_B = 0;
        this.rating_C = 0;
        this.rating_D = 0;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getRating_A() {
        return rating_A;
    }

    public void setRating_A(int rating_A) {
        this.rating_A = rating_A;
    }

    public int getRating_B() {
        return rating_B;
    }

    public void setRating_B(int rating_B) {
        this.rating_B = rating_B;
    }

    public int getRating_C() {
        return rating_C;
    }

    public void setRating_C(int rating_C) {
        this.rating_C = rating_C;
    }

    public int getRating_D() {
        return rating_D;
    }

    public void setRating_D(int rating_D) {
        this.rating_D = rating_D;
    }
}
