package com.example.lab7;
public enum StudentCondition
{
    ODRABIAJACY {
        @Override public String toString() {
            return "odrabiający";
        }
    },
    CHORY {
        @Override public String toString() {
            return "chory";
        }
    },
    ZDROWY {
        @Override public String toString() {
            return "zdrowy";
        }
    },
    NIEOBECNY{
        @Override public String toString() {
            return "nieobecny";
        }
    },
    ZAPISANY{
        @Override public String toString() {
            return "zapisany";
        }
    },
    OCZEKIWANY{
        @Override public String toString() {
            return "oczekujący na dodanie";
        }
    },
}
