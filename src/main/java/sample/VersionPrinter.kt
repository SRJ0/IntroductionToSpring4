package sample

class VersionPrinter {
    private var majorVersion = 0
    private var minorVersion = 0
    fun setMajorVersion(majorVersion: Int) {
        this.majorVersion = majorVersion
    }

    fun setMinorVersion(minorVersion: Int) {
        this.minorVersion = minorVersion
    }

    fun print() {
        System.out.printf("버전 : %d.%d \n", majorVersion, minorVersion)
    }
}
