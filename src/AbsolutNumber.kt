package com.renner

class AbsolutNumber(var CreationString: String, val base: Int) {
    var Values: UByteArray = UByteArray(1);
    var Sing: Boolean = true;

    init {
        if (this.CreationString.substring(0, 1) == "-") {
            this.Sing = false;
            this.CreationString = this.CreationString.trim { it == '-' || it <= ' ' }  //trim whitespace and -
        }
        this.Values = UByteArray(this.CreationString.length)  //Klappt solange 1 stelle Creation string 1 UByte entspricht
        if (this.base > 10) {
            throw Error("not yet implemented");
        } else {
            var i:Int = 0;
            for(curChar: Char in this.CreationString) {
                Values.set(i, Character.getNumericValue(curChar).toUByte())
                i++;
            }
        }

    }

    override fun toString(): String {
        var resString: String = "";
        if(!this.Sing){
            resString = "-"
        }
        for(pos in this.Values){
            resString += pos.toString();
        }
        return resString
    }
}