package com.renner

import java.math.BigDecimal

class RationalNumber(var CreationString: String) {
    var denominator: BigDecimal;
    var counter: BigDecimal


    init {
        this.counter = BigDecimal(this.CreationString);
        this.denominator = BigDecimal(1);
    }



    override fun toString(): String {

        return this.counter.toString() + " / " + this.denominator.toString();
    }
}