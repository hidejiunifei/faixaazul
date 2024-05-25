package hideji.hayakawa.faixaazul.coordinate

import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan


/**
 * This interface models a point in the Universal Transverse Mercator coordinate system.
 * There are two valid formats for UTM coordinates.  Those are:
 *
 * `<zone number><latitude band letter><space><easting><space><northing>`
 * e.g. 10Q -204832 302043
 *
 * or
 *
 * `<zone number><space><easting><space><northing>`
 * e.g. 10 -204832 302043
 *
 * The default implementation of this class are immutable and therefore threadsafe.
 *
 * @param zoneNumber the zone number for this UTM coordinate.
 * @param easting the easting value for the UTM coordinate.
 * @param northing the northing value of the UTM coordinate.
 */
// TODO RAP 19 Jul 18: enums or typedefs for latitudeBand and nsIndicator
@Suppress("MagicNumber")
class UtmCoordinate(
    val zoneNumber: Int,
    val easting: Double,
    val northing: Double,
) {

    fun utm2Deg() : DecimalDegreesCoordinate {
        val north = northing - 10000000

        var latitude =
            (north / 6366197.724 / 0.9996 + (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(
                2.0
            ) - 0.006739496742 * sin(north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996) * (atan(
                cos(
                    atan(
                        (exp(
                            (easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(
                                    north / 6366197.724 / 0.9996
                                ).pow(2.0))
                            )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0) / 3)
                        ) - exp(
                            -(easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0) / 3)
                        )) / 2 / cos(
                            (north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996 - 0.006739496742 * 3 / 4 * (north / 6366197.724 / 0.9996 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) / 2) + (0.006739496742 * 3 / 4).pow(2.0) * 5 / 3 * (3 * (north / 6366197.724 / 0.9996 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                                2.0
                            )) / 4 - (0.006739496742 * 3 / 4).pow(3.0) * 35 / 27 * (5 * (3 * (north / 6366197.724 / 0.9996 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                                2.0
                            )) / 4 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) * cos(north / 6366197.724 / 0.9996).pow(2.0) * cos(north / 6366197.724 / 0.9996).pow(
                                2.0
                            )) / 3)) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0)) + north / 6366197.724 / 0.9996
                        )
                    )
                ) * tan(
                    (north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996 - 0.006739496742 * 3 / 4 * (north / 6366197.724 / 0.9996 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) / 2) + (0.006739496742 * 3 / 4).pow(2.0) * 5 / 3 * (3 * (north / 6366197.724 / 0.9996 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                        2.0
                    )) / 4 - (0.006739496742 * 3 / 4).pow(3.0) * 35 / 27 * (5 * (3 * (north / 6366197.724 / 0.9996 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                        2.0
                    )) / 4 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) * cos(north / 6366197.724 / 0.9996).pow(2.0) * cos(north / 6366197.724 / 0.9996).pow(
                        2.0
                    )) / 3)) / (0.9996 * 6399593.625 / sqrt(
                        (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                    )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                        (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                    ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0)) + north / 6366197.724 / 0.9996
                )
            ) - north / 6366197.724 / 0.9996) * 3 / 2) * (atan(
                cos(
                    atan(
                        (exp(
                            (easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(
                                    north / 6366197.724 / 0.9996
                                ).pow(2.0))
                            )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0) / 3)
                        ) - exp(
                            -(easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0) / 3)
                        )) / 2 / cos(
                            (north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996 - 0.006739496742 * 3 / 4 * (north / 6366197.724 / 0.9996 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) / 2) + (0.006739496742 * 3 / 4).pow(2.0) * 5 / 3 * (3 * (north / 6366197.724 / 0.9996 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                                2.0
                            )) / 4 - (0.006739496742 * 3 / 4).pow(3.0) * 35 / 27 * (5 * (3 * (north / 6366197.724 / 0.9996 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                                2.0
                            )) / 4 + sin(
                                2 * north / 6366197.724 / 0.9996
                            ) * cos(north / 6366197.724 / 0.9996).pow(2.0) * cos(north / 6366197.724 / 0.9996).pow(
                                2.0
                            )) / 3)) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                                (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                            ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0)) + north / 6366197.724 / 0.9996
                        )
                    )
                ) * tan(
                    (north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996 - 0.006739496742 * 3 / 4 * (north / 6366197.724 / 0.9996 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) / 2) + (0.006739496742 * 3 / 4).pow(2.0) * 5 / 3 * (3 * (north / 6366197.724 / 0.9996 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                        2.0
                    )) / 4 - (0.006739496742 * 3 / 4).pow(3.0) * 35 / 27 * (5 * (3 * (north / 6366197.724 / 0.9996 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                        2.0
                    )) / 4 + sin(
                        2 * north / 6366197.724 / 0.9996
                    ) * cos(north / 6366197.724 / 0.9996).pow(2.0) * cos(north / 6366197.724 / 0.9996).pow(
                        2.0
                    )) / 3)) / (0.9996 * 6399593.625 / sqrt(
                        (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                    )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                        (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                    ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0)) + north / 6366197.724 / 0.9996
                )
            ) - north / 6366197.724 / 0.9996)) * 180 / Math.PI
        latitude = Math.round(latitude * 10000000).toDouble()
        latitude /= 10000000
        var longitude = atan(
            (exp(
                (easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                    (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(
                        2.0
                    ))
                )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                    (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0) / 3)
            ) - exp(
                -(easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                    (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                    (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0) / 3)
            )) / 2 / cos(
                (north - 0.9996 * 6399593.625 * (north / 6366197.724 / 0.9996 - 0.006739496742 * 3 / 4 * (north / 6366197.724 / 0.9996 + sin(
                    2 * north / 6366197.724 / 0.9996
                ) / 2) + (0.006739496742 * 3 / 4).pow(2.0) * 5 / 3 * (3 * (north / 6366197.724 / 0.9996 + sin(
                    2 * north / 6366197.724 / 0.9996
                ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                    2.0
                )) / 4 - (0.006739496742 * 3 / 4).pow(3.0) * 35 / 27 * (5 * (3 * (north / 6366197.724 / 0.9996 + sin(
                    2 * north / 6366197.724 / 0.9996
                ) / 2) + sin(2 * north / 6366197.724 / 0.9996) * cos(north / 6366197.724 / 0.9996).pow(
                    2.0
                )) / 4 + sin(
                    2 * north / 6366197.724 / 0.9996
                ) * cos(north / 6366197.724 / 0.9996).pow(2.0) * cos(north / 6366197.724 / 0.9996).pow(
                    2.0
                )) / 3)) / (0.9996 * 6399593.625 / sqrt(
                    (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                )) * (1 - 0.006739496742 * ((easting - 500000) / (0.9996 * 6399593.625 / sqrt(
                    (1 + 0.006739496742 * cos(north / 6366197.724 / 0.9996).pow(2.0))
                ))).pow(2.0) / 2 * cos(north / 6366197.724 / 0.9996).pow(2.0)) + north / 6366197.724 / 0.9996
            )
        ) * 180 / Math.PI + zoneNumber * 6 - 183
        longitude = Math.round(longitude * 10000000).toDouble()
        longitude /= 10000000

        return DecimalDegreesCoordinate.createDecimalDegreesCoordinate(latitude, longitude)
    }
}