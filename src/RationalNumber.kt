package com.renner

import java.math.BigDecimal

class RationalNumber(var enumerator: BigDecimal, var denominator: BigDecimal = BigDecimal(1)) {

    constructor(creationString: String) : this(BigDecimal(creationString)) {}

    operator fun plus(b : RationalNumber): RationalNumber{
        if(this.denominator == b.denominator){
            return RationalNumber(this.enumerator+b.enumerator, this.denominator);
        }
        val lcm:BigDecimal = lcm(this.denominator, b.denominator);
        this.enumerator *= (lcm / this.denominator)
        b.enumerator *= (lcm / b.denominator)

        return (RationalNumber(this.enumerator+b.enumerator, lcm));
    }

    operator fun minus(b : RationalNumber): RationalNumber{
       return this + RationalNumber(BigDecimal(-1)*b.enumerator, b.denominator)
    }

    override fun toString(): String {

        return this.enumerator.toString() + " / " + this.denominator.toString();
    }
}