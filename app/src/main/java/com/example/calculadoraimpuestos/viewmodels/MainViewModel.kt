package com.example.calculadoraimpuestos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculadoraimpuestos.models.Impuestos

class MainViewModel : ViewModel() {

        var Salario = MutableLiveData<String>();
        var Seguro = MutableLiveData<Double>();
        var Pension = MutableLiveData<Double>();
        var Renta = MutableLiveData<Double>();
        var Total = MutableLiveData<Double>();
        var Neto = MutableLiveData<Double>();

        val escalaTasa15: Double = 416222.00
        val escalaTasa20: Double = 624329.01
        val escalaTasa25: Double = 867123.01
        val monto20: Double = 31216.00
        val monto25: Double = 79776.00
        val cantMesesAño: Double = 12.00
        val tasa25: Double = 0.25
        val tasa20: Double = 0.20
        val tasa15: Double = 0.15

    fun onCalcularImpuesto() {
       var salDouble: Double? = Salario.value?.toDouble();
        if (salDouble != null) {
            Seguro.value = (salDouble * 0.0304)
            Pension.value = (salDouble * 0.0287)
            var sumaImpuestosBase: Double = Seguro.value!! + Pension.value!!
            var SalarioConImpuestos = salDouble - sumaImpuestosBase;
            var SalarioConImpuestosAnual = SalarioConImpuestos * 12;
            Renta.value = calcularRenta(SalarioConImpuestosAnual)
            Total.value = Seguro.value!! + Pension.value!! + Renta.value!!
            Neto.value = salDouble - Total.value!!
        };
    }

    fun calcularRenta(salarioAnual: Double) : Double {
            return when {
            salarioAnual > escalaTasa25 -> (tasa25 * (salarioAnual - escalaTasa25) + monto25) / cantMesesAño
            salarioAnual > escalaTasa20 -> (tasa20 * (salarioAnual - escalaTasa20) + monto20) / cantMesesAño
            salarioAnual > escalaTasa15 -> (tasa15 * (salarioAnual - escalaTasa15)) / cantMesesAño
            else -> 0.00
        }
    }
}