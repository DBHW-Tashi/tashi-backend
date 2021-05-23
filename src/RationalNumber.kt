package com.renner

import java.math.BigDecimal

class RationalNumber(var CreationString: String) {
    var denominator: BigDecimal;
    var enumerator: BigDecimal


    init {
        this.enumerator = BigDecimal(this.CreationString);
        this.denominator = BigDecimal(1);
    }



    override fun toString(): String {

        return this.enumerator.toString() + " / " + this.denominator.toString();
    }
}