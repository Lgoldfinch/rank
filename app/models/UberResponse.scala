package models

case class UberResponse(taxis: Set[Product]) extends ResponseToAvailableTaxis[Product]

case class Product(isUpfrontFareEnabled: Boolean,
                   capacity: Int,
                   productId: String,
                   priceDetails: PriceDetails,
                   image: String,
                   cashEnabled: Boolean,
                   shared: Boolean,
                   shortDescription: String,
                   productGroup: String,
                   description: String
                  ) extends AvailableTaxi


