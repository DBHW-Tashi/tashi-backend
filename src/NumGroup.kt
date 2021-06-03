package com.renner

class NumGroup(var A:ReturnsRationalNumber, var B:ReturnsRationalNumber, var op:Operation) : ReturnsRationalNumber {
    override fun get():RationalNumber {
        return when (this.op) {
            Operation.Addition -> A.get() + B.get();
            Operation.Subtraction -> A.get() - B.get();
            Operation.Multiplication -> A.get() * B.get();
            Operation.Division -> A.get() / B .get();
            else -> {
                RationalNumber("NaN");
            }
        }
    }
}