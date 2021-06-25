package com.renner

import java.lang.Exception
import java.math.BigDecimal

class RationalNumber(var enumerator: BigDecimal, var denominator: BigDecimal = BigDecimal(1)) {

    init{
        if(this.denominator == BigDecimal("0")){
            throw Exception("Division by Zero is not defined")
        }
    }

    constructor(creationString: String) : this(BigDecimal(creationString))
    constructor(creationDouble: Double) : this(BigDecimal(creationDouble.toString()))
    constructor(creationInteger: Int) : this(BigDecimal(creationInteger.toString()))

    operator fun plus(b : RationalNumber): RationalNumber{
        if(this.denominator == b.denominator){
            return RationalNumber(this.enumerator+b.enumerator, this.denominator)
        }
        val lcm:BigDecimal = lcm(this.denominator, b.denominator)
        this.enumerator *= (lcm / this.denominator)
        b.enumerator *= (lcm / b.denominator)
        return (RationalNumber(this.enumerator+b.enumerator, lcm))
    }

    operator fun minus(b : RationalNumber): RationalNumber{
       return this + RationalNumber(BigDecimal(-1)*b.enumerator, b.denominator)
    }

    operator fun times(b: RationalNumber):RationalNumber{
        return RationalNumber(this.enumerator*b.enumerator, this.denominator*b.denominator)
    }

    operator fun div(b: RationalNumber):RationalNumber{
        return RationalNumber(this.enumerator*b.denominator, this.denominator*b.enumerator)
    }

    fun simplify(){
        val gcd:BigDecimal = gcd(this.enumerator, this.denominator)
        if (gcd > BigDecimal.ZERO)
        {
            this.denominator /= gcd
            this.enumerator /= gcd
        }

        this.denominator = this.denominator.stripTrailingZeros()
        this.enumerator = this.enumerator.stripTrailingZeros()
    }

    fun getSimplified():RationalNumber{
        var buffer = RationalNumber(this.enumerator, this.denominator)
        buffer.simplify()

        return buffer
    }

    override fun toString(): String {
        this.simplify()
        if(this.denominator == BigDecimal(1)){
            return this.enumerator.toPlainString()
        }
        return this.enumerator.toPlainString() + "/" + this.denominator.toPlainString()
    }
}