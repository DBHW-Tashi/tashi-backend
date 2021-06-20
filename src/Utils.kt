package com.renner

import java.math.BigDecimal

enum class OperatorChars (val sign: Char){
    PLUS('+'),
    SUBTRACT('-'),
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
        a = BigDecimal(a.stripTrailingZeros().toPlainString())
        b *= BigDecimal("10").pow(factor)
        b = BigDecimal(b.stripTrailingZeros().toPlainString())
    }

    while (b != BigDecimal.ZERO) {
        val buffer = b
        b = a.remainder(b)
        a = buffer
    }

    return if (factor > 0) {
        a.divide(BigDecimal("10").pow(factor))
    }
    else {
        a
    }
}

fun parse(MathExpression:String):RationalNumber{
    var curMathExpression = MathExpression;
    if(isEncasedByBrackets(curMathExpression)){
        curMathExpression = curMathExpression.substring(1,curMathExpression.length-1);
    }

     for(i in 0 until OperatorChars.values().size){  //iterate through Operators
         var indexLastOperatorFound:Int = 0;
         for(j in 0 until curMathExpression.filter { it ==  OperatorChars.values()[i].sign}.count()) {    //iterate through instances of current Operator
             val OperatorPosition = curMathExpression.indexOf(OperatorChars.values()[i].sign, indexLastOperatorFound+1)
             indexLastOperatorFound = OperatorPosition;
             if (OperatorPosition > -1 && !isPosInBrackets(curMathExpression, OperatorPosition)) {        //check if the Operator is found and not inside a bracket
                 //parse the subExpression before and after the Operator and then apply the Operator to the results
                 when (OperatorChars.values()[i].sign) {
                     '-' -> {
                         return parse(curMathExpression.substring(0, OperatorPosition)) - parse(curMathExpression.substring(OperatorPosition + 1))
                     }
                     '+' -> {
                         return parse(curMathExpression.substring(0, OperatorPosition)) + parse(curMathExpression.substring(OperatorPosition + 1))
                     }
                     '*' -> {
                         return parse(curMathExpression.substring(0, OperatorPosition)) * parse(curMathExpression.substring(OperatorPosition + 1))
                     }
                     '/' -> {
                         return parse(curMathExpression.substring(0, OperatorPosition)) / parse(curMathExpression.substring(OperatorPosition + 1))
                     }
                 }
             }
         }
        continue
    }
    return RationalNumber(curMathExpression)
}

fun isEncasedByBrackets(input:String):Boolean{
    if(input.length<=1){
        return false
    }
    var level:Int = 0;
    for (i in 0 until input.length-1){
        if(input[i] == '('){
            level ++;
        }else if(input[i] == ')'){
            level --;
        }
        if(level == 0){
            return false
        }
    }
    return true;
}

fun isPosInBrackets(str:String, pos:Int):Boolean{
    var stringBeforePos = str.substring(0,pos);
    if(stringBeforePos.filter { it == '(' }.count() == stringBeforePos.filter { it == ')' }.count()){
        return false
    }
    return true
}

fun validateBrackets(input: String):Boolean{
    var openingBrackets:Int = 0;
    var closingBrackets:Int = 0;
    for (i in input.indices){
        when(input[i]){
            '(' -> {
                openingBrackets++;
            }
            ')' ->{
                closingBrackets++;
            }
        }
        if(closingBrackets>openingBrackets){
            return false
        }
    }
    if(openingBrackets != closingBrackets){
        return false
    }
    return true
}