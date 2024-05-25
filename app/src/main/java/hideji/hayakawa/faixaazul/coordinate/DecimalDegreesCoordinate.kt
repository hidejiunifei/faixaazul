package hideji.hayakawa.faixaazul.coordinate


/**
 * This interface models a point on the globe represented by latitude/longitude in
 * decimal degrees.
 *
 * Default implementations of this class are immutable and therefore threadsafe.
 *
 * @param latitude the latitude value for this geographic point.
 * @param longitude the longitude value fo this geographic point.
 */
@Suppress("MagicNumber")
class DecimalDegreesCoordinate private constructor(val latitude: Double, val longitude: Double) {
    companion object {
        fun createDecimalDegreesCoordinate(latitude: Double, longitude: Double):
                DecimalDegreesCoordinate {
            return DecimalDegreesCoordinate(latitude, longitude)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DecimalDegreesCoordinate

        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }
}