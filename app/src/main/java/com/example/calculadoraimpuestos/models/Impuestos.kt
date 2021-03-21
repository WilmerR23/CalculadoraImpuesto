package com.example.calculadoraimpuestos.models

data class Impuestos(val Salario: Int, val Seguro: Int, val Pension: Int, val Renta: Int, val Total: Int, val Neto: Int) {
}