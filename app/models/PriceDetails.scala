package models

case class PriceDetails(
                         serviceFees: Set[ServiceFee],
                         costPerMinute: Float,
                         distanceUnit: String, // eg, Mile this could be an ADT
                         minimum: Int,
                         costPerDistance: Float,
                         base: Int,
                         cancellationFee: Int,
                         currencyCode: String // eg, USD this could be an ADT
                       )

sealed trait DistanceUnit
case object Mile extends DistanceUnit
case object Kilometre extends DistanceUnit
