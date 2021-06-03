package com.renner

import java.math.BigDecimal

enum class OperatorChars (val sign: Char){
    SUBTRACT('-'),
    PLUS('+'),
    MULTIPLY('*'),
    DIVIDE('/')
}

fun lcm (a:BigDecimal, b:BigDecimal):BigDecimal{
    return if (a == BigDecimal(0) || b == BigDecimal(0)) {
        BigDecimal(0)
    }
    else {
        (a * b).abs() / gcd(a, b)
    }
}

// TO-DO: Better variable and parameter names
fun gcd(A:BigDecimal, B:BigDecimal):BigDecimal{
    var a: BigDecimal
    var b: BigDecimal

    // ensure that variables are in right order
    // otherwise gcd makes no sense
    if (A >= B) {
        a = A
        b = B
    }
    else {
        a = B
        b = A
    }

    var factor = if (a.scale() >= b.scale()) a.scale() else b.scale()

    if (factor > 0) {
        a *= BigDecimal("10").pow(factor)
        b *= BigDecimal("10").pow(factor)
    }

    while (b != BigDecimal("0.0")) {
        val buffer = b
        b = a.remainder(b)
        a = buffer
    }

    return if (factor > 0) {
        a / BigDecimal(factor)
    }
    else {
        a
    }
}

fun parse(MathExpression:String):RationalNumber{
     for(i in 0 until OperatorChars.values().size){
        val OperatorPosition = MathExpression.indexOf(OperatorChars.values()[i].sign)
        if (OperatorPosition > -1){
            when(OperatorChars.values()[i].sign){
                '-' -> {
                    return parse(MathExpression.substring(0,OperatorPosition)) - parse(MathExpression.substring(OperatorPosition+1))
                }
               '+' -> {
                   return parse(MathExpression.substring(0,OperatorPosition)) + parse(MathExpression.substring(OperatorPosition+1))
               }
                '*' -> {
                    return parse(MathExpression.substring(0,OperatorPosition)) * parse(MathExpression.substring(OperatorPosition+1))
               }
                '/' -> {
                    return parse(MathExpression.substring(0,OperatorPosition)) / parse(MathExpression.substring(OperatorPosition+1))
                }
            }
        }
        continue
    }
    return RationalNumber(MathExpression)
}