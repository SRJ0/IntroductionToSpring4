package sample

class Greeter {
    private var format: String? = null
    fun greet(guest: String?): String {
        return String.format(format!!, guest)
    }

    fun setFormat(format: String?) {
        this.format = format
    }
}
