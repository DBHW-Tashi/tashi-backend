package com.renner

class AbsolutNumber(var CreationString: String, val base: Int) {
    var digits: UByteArray;
    var sing: Boolean = true;

    init {
        if (this.CreationString.substring(0, 1) == "-") {
            this.sing = false;
            this.CreationString = this.CreationString.trim { it == '-' || it <= ' ' }  //trim whitespace and -
        }
        this.digits = UByteArray(this.CreationString.length)  //Klappt solange 1 stelle Creation string 1 UByte entspricht
        if (this.base != 10) {
            throw Error("not yet implemented");
        } else {
            var i:Int = 0;
            for(curChar: Char in this.CreationString) {
                digits.set(i, Character.getNumericValue(curChar).toUByte())
                i++;
            }
        }
    }

    operator fun plus(b : AbsolutNumber):AbsolutNumber{
        var i = 0;
        while(true){
            if(this.digits[i] + b.digits[i] < this.base.toUInt())
        }
    }

    override fun toString(): String {
        var resString: String = "";
        if(!this.sing){
            resString = "-"
        }
        for(pos in this.digits){
            resString += pos.toString();
        }
        return resString
    }
}